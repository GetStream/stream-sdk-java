package io.getstream.exceptions;

import java.util.Collections;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * Thrown when the Stream API returns a 4xx/5xx response. Carries the parsed {@code APIError}
 * envelope per CHA-2958 §5.1.
 *
 * <p>Checked subclass of {@link StreamException}: existing {@code throws StreamException}
 * declarations continue to compile.
 */
public class StreamApiException extends StreamException {
  private static final long serialVersionUID = 1L;

  private final int statusCode;
  private final int code;
  private final Map<String, String> exceptionFields;
  private final boolean unrecoverable;
  private final String rawResponseBody;
  @Nullable private final String moreInfo;
  @Nullable private final Object details;

  public StreamApiException(
      String message,
      int statusCode,
      int code,
      @Nullable Map<String, String> exceptionFields,
      boolean unrecoverable,
      String rawResponseBody,
      @Nullable String moreInfo,
      @Nullable Object details,
      @Nullable Throwable cause) {
    super(
        message,
        cause,
        buildResponseData(
            statusCode,
            code,
            message,
            exceptionFields,
            unrecoverable,
            rawResponseBody,
            moreInfo,
            details));
    this.statusCode = statusCode;
    this.code = code;
    this.exceptionFields = exceptionFields != null ? exceptionFields : Collections.emptyMap();
    this.unrecoverable = unrecoverable;
    this.rawResponseBody = rawResponseBody != null ? rawResponseBody : "";
    this.moreInfo = moreInfo;
    this.details = details;
  }

  private static ResponseData buildResponseData(
      int statusCode,
      int code,
      String message,
      @Nullable Map<String, String> exceptionFields,
      boolean unrecoverable,
      String rawResponseBody,
      @Nullable String moreInfo,
      @Nullable Object details) {
    ResponseData rd = new ResponseData();
    rd.setStatusCode(statusCode);
    rd.setCode(code);
    rd.setMessage(message);
    rd.setExceptionFields(exceptionFields);
    rd.setUnrecoverable(unrecoverable);
    rd.setMoreInfo(moreInfo);
    rd.setDetails(details);
    return rd;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public int getCode() {
    return code;
  }

  public Map<String, String> getExceptionFields() {
    return exceptionFields;
  }

  public boolean isUnrecoverable() {
    return unrecoverable;
  }

  public String getRawResponseBody() {
    return rawResponseBody;
  }

  @Nullable
  public String getMoreInfo() {
    return moreInfo;
  }

  @Nullable
  public Object getDetails() {
    return details;
  }
}
