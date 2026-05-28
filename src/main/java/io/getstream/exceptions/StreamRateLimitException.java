package io.getstream.exceptions;

import java.time.Duration;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * Thrown when the Stream API returns HTTP 429. Subclass of {@link StreamApiException} that
 * additionally carries the parsed {@code Retry-After} header (RFC 7231 §7.1.3 — integer seconds or
 * HTTP-date). Per CHA-2958 §5.2.
 */
public class StreamRateLimitException extends StreamApiException {
  private static final long serialVersionUID = 1L;

  @Nullable private final Duration retryAfter;

  public StreamRateLimitException(
      String message,
      int statusCode,
      int code,
      @Nullable Map<String, String> exceptionFields,
      boolean unrecoverable,
      String rawResponseBody,
      @Nullable String moreInfo,
      @Nullable Object details,
      @Nullable Duration retryAfter,
      @Nullable Throwable cause) {
    super(
        message,
        statusCode,
        code,
        exceptionFields,
        unrecoverable,
        rawResponseBody,
        moreInfo,
        details,
        cause);
    this.retryAfter = retryAfter;
  }

  /**
   * Returns the {@code Retry-After} delta if the server sent a parseable value. {@code null} when
   * the header was absent or unparseable.
   */
  @Nullable
  public Duration getRetryAfter() {
    return retryAfter;
  }
}
