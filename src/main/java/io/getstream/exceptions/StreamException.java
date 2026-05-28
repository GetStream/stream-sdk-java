package io.getstream.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class StreamException extends Exception {
  private static final long serialVersionUID = 1L;

  @Getter private ResponseData responseData;

  public StreamException(String message, ResponseData responseData) {
    super(message);
    this.responseData = responseData;
  }

  public StreamException(String message, Throwable t) {
    super(message, t);
  }

  public StreamException(Throwable t) {
    super(t);
  }

  // Allows subclasses (StreamApiException) to set both the cause and the back-compat responseData
  // mirror in a single super() call. Package-private: only the exceptions package builds these.
  StreamException(String message, Throwable cause, ResponseData responseData) {
    super(message, cause);
    this.responseData = responseData;
  }

  /**
   * Builds a StreamException to signal an issue
   *
   * @param issue the issue
   * @return the StreamException
   */
  public static StreamException build(String issue) {
    return new StreamException(issue, (Throwable) null);
  }

  /**
   * Builds a typed API exception from the Stream API error body.
   *
   * <p>Per CHA-2958 §6.2: parseable {@code APIError} envelope → {@link StreamApiException};
   * unparseable body but HTTP layer succeeded → {@code StreamApiException} with {@code code=0}
   * and the raw body preserved (§6.3).
   *
   * <p>This overload does not see the status code; callers that have a {@link Response} should
   * prefer {@link #build(Response)} so 429 is routed to {@link StreamRateLimitException} and
   * {@code statusCode} is preserved.
   */
  public static StreamException build(ResponseBody responseBody) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try {
      String responseBodyString = responseBody.string();
      try {
        ResponseData responseData = objectMapper.readValue(responseBodyString, ResponseData.class);
        int status = responseData.getStatusCode() != null ? responseData.getStatusCode() : 0;
        return apiExceptionFromResponseData(responseData, responseBodyString, status, null);
      } catch (JsonProcessingException e) {
        return new StreamApiException(
            "failed to parse error response",
            0,
            0,
            null,
            false,
            responseBodyString,
            null,
            null,
            e);
      }
    } catch (IOException e) {
      return new StreamException(e);
    }
  }

  /**
   * Builds a typed API exception from an HTTP response. Per CHA-2958 §6.2: 429 → {@link
   * StreamRateLimitException} with {@code Retry-After} parsed per RFC 7231 §7.1.3 (integer
   * seconds or HTTP-date). Other 4xx/5xx → {@link StreamApiException}.
   */
  public static StreamException build(Response httpResponse) {
    int status = httpResponse.code();
    String bodyString = "";
    ResponseData parsed = null;
    Throwable parseCause = null;

    ResponseBody errorBody = httpResponse.body();
    if (errorBody != null) {
      try {
        bodyString = errorBody.string();
      } catch (IOException e) {
        // Body unreadable — treat as empty.
        parseCause = e;
      }
      if (!bodyString.isEmpty()) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
          parsed = objectMapper.readValue(bodyString, ResponseData.class);
        } catch (JsonProcessingException e) {
          parseCause = e;
        }
      }
    }

    if (parsed == null) {
      String msg =
          parseCause != null
              ? "failed to parse error response"
              : String.format("Unexpected server response code %d", status);
      if (status == 429) {
        return new StreamRateLimitException(
            msg, status, 0, null, false, bodyString, null, null,
            RetryAfterParser.parse(httpResponse.header("Retry-After")), parseCause);
      }
      return new StreamApiException(msg, status, 0, null, false, bodyString, null, null, parseCause);
    }

    if (status == 429) {
      return new StreamRateLimitException(
          parsed.getMessage() != null ? parsed.getMessage() : "rate limited",
          status,
          parsed.getCode() != null ? parsed.getCode() : 0,
          parsed.getExceptionFields(),
          parsed.getUnrecoverable() != null ? parsed.getUnrecoverable() : false,
          bodyString,
          parsed.getMoreInfo(),
          parsed.getDetails(),
          RetryAfterParser.parse(httpResponse.header("Retry-After")),
          null);
    }
    return apiExceptionFromResponseData(parsed, bodyString, status, null);
  }

  /**
   * Builds a StreamException when an exception occurs calling the API.
   *
   * <p>Historic factory preserved for back-compat. The HTTP-call path now classifies transport
   * failures directly via {@link StreamTransportException#fromIOException(IOException)} so this
   * factory no longer auto-routes; it simply wraps the cause.
   *
   * @param t the underlying exception
   * @return the StreamException
   */
  public static StreamException build(Throwable t) {
    return new StreamException(t);
  }

  // statusCode comes from the HTTP layer (§5.1: "Source: HTTP status"). The envelope's
  // StatusCode is only used as a fallback by build(ResponseBody), which has no live response.
  private static StreamApiException apiExceptionFromResponseData(
      ResponseData rd, String rawBody, int statusCode, Throwable cause) {
    return new StreamApiException(
        rd.getMessage() != null ? rd.getMessage() : "",
        statusCode,
        rd.getCode() != null ? rd.getCode() : 0,
        rd.getExceptionFields(),
        rd.getUnrecoverable() != null ? rd.getUnrecoverable() : false,
        rawBody != null ? rawBody : "",
        rd.getMoreInfo(),
        rd.getDetails(),
        cause);
  }

  @Data
  public static class ResponseData {
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("exception_fields")
    private Map<String, String> exceptionFields;

    @JsonProperty("StatusCode")
    private Integer statusCode;

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("more_info")
    private String moreInfo;

    @JsonProperty("details")
    private Object details;

    @JsonProperty("unrecoverable")
    private Boolean unrecoverable;
  }
}
