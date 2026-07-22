package io.getstream.services.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.exceptions.StreamException;
import io.getstream.exceptions.StreamTransportException;
import io.getstream.models.UploadChannelFileRequest;
import io.getstream.models.UploadChannelImageRequest;
import io.getstream.models.UploadFileRequest;
import io.getstream.models.UploadImageRequest;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.helpers.NOPLogger;

public class StreamRequest<T> {
  private final OkHttpClient client;
  private final Request request;
  private final ObjectMapper objectMapper;
  private final TypeReference<T> typeReference;
  private Duration callTimeoutOverride;
  private Logger logger = NOPLogger.NOP_LOGGER;
  private boolean logBodies = false;
  // Serialized JSON request body captured at construction so logRequestSent can emit it (redacted)
  // without re-reading the OkHttp RequestBody. Null for GET/DELETE/multipart uploads.
  private String requestBodyJson;

  /**
   * Preferred constructor: derives the transport, base URL, logger and log-bodies flag from {@code
   * client}, so the request emits the SDK's structured log events.
   */
  public StreamRequest(
      StreamHTTPClient client,
      String method,
      String path,
      Object jRequest,
      Map<String, String> pathParams,
      TypeReference<T> typeReference)
      throws StreamException {
    this(
        client.getHttpClient(),
        client.getObjectMapper(),
        client.getBaseUrl(),
        method,
        path,
        jRequest,
        pathParams,
        typeReference);
    this.logger = client.getLogger();
    this.logBodies = client.getLogBodies();
  }

  public StreamRequest(
      OkHttpClient client,
      ObjectMapper mapper,
      String baseURL,
      String method,
      String path,
      Object jRequest,
      Map<String, String> pathParams,
      TypeReference<T> typeReference)
      throws StreamException {
    this.objectMapper = mapper;
    this.client = client;
    this.typeReference = typeReference;

    Request request;
    try {
      RequestBody rawBody;
      if (List.of("GET", "DELETE", "HEAD", "OPTIONS").contains(method) || jRequest == null) {
        rawBody = null;
      } else if (jRequest instanceof UploadFileRequest) {
        rawBody = createMultipartBody((UploadFileRequest) jRequest);
      } else if (jRequest instanceof UploadImageRequest) {
        rawBody = createMultipartBody((UploadImageRequest) jRequest);
      } else if (jRequest instanceof UploadChannelFileRequest) {
        rawBody = createMultipartBody((UploadChannelFileRequest) jRequest);
      } else if (jRequest instanceof UploadChannelImageRequest) {
        rawBody = createMultipartBody((UploadChannelImageRequest) jRequest);
      } else {
        byte[] bodyBytes = objectMapper.writeValueAsBytes(jRequest);
        this.requestBodyJson = new String(bodyBytes, StandardCharsets.UTF_8);
        rawBody = RequestBody.create(bodyBytes);
      }
      request =
          new Request.Builder()
              .url(buildUrl(baseURL, path, pathParams, jRequest))
              .method(method, rawBody)
              .build();
    } catch (Throwable e) {
      throw new StreamException(e);
    }

    this.request = request;
  }

  @NotNull
  private static RateLimit getRateLimit(Response response) {
    Headers headers = response.headers();
    RateLimit rateLimit = new RateLimit();

    var header = headers.get("X-Ratelimit-Limit");
    if (header != null) {
      rateLimit.setLimit(Integer.parseInt(header));
    }

    header = headers.get("X-Ratelimit-Remaining");
    if (header != null) {
      rateLimit.setRemaining(Integer.parseInt(header));
    }

    header = headers.get("X-Ratelimit-Reset");
    if (header != null) {
      rateLimit.setReset(new Date(Long.parseLong(header) * 1000));
    }
    return rateLimit;
  }

  public HttpUrl buildUrl(
      String baseUrl, String path, Map<String, String> pathParams, Object queryParams)
      throws JsonProcessingException, NullPointerException, IllegalAccessException {
    // Handle path parameters
    if (pathParams != null && !pathParams.isEmpty()) {
      for (Map.Entry<String, String> entry : pathParams.entrySet()) {
        path = path.replace("{" + entry.getKey() + "}", entry.getValue());
      }
    }

    // Add the processed path
    // Remove leading slash if present to avoid double slashes
    String processedPath = path.startsWith("/") ? path.substring(1) : path;

    // Start building with the base URL
    HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder(processedPath);

    // Add query parameters
    if (queryParams != null) {
      Map<String, String> queryMap = QueryConverter.getQueryParameters(queryParams, objectMapper);
      for (Map.Entry<String, String> entry : queryMap.entrySet()) {
        urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
      }
    }

    return urlBuilder.build();
  }

  private RequestBody createMultipartBody(UploadFileRequest request) throws IOException {
    if (request.getFile() == null || request.getFile().isEmpty()) {
      throw new IllegalArgumentException("File path must be provided");
    }

    File file = new File(request.getFile());
    if (!file.exists()) {
      throw new IOException("File not found: " + request.getFile());
    }

    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

    // Add file
    RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/octet-stream"));
    builder.addFormDataPart("file", file.getName(), fileBody);

    // Add user field if present
    if (request.getUser() != null) {
      String userJson = objectMapper.writeValueAsString(request.getUser());
      builder.addFormDataPart("user", userJson);
    }

    return builder.build();
  }

  private RequestBody createMultipartBody(UploadImageRequest request) throws IOException {
    if (request.getFile() == null || request.getFile().isEmpty()) {
      throw new IllegalArgumentException("File path must be provided");
    }

    File file = new File(request.getFile());
    if (!file.exists()) {
      throw new IOException("File not found: " + request.getFile());
    }

    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

    // Add file
    RequestBody fileBody = RequestBody.create(file, MediaType.parse("image/*"));
    builder.addFormDataPart("file", file.getName(), fileBody);

    // Add upload_sizes field if present
    if (request.getUploadSizes() != null && !request.getUploadSizes().isEmpty()) {
      String uploadSizesJson = objectMapper.writeValueAsString(request.getUploadSizes());
      builder.addFormDataPart("upload_sizes", uploadSizesJson);
    }

    // Add user field if present
    if (request.getUser() != null) {
      String userJson = objectMapper.writeValueAsString(request.getUser());
      builder.addFormDataPart("user", userJson);
    }

    return builder.build();
  }

  private RequestBody createMultipartBody(UploadChannelFileRequest request) throws IOException {
    if (request.getFile() == null || request.getFile().isEmpty()) {
      throw new IllegalArgumentException("File path must be provided");
    }

    File file = new File(request.getFile());
    if (!file.exists()) {
      throw new IOException("File not found: " + request.getFile());
    }

    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

    RequestBody fileBody = RequestBody.create(file, MediaType.parse("application/octet-stream"));
    builder.addFormDataPart("file", file.getName(), fileBody);

    if (request.getUser() != null) {
      String userJson = objectMapper.writeValueAsString(request.getUser());
      builder.addFormDataPart("user", userJson);
    }

    return builder.build();
  }

  private RequestBody createMultipartBody(UploadChannelImageRequest request) throws IOException {
    if (request.getFile() == null || request.getFile().isEmpty()) {
      throw new IllegalArgumentException("File path must be provided");
    }

    File file = new File(request.getFile());
    if (!file.exists()) {
      throw new IOException("File not found: " + request.getFile());
    }

    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

    RequestBody fileBody = RequestBody.create(file, MediaType.parse("image/*"));
    builder.addFormDataPart("file", file.getName(), fileBody);

    if (request.getUploadSizes() != null && !request.getUploadSizes().isEmpty()) {
      String uploadSizesJson = objectMapper.writeValueAsString(request.getUploadSizes());
      builder.addFormDataPart("upload_sizes", uploadSizesJson);
    }

    if (request.getUser() != null) {
      String userJson = objectMapper.writeValueAsString(request.getUser());
      builder.addFormDataPart("user", userJson);
    }

    return builder.build();
  }

  /**
   * Override the per-call timeout for this single request. The override takes precedence over the
   * client-wide {@code RequestTimeout} configured via {@link
   * io.getstream.services.framework.StreamClientOptions#setRequestTimeout}. Returns {@code this}
   * for chaining.
   *
   * <p>Per CHA-2956.
   */
  public StreamRequest<T> callTimeout(@NotNull Duration d) {
    if (d.isNegative()) {
      throw new IllegalArgumentException("callTimeout must be non-negative, got " + d);
    }
    this.callTimeoutOverride = d;
    return this;
  }

  public StreamResponse<T> execute() throws StreamException {
    okhttp3.Call call = client.newCall(request);
    if (callTimeoutOverride != null) {
      // OkHttp 4.x: Call.timeout() returns an okio.Timeout. Setting it overrides the client-wide
      // callTimeout for this single dispatch.
      call.timeout().timeout(callTimeoutOverride.toNanos(), TimeUnit.NANOSECONDS);
    }
    logRequestSent();
    long startNanos = System.nanoTime();
    Response response;
    try {
      response = call.execute();
    } catch (IOException e) {
      // Transport failure: no HTTP response was received. Classify, log ERROR, then re-throw.
      StreamTransportException transportException = StreamTransportException.fromIOException(e);
      logger.error(
          "http.request.failed http.request.method={} url.path={} url.query={} error.type={}"
              + " error.message={} duration_ms={}",
          request.method(),
          request.url().encodedPath(),
          LogRedaction.redactQuery(request.url()),
          transportException.getErrorType(),
          e.getMessage(),
          elapsedMs(startNanos));
      throw transportException;
    }

    logResponseReceived(response, elapsedMs(startNanos));
    return this.parseResponse(response);
  }

  private static long elapsedMs(long startNanos) {
    return (System.nanoTime() - startNanos) / 1_000_000;
  }

  private void logRequestSent() {
    if (logBodies && requestBodyJson != null) {
      logger.debug(
          "http.request.sent http.request.method={} url.path={} url.query={} http.request.body={}",
          request.method(),
          request.url().encodedPath(),
          LogRedaction.redactQuery(request.url()),
          LogRedaction.redactJsonBody(requestBodyJson));
    } else {
      logger.debug(
          "http.request.sent http.request.method={} url.path={} url.query={}",
          request.method(),
          request.url().encodedPath(),
          LogRedaction.redactQuery(request.url()));
    }
  }

  private void logResponseReceived(Response response, long durationMs) {
    long bodySize = response.body() != null ? response.body().contentLength() : -1;
    if (logBodies) {
      String body;
      try {
        // peekBody copies up to the limit without consuming the real body that parseResponse reads.
        body = LogRedaction.redactJsonBody(response.peekBody(1_048_576).string());
      } catch (IOException e) {
        body = "";
      }
      logger.debug(
          "http.response.received http.request.method={} url.path={} http.response.status_code={}"
              + " http.response.body.size={} duration_ms={} http.response.body={}",
          request.method(),
          request.url().encodedPath(),
          response.code(),
          bodySize,
          durationMs,
          body);
    } else {
      logger.debug(
          "http.response.received http.request.method={} url.path={} http.response.status_code={}"
              + " http.response.body.size={} duration_ms={}",
          request.method(),
          request.url().encodedPath(),
          response.code(),
          bodySize,
          durationMs);
    }
  }

  private StreamResponse<T> parseResponse(okhttp3.Response response) throws StreamException {
    if (!response.isSuccessful()) {
      // 4xx/5xx → StreamApiException (StreamRateLimitException for 429).
      throw StreamException.build(response);
    }
    ResponseBody rawBody = response.body();
    // unmarshal the response body to the expected type using jackson
    String bodyStr;
    try {
      bodyStr = rawBody.string();
    } catch (IOException e) {
      // Body read failure is transport, not parse.
      throw StreamTransportException.fromIOException(e);
    }
    T result;
    try {
      result = objectMapper.readValue(bodyStr, typeReference);
    } catch (Throwable e) {
      throw new StreamException("failed to parse response body", e);
    }

    StreamResponse<T> streamResponse = new StreamResponse<>();
    streamResponse.setData(result);

    RateLimit rateLimit = getRateLimit(response);
    streamResponse.setRateLimit(rateLimit);

    return streamResponse;
  }
}
