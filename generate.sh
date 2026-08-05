#!/usr/bin/env bash

set -euo pipefail

REPO_ROOT=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
cd "$REPO_ROOT"

SOURCE_PATH=${SOURCE_PATH:-../chat}
MODELS_DIR=src/main/java/io/getstream/models
FIXTURES_DIR=src/test/resources/fixtures/webhooks

# Building chat-manager pulls in a huge Go dependency graph. Without headroom the
# compiler dies with "no space left on device" partway through and leaves a
# half-generated tree behind, so check up front instead.
MIN_FREE_GB=${MIN_FREE_GB:-20}

if [ ! -d "$SOURCE_PATH" ]
then
  echo "cannot find chat path on the parent folder (${SOURCE_PATH}), do you have a copy of the API source?";
  exit 1;
fi

free_gb() {
  df -Pk "$1" | awk 'NR==2 { print int($4 / 1048576) }'
}

for volume in "${TMPDIR:-/tmp}" "$REPO_ROOT"; do
  available=$(free_gb "$volume")
  if [ "$available" -lt "$MIN_FREE_GB" ]; then
    echo "only ${available}GB free on ${volume}, need ~${MIN_FREE_GB}GB to build the generator"
    echo "reclaim space with: go clean -cache && ./gradlew --stop && rm -rf ~/.gradle/caches/build-cache-*"
    exit 1
  fi
done

# Generated sources are wiped before regenerating, so roll them back rather than
# leaving the tree in a half-generated state if any step below fails.
rollback() {
  status=$?
  if [ "$status" -ne 0 ]; then
    echo "generation failed (exit ${status}), restoring generated sources"
    git checkout -- "$MODELS_DIR" "$FIXTURES_DIR" 2>/dev/null || true
    git clean -fdq "$MODELS_DIR" "$FIXTURES_DIR" 2>/dev/null || true
  fi
}
trap rollback EXIT

set -x

# Build the generator first so a compile failure never touches the SDK tree.
( cd "$SOURCE_PATH" ; make openapi )

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
trap - EXIT

# format generated code, clean stale Gradle/Spotless caches, then build
./gradlew clean spotlessApply build -x test
