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
| Daily at 09:00 UTC | `integrationTest` | no, a red run opens an issue |
| Push to `main` with a release pending | both | only the unit half gates the tag |

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
  and `🧪 Tests` goes green in seconds without running a test. **Update branch** does not
  bring the suite back, and neither does pushing a commit by hand, so a commit pushed onto
  a Release PR reaches `main` untested.
- Merging runs `spotlessCheck` and the build on the merge commit, which is the commit the
  tag will point at. Only if that is green does the workflow create the tag and the GitHub
  Release and publish to Maven Central. The order matters: a tag, a GitHub Release and a
  Maven Central push cannot be withdrawn. Integration tests are advisory and gate none of
  it.

Tags here have no `v` prefix (`10.1.1`, not `v10.1.1`), which `include-v-in-tag: false`
in `release-please-config.json` preserves.

To retry a publish that failed after the release was tagged, use "Re-run failed jobs" on
that workflow run. Once GitHub has retired the run, dispatch `Release` from `main` with
`publish_tag` set to the tag, which builds and publishes that tag without touching
release-please. If the suite goes red after the Release PR merged, the release stays
pending and every later push logs a warning naming the commit to go back to.

To force a specific version, type `Release-As: X.Y.Z` in the commit message box of the
squash dialog when merging a PR; the PR description is not copied there.

