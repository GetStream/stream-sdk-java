package io.getstream.exceptions;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.jetbrains.annotations.Nullable;

/**
 * Parses the HTTP {@code Retry-After} header per RFC 7231 §7.1.3.
 *
 * <p>Accepts either non-negative integer seconds (e.g. {@code 30}) or an HTTP-date (IMF-fixdate,
 * e.g. {@code Fri, 31 Dec 2026 23:59:59 GMT}). HTTP-date values are converted to a delta from
 * {@code now()}, clamped to {@code >= 0}. Returns {@code null} when the header is absent or
 * unparseable — graceful per CHA-2958 §7.
 */
final class RetryAfterParser {
  private RetryAfterParser() {}

  @Nullable
  static Duration parse(@Nullable String header) {
    if (header == null) {
      return null;
    }
    String trimmed = header.trim();
    if (trimmed.isEmpty()) {
      return null;
    }
    // Integer seconds path.
    try {
      long seconds = Long.parseLong(trimmed);
      if (seconds < 0) {
        return Duration.ZERO;
      }
      return Duration.ofSeconds(seconds);
    } catch (NumberFormatException ignored) {
      // Fall through to HTTP-date parsing.
    }
    // HTTP-date (IMF-fixdate). RFC 7231 §7.1.1.1 says HTTP/1.1 servers MUST generate this form.
    try {
      ZonedDateTime when = ZonedDateTime.parse(trimmed, DateTimeFormatter.RFC_1123_DATE_TIME);
      Duration delta = Duration.between(ZonedDateTime.now(when.getZone()), when);
      return delta.isNegative() ? Duration.ZERO : delta;
    } catch (DateTimeParseException ignored) {
      return null;
    }
  }
}
