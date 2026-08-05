#!/usr/bin/env bash

set -euo pipefail

REPO_ROOT=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
cd "$REPO_ROOT"

SOURCE_PATH=${SOURCE_PATH:-../chat}
MODELS_DIR=src/main/java/io/getstream/models
FIXTURES_DIR=src/test/resources/fixtures/webhooks

# Everything generate-client and generate-webhook-fixtures write. Snapshotted
# before the run so a failure anywhere can put the tree back exactly as it was.
# services/ and models/ also hold hand-written sources, which is fine: the
# snapshot restores whatever was there rather than whatever git has.
GENERATED_PATHS=(
  "$MODELS_DIR"
  src/main/java/io/getstream/services
  src/main/java/io/getstream/Webhook.java
  src/test/java/io/getstream/WebhookTest.java
  "$FIXTURES_DIR"
)

# Building chat-manager pulls in a huge Go dependency graph. Without headroom the
# compiler dies with "no space left on device" partway through and leaves a
# half-generated tree behind, so check up front instead.
MIN_FREE_GB=${MIN_FREE_GB:-30}

if [ ! -d "$SOURCE_PATH" ]
then
  echo "cannot find chat path on the parent folder (${SOURCE_PATH}), do you have a copy of the API source?";
  exit 1;
fi

# df needs a path that exists; the Go caches may not have been created yet.
existing_ancestor() {
  local path=$1
  while [ ! -e "$path" ] && [ "$path" != "/" ]; do
    path=$(dirname "$path")
  done
  printf '%s' "$path"
}

mount_point() {
  df -Pk "$1" | awk 'NR == 2 { for (i = 6; i <= NF; i++) printf "%s%s", (i > 6 ? " " : ""), $i }'
}

free_gb() {
  df -Pk "$1" | awk 'NR == 2 { print int($4 / 1048576) }'
}

# The build spends its space in the Go build and module caches, which usually
# live under $HOME rather than on the repo or TMPDIR volume. Probe all of them so
# the check still means something when they sit on separate disks.
volumes=("${TMPDIR:-/tmp}" "$REPO_ROOT" "$HOME")
if command -v go >/dev/null 2>&1; then
  volumes+=("$(go env GOCACHE)" "$(go env GOMODCACHE)")
fi

checked_mounts=""
for volume in "${volumes[@]}"; do
  [ -n "$volume" ] || continue
  volume=$(existing_ancestor "$volume")
  mount=$(mount_point "$volume")

  # One message per filesystem, however many probed paths share it.
  if printf '%s' "$checked_mounts" | grep -Fxq "$mount"; then
    continue
  fi
  checked_mounts="${checked_mounts}${mount}"$'\n'

  available=$(free_gb "$volume")
  if [ "$available" -lt "$MIN_FREE_GB" ]; then
    echo "only ${available}GB free on ${mount} (${volume}), need ~${MIN_FREE_GB}GB to build the generator"
    echo "reclaim space with: go clean -cache && ./gradlew --stop && rm -rf ~/.gradle/caches/build-cache-*"
    exit 1
  fi
done

SNAPSHOT=$(mktemp -d)
ROLLBACK_ARMED=0

take_snapshot() {
  local path
  for path in "${GENERATED_PATHS[@]}"; do
    [ -e "$path" ] || continue
    mkdir -p "$SNAPSHOT/$(dirname "$path")"
    cp -R "$path" "$SNAPSHOT/$(dirname "$path")/"
  done
}

# Restores the pre-run contents, including uncommitted edits and untracked files
# that a git-based rollback would throw away.
restore_snapshot() {
  local path
  for path in "${GENERATED_PATHS[@]}"; do
    rm -rf "$path"
    if [ -e "$SNAPSHOT/$path" ]; then
      mkdir -p "$(dirname "$path")"
      cp -R "$SNAPSHOT/$path" "$(dirname "$path")/"
    fi
  done
}

cleanup() {
  status=$?
  if [ "$status" -ne 0 ] && [ "$ROLLBACK_ARMED" -eq 1 ]; then
    echo "generation failed (exit ${status}), restoring generated sources"
    restore_snapshot
  fi
  rm -rf "$SNAPSHOT"
}
trap cleanup EXIT

set -x

# Build the generator first so a compile failure never touches the SDK tree.
( cd "$SOURCE_PATH" ; make openapi )

take_snapshot
ROLLBACK_ARMED=1

# Every file the generator writes here is regenerated from scratch, so wipe them
# first: schemas and webhook events dropped from the spec would otherwise survive
# as stale sources. models/framework is hand-written, hence the maxdepth.
find "$MODELS_DIR" -maxdepth 1 -name '*.java' -delete
rm -rf "$FIXTURES_DIR"

( cd "$SOURCE_PATH" ; ./build/chat-manager openapi generate-client --language java --spec ./releases/v2/serverside-api.yaml --output "$REPO_ROOT" )

# Generate webhook conformance fixtures (CHA-2961). The test template reads them from
# src/test/resources/fixtures/webhooks/ and gracefully skips if the dir is missing.
( cd "$SOURCE_PATH" ; ./build/chat-manager openapi generate-webhook-fixtures --output "$REPO_ROOT/$FIXTURES_DIR" )

# CallParticipant carries both "role" and "Role"; drop the duplicate that Jackson
# would reject. The model comes and goes across spec revisions, so patch it only
# when it is actually generated.
if [ -f src/main/java/io/getstream/models/CallParticipant.java ]; then
  perl -i -0pe 's/    \@JsonProperty\("Role"\)\n    private String role;\n//g' src/main/java/io/getstream/models/CallParticipant.java
fi

# Clean up test files that may exist in main source from older generator versions
# (generator now outputs tests directly to src/test/)
rm -f src/main/java/io/getstream/WebhookTest.java

# Generated output is complete and coherent from here on: a compile failure is
# something to inspect, not something to roll back.
ROLLBACK_ARMED=0

# format generated code, clean stale Gradle/Spotless caches, then build
./gradlew clean spotlessApply build -x test
