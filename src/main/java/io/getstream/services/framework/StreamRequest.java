package io.getstream.services.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.exceptions.StreamException;
import io.getstream.models.UploadFileRequest;
import io.getstream.models.UploadImageRequest;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

public class StreamRequest<T> {
  private final OkHttpClient client;
  private final Request request;
  private final ObjectMapper objectMapper;
  private final TypeReference<T> typeReference;

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
      } else {
        rawBody = RequestBody.create(objectMapper.writeValueAsBytes(jRequest));
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

  public StreamResponse<T> execute() throws StreamException {
    okhttp3.Call call = client.newCall(request);
    Response response;
    try {
      response = call.execute();
    } catch (IOException e) {
      throw StreamException.build(e);
    }

    return this.parseResponse(response);
  }

  private StreamResponse<T> parseResponse(okhttp3.Response response) throws StreamException {
    if (!response.isSuccessful()) {
      throw StreamException.build(response);
    }
    ResponseBody rawBody = response.body();
    // unmarshal the response body to the expected type using jackson
    T result;
    try {
      result = objectMapper.readValue(rawBody.string(), typeReference);
    } catch (Throwable e) {
      throw StreamException.build(e);
    }

    StreamResponse<T> streamResponse = new StreamResponse<>();
    streamResponse.setData(result);

    RateLimit rateLimit = getRateLimit(response);
    streamResponse.setRateLimit(rateLimit);

    return streamResponse;
  }
}
