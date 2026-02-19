#!/usr/bin/env bash

SOURCE_PATH=../chat

if [ ! -d $SOURCE_PATH ]
then
  echo "cannot find chat path on the parent folder (${SOURCE_PATH}), do you have a copy of the API source?";
  exit 1;
fi

set -ex

# cd in API repo, generate new spec and then generate code from it
( cd $SOURCE_PATH ; make openapi ; go run ./cmd/chat-manager openapi generate-client --language java --spec ./releases/v2/serverside-api.yaml --output ../stream-sdk-java )

sed -i '' '/^    @JsonProperty("Role")$/N;/\n    private String role;$/d' src/main/java/io/getstream/models/CallParticipant.java

# Clean up test files that may exist in main source from older generator versions
# (generator now outputs tests directly to src/test/)
rm -f src/main/java/io/getstream/WebhookTest.java

# format generated code, clean stale Gradle/Spotless caches, then build
./gradlew clean spotlessApply build -x test
