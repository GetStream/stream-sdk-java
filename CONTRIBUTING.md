# :recycle: Contributing <!-- omit in toc -->

> [!NOTE]
> Most of the code in this repository is **automatically generated** from the [OpenAPI specification](https://getstream.github.io/protocol/) of the Stream API. Therefore, **it does NOT make sense to try to change them manually**.

Tests are written manually and more tests are always welcome.
Some parts like `lib/src/main/java/io/getstream/services/framework` and `lib/src/main/java/io/getstream/models/framework`, serve as the base and are written manually as well.

## Requirements <!-- omit in toc -->

To contribute to this project, you need to have [Lombok](https://projectlombok.org/) installed in your IDE.
You also need to use javac >= 11 to compile (ensure that your JAVA_HOME points to such a location), even if the produced library is compatible with Java 8 (for some reasons, jdk 8 compiler fails on some Lombok annotations).

- [Setup the project](#setup-the-project)
- [Architecture explanation](#architecture-explanation)
  - [Model classes](#model-classes)
  - [Request classes](#request-classes)
  - [RequestData and RequestObject classes](#requestdata-and-requestobject-classes)
  - [Response classes](#response-classes)
  - [Service interfaces](#service-interfaces)
  - [The StreamServiceGenerator class](#the-streamservicegenerator-class)
  - [The StreamResponse interface and related classes](#the-streamresponse-interface-and-related-classes)
  - [The StreamRequest and StreamServiceHandler classes](#the-streamrequest-and-streamservicehandler-classes)
  - [Test classes](#test-classes)
- [Code rules](#code-rules)
- [How to](#how-to)
  - [Enable logging](#enable-logging)
- [Commit message convention](#commit-message-convention)
- [Releasing a new version (for Stream developers)](#releasing-a-new-version-for-stream-developers)

## Setup the project

The project is a Maven project.

Copy `local.properties.example` to `local.properties` and put configuration there. These properties are automatically picked up during tests execution

```shell
# Run the unit tests
./gradlew test

# Run the tests that talk to a live Stream app
./gradlew integrationTest

# Check code style
./gradlew spotlessCheck

# Fix code style problems
./gradlew spotlessApply
```

## Architecture explanation

The code is composed of:

### Model classes

They are the data objects. They correspond to the formats of the objects contained in the API responses.

### Request classes

They are builders for API requests, and also contain methods to perform the calls.

### RequestData and RequestObject classes

They are data objects. They correspond to the formats of the API requests.

### Response classes

They are data objects. They correspond to the formats of the API responses.

### Service interfaces

They are the interfaces that define the API endpoints. 

### The StreamServiceGenerator class

This class is responsible to make the Service interfaces usable to call the API. It is used by all Request classes to define the `generateCall` method.
It also handles authentication and logging.

### The StreamResponse interface and related classes

StreamResponse is the interface that correspond to an API response. Most APIs responses are StreamResponseObject (mean they contain duration and rate limit data).

### The StreamRequest and StreamServiceHandler classes

StreamRequest is the generic Request class, that defines the `request` and `requestAsync` methods, which call the `StreamServiceHandler`.
The `StreamServiceHandler` class defines the synchronous and asynchronous processing. It also enriched the Response object with rate limit data when available.

### Test classes

They are organized by model. Each endpoint has at least one test related.

A test tagged `@Tag("integration")` talks to a live Stream app and needs credentials.
`BasicTest` carries the tag and JUnit inherits it, so extending that class is enough.
`./gradlew test` excludes them and needs no credentials, `./gradlew integrationTest` runs
only them.

CI follows the same split:

| When | What runs | Gates anything |
| --- | --- | --- |
| Pull request | `spotlessCheck` and `build` | yes, `🧪 Tests` is required on `main` |
| Daily at 10:00 UTC | `integrationTest` | no, a red run opens an issue |
| Release PR merged | nothing on the default branch, the unit lane on `N.x` | `N.x` only |

## Code rules

- The code should be formatted using Google formatter.
- All attributes, parameters and return values should be annotated with either `@Nullable` or `@NotNull`
- New implementations should follow the same principles as the existing ones (see how to section below)
- In Models, collections of submodel should be List (other collections and arrays are not supported by RequestObjectBuilder)
- String that only can take a given set of values should be represented as enums, with an `UNKNOWN` value marked `@JsonEnumDefaultValue` to avoid problem if the list of possible values changes

## How to

### Enable logging

Logging is enabled by default in tests. If you want to create a main class and activate logging, you should do the following:

```java
StreamServiceGenerator.logLevel = HttpLoggingInterceptor.Level.BODY;
```

## Commit message convention

This repository follows a commit message convention in order to automatically generate the [CHANGELOG](./CHANGELOG.md). Make sure you follow the rules of [conventional commits](https://www.conventionalcommits.org/) when opening a pull request.

## Regeneration (for Stream developers)

The client regenerates itself on every chat production release:

1. chat attaches its `openapi` generator to the release and publishes the matching `openapi-vX` spec release in [protocol](https://github.com/GetStream/protocol).
2. protocol notifies this repo, and the **Regenerate** workflow opens one PR from `regen/auto`, titled `feat: regenerate client from openapi-vX`. Its `BEGIN_COMMIT_OVERRIDE` block lists the changelog lines.
3. Once the required checks pass and the **Regen integration** run passes on the PR's head commit, the PR merges itself, and release-please adds those lines to the Release PR. Merging the Release PR ships, as described below.

A regen PR waits for a human when its changelog carries a `BREAKING CHANGE:` footer, when the `Breaking changes check` finds an approved breaking change in the new spec, or when a required check fails. For each approved breaking change with wire lines, the regen writes `BREAKING CHANGE:` footers from the lines chat allowlisted and titles the PR `feat!:`, so the check passes and the PR only waits for a human to merge it. A ticket chat approved for getstream-go only has no Java lines, so the check fails until a human names it in the PR body, also on a `feat!:` PR. Only `getstream-sdk-bot` can push to `regen/**`.

### Manual cases

- **Regenerate now:** run **Regenerate** from the Actions tab with an `openapi-vX` tag, or leave it empty for the latest. Locally, `SPEC_TAG=openapi-vX ./generate.sh` does the same with the generator built from a `../chat` checkout at that tag.
- **A breaking change that breaks this SDK:** when the regen titled the PR `feat!:`, review its footers. It moves on to each newer spec until you rename the PR or edit its body, which keeps it on the spec you judged. Otherwise add `!` to the PR title, and before `END_COMMIT_OVERRIDE` add a blank line and `BREAKING CHANGE: <what breaks for customers>`. Then merge it yourself in a breaking-change window, with a `MIGRATION_vX_to_vY.md` guide.
- **An approved breaking change that does not break this SDK:** label the PR `sdk-not-breaking` and name each ticket the check lists in the PR body. When the regen titled it `feat!:`, also drop the `!` and delete the entries that carry a `BREAKING CHANGE:` footer. The next Regenerate run merges it, daily at 06:23 UTC or when you start one.
- **Failed integration tests:** the tests share their Stream app with other SDKs' CI, so the Regenerate run reruns a red or cancelled **Regen integration** run once by itself, and merges the PR when the rerun passes. If the rerun is red too, fix the cause or rerun it again; a pass merges the PR. If no run started for the PR's head commit, start **Regen integration** on `regen/auto` from the Actions tab.
- **Rewrite a changelog line:** edit the `BEGIN_COMMIT_OVERRIDE` block in the merged PR's body, then run the **Release** workflow to rebuild the Release PR. Keep ticket IDs out of PR titles, because a title becomes a changelog line.

## Releasing a new version (for Stream developers)

Releases are driven by [release-please](https://github.com/googleapis/release-please).

- Merge PRs to `main` with conventional-commit titles, using **Squash and merge**. The
  title becomes the commit subject and decides the next version: `feat:` is a minor,
  `fix:` and `perf:` are a patch, `feat!:` or `<type>(scope)!:` is a major. Other types
  (`chore`, `ci`, `docs`, `test`, `refactor`) ship nothing. This is new: the version used
  to be typed by hand into the `initiate_release` job, which is gone.
- release-please keeps a Release PR open with the version bump in `gradle.properties`
  and `CHANGELOG.md`. Never edit the version by hand, and leave the
  `x-release-please-start-version` comments around it in place; a `.properties` file
  takes a trailing comment as part of the value, so the marker has to bracket the line.
- Its checks sit Pending until someone clicks **Approve and run**, because a PR opened
  with `GITHUB_TOKEN` starts no workflow runs. After that the unit lane reports `skipped`
  and `🧪 Tests` goes green in seconds without running a test. The skip only applies while the diff is nothing but what release-please writes, down to the version line in each version file, so a code or dependency change pushed onto a Release PR by hand runs the unit lane like any other PR.
- Merging creates the tag and the GitHub Release on the merge commit and publishes to Maven Central, with no further test run: the Release PR adds only the version bump and changelog to an already-tested `main`. A hotfix release from `N.x` runs the unit lane first, since its commits were pushed without a PR. A tag, a GitHub Release and a Maven Central push cannot be withdrawn. The publish step builds the project, so a build that does not compile fails there after the tag exists; the fix ships under the next version, since `publish_tag` rebuilds the same tag.

Tags here have no `v` prefix (`10.1.1`, not `v10.1.1`), which `include-v-in-tag: false`
in `release-please-config.json` preserves.

To retry a publish that failed after the release was tagged, use "Re-run failed jobs" on
that workflow run. Once GitHub has retired the run, dispatch `Release` from `main` with
`publish_tag` set to the tag, which builds and publishes that tag without touching
release-please. If the release job fails after the Release PR merged, the release stays
pending and every later push logs a warning naming the commit to go back to.

To force a specific version, type `Release-As: X.Y.Z` in the commit message box of the
squash dialog when merging a PR; the PR description is not copied there.

