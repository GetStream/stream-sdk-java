package io.getstream.services.framework;

import java.time.Duration;
import org.jetbrains.annotations.NotNull;

/**
 * Opt-in auto-retry policy. Disabled by default: the client performs exactly one attempt and
 * surfaces errors unchanged. When enabled, only GET/HEAD requests failing with HTTP 429 or a
 * transport error are retried, and never when the backend marked the error unrecoverable.
 */
public class RetryConfig {
  public static final int DEFAULT_MAX_ATTEMPTS = 3;
  public static final Duration DEFAULT_MAX_BACKOFF = Duration.ofSeconds(30);

  private boolean enabled = false;
  private int maxAttempts = DEFAULT_MAX_ATTEMPTS;
  @NotNull private Duration maxBackoff = DEFAULT_MAX_BACKOFF;

  public RetryConfig setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public RetryConfig setMaxAttempts(int n) {
    if (n < 1) throw new IllegalArgumentException("maxAttempts must be >= 1, got " + n);
    this.maxAttempts = n;
    return this;
  }

  public RetryConfig setMaxBackoff(@NotNull Duration d) {
    if (d.isNegative()) throw new IllegalArgumentException("maxBackoff must be >= 0, got " + d);
    this.maxBackoff = d;
    return this;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public int getMaxAttempts() {
    return maxAttempts;
  }

  @NotNull
  public Duration getMaxBackoff() {
    return maxBackoff;
  }
}
