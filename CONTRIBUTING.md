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
# Run tests
./gradlew test

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

In order to release new version you need to be a maintainer of the library.

- Kick off a job called `initiate_release` ([link](https://github.com/GetStream/stream-sdk-java/actions/workflows/initiate_release.yml)).

The job creates a pull request with the changelog. Check if it looks good.

- Merge the pull request.

Once the PR is merged, it automatically kicks off another job which will upload the Gem to RubyGems.org and creates a GitHub release.
