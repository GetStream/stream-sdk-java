package io.getstream.services.framework;

import java.time.Duration;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Tunables for the SDK's HTTP transport / connection pool. Per CHA-2956 connection pooling spec.
 * Defaults: 5 conns/host, 55s idle, 10s connect, 30s request. HTTP keep-alive is always on. {@link
 * #setHttpClient(OkHttpClient)} is the §7 escape hatch — when set, the four knobs are ignored.
 */
public class StreamClientOptions {
  public static final int DEFAULT_MAX_CONNS_PER_HOST = 5;
  public static final Duration DEFAULT_IDLE_TIMEOUT = Duration.ofSeconds(55);
  public static final Duration DEFAULT_CONNECT_TIMEOUT = Duration.ofSeconds(10);
  public static final Duration DEFAULT_REQUEST_TIMEOUT = Duration.ofSeconds(30);

  private int maxConnsPerHost = DEFAULT_MAX_CONNS_PER_HOST;
  @NotNull private Duration idleTimeout = DEFAULT_IDLE_TIMEOUT;
  @NotNull private Duration connectTimeout = DEFAULT_CONNECT_TIMEOUT;
  @NotNull private Duration requestTimeout = DEFAULT_REQUEST_TIMEOUT;
  @Nullable private OkHttpClient httpClient;

  public StreamClientOptions setMaxConnsPerHost(int n) {
    if (n <= 0) throw new IllegalArgumentException("maxConnsPerHost must be > 0, got " + n);
    this.maxConnsPerHost = n;
    return this;
  }

  public StreamClientOptions setIdleTimeout(@NotNull Duration d) {
    if (d.isNegative() || d.isZero())
      throw new IllegalArgumentException("idleTimeout must be positive, got " + d);
    this.idleTimeout = d;
    return this;
  }

  public StreamClientOptions setConnectTimeout(@NotNull Duration d) {
    if (d.isNegative() || d.isZero())
      throw new IllegalArgumentException("connectTimeout must be positive, got " + d);
    this.connectTimeout = d;
    return this;
  }

  public StreamClientOptions setRequestTimeout(@NotNull Duration d) {
    if (d.isNegative() || d.isZero())
      throw new IllegalArgumentException("requestTimeout must be positive, got " + d);
    this.requestTimeout = d;
    return this;
  }

  /** Spec §7 escape hatch. When set, the four knobs above are ignored. */
  public StreamClientOptions setHttpClient(@Nullable OkHttpClient client) {
    this.httpClient = client;
    return this;
  }

  public int getMaxConnsPerHost() {
    return maxConnsPerHost;
  }

  @NotNull
  public Duration getIdleTimeout() {
    return idleTimeout;
  }

  @NotNull
  public Duration getConnectTimeout() {
    return connectTimeout;
  }

  @NotNull
  public Duration getRequestTimeout() {
    return requestTimeout;
  }

  @Nullable
  public OkHttpClient getHttpClient() {
    return httpClient;
  }

  public boolean hasUserHttpClient() {
    return httpClient != null;
  }
}
