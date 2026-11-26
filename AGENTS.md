# stream-sdk-java

Official Java server SDK for Stream Chat, Video, Feeds, and Moderation.

- Default branch: `main`
- Maven coordinates: `io.getstream:stream-sdk-java` (`group` / `artifactId` in `build.gradle.kts`)
- Version: `gradle.properties` (`version=…`)
- Gradle project (not Maven). Java toolchain 21, bytecode 17.
- Clone sibling of the chat monorepo as `../chat` (required for OpenAPI regen)

## Layout

Generated (do not hand-edit): `src/main/java/io/getstream/models/*.java` (except `models/framework/`), `src/main/java/io/getstream/services/*.java` (except `services/framework/`), `src/main/java/io/getstream/Webhook.java`, `src/test/java/io/getstream/WebhookTest.java`, `src/test/resources/fixtures/webhooks/`.

Handwritten: `src/main/java/io/getstream/models/framework/`, `src/main/java/io/getstream/services/framework/`, `src/main/java/io/getstream/exceptions/`, `src/main/java/io/getstream/annotations/`. Tests under `src/test/java/io/getstream/` are handwritten except `WebhookTest.java`.

`generate.sh` snapshots generated paths and rolls them back if generation fails. `models/framework` is preserved (`find … -maxdepth 1`).

## Local commands

```bash
cp local.properties.example local.properties
# Tests read io.getstream.apiKey / apiSecret / url from local.properties
# CI instead injects STREAM_API_KEY, STREAM_API_SECRET, STREAM_BASE_URL
./gradlew test
./gradlew spotlessCheck
./gradlew spotlessApply
./gradlew build
./generate.sh
```

CI uses JDK 17 (Corretto). Lombok is required in the IDE. Spotless uses Google Java Format 1.28.0.

`CONTRIBUTING.md` still says “Maven project” and mentions RubyGems in the release section; those lines are stale. Use Gradle and Maven Central as below.

## OpenAPI regen

`./generate.sh`:

1. Requires `../chat` and ~30GB free on Go cache / home / tmp (`MIN_FREE_GB`, default 30).
2. `make openapi` in chat, then `./build/chat-manager openapi generate-client --language java --spec ./releases/v2/serverside-api.yaml --output <this repo>`.
3. Webhook fixtures via `generate-webhook-fixtures` into `src/test/resources/fixtures/webhooks/`.
4. Optional `CallParticipant.java` Jackson duplicate-`Role` patch.
5. `./gradlew clean spotlessApply build -x test`.

Uses chat’s local `releases/v2/serverside-api.yaml`, not the protocol GitHub release (unlike getstream-go). Generator is internal.

Additive regen = **minor**. Do not invent a new major unless the public Java API actually breaks.

## CI

Workflows in `.github/workflows/`:

| File | Name | Trigger |
|---|---|---|
| `ci.yml` | Build (`🧪 Test & lint`) | pull_request; environment `ci` |
| `initiate_release.yml` | Create release PR | `workflow_dispatch` with `version` (`X.Y.Z`) |
| `release.yml` | Release | merged PR to `main` whose head branch starts with `release-` |
| `publish-new-version.yml` | Publish New Version | `workflow_dispatch` (publish existing `gradle.properties` as-is) |
| `scheduled_test.yml` | Scheduled tests | Monday 09:00 UTC + `workflow_dispatch`; retries `./gradlew test` 3 times |

Vars: `STREAM_BASE_URL`, `STREAM_API_KEY`.
Secrets: `STREAM_API_SECRET`, `GPG_KEY_CONTENTS`, `OSSRH_USERNAME`, `OSSRH_PASSWORD`, `SIGNING_KEY_ID`, `SIGNING_PASSWORD`, `SIGNING_SECRET_KEY_RING_FILE`, `SONATYPE_STAGING_PROFILE_ID`, `SLACK_NOTIFICATIONS_BOT_TOKEN`.

Known flakes: live Chat API 503 if the CI app is on a bad shard; scheduled tests retry 3 times.

## Release

Tags are **unprefixed** (`10.1.0`, not `v10.1.0`). Publish target is Maven Central (Sonatype).

Normal path:

1. Run workflow **Create release PR** (`initiate_release.yml`) with the new version (e.g. `10.1.0`).
2. It runs `standard-version`, writes `CHANGELOG.md` + `gradle.properties`, pushes `release-<version>`, opens a PR. Squash-merge that PR.
3. `release.yml` publishes with `./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository` and creates a GitHub release at tag `<version>`.

Manual / old-line publish: **Publish New Version** (`publish-new-version.yml`) on a chosen ref; does not bump version. `prerelease` defaults to true.

Pre-releases: push a tag like `1.0.0-beta.1`, draft a GitHub pre-release; CI publishes to Maven Central (`CONTRIBUTING.md`).

## PR conventions

Conventional commits for changelog generation (`CONTRIBUTING.md`). `ci.yml` commitlint step is commented out. Do not name a docs-only branch `release-*` or merge to `main` will try to publish.
