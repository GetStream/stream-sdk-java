package io.getstream.services.framework;

import java.time.Duration;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.helpers.NOPLogger;

/**
 * Tunables for the SDK's HTTP transport / connection pool. Per CHA-2956. Defaults: 5 conns/host,
 * 55s idle, 10s connect, 30s request. HTTP keep-alive is always on. {@link
 * #setHttpClient(OkHttpClient)} is the escape hatch: when set, the four knobs are ignored.
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
  @Nullable private Logger logger;
  private boolean logBodies = false;

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

  /** Escape hatch: when set, the four knobs above are ignored. */
  public StreamClientOptions setHttpClient(@Nullable OkHttpClient client) {
    this.httpClient = client;
    return this;
  }

  /**
   * Inject an SLF4J {@link Logger} for the SDK's structured log events. When unset, the SDK logs to
   * a no-op logger. The SDK never sets the logger's level.
   */
  public StreamClientOptions setLogger(@Nullable Logger logger) {
    this.logger = logger;
    return this;
  }

  /**
   * Opt in to logging HTTP request/response bodies on the debug events. Secret body keys are still
   * redacted. Off by default; enabling it emits a one-time warning at client construction.
   */
  public StreamClientOptions setLogBodies(boolean logBodies) {
    this.logBodies = logBodies;
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

  @Nullable
  public Logger getLogger() {
    return logger;
  }

  @NotNull
  public Logger getLoggerOrNop() {
    return logger != null ? logger : NOPLogger.NOP_LOGGER;
  }

  public boolean getLogBodies() {
    return logBodies;
  }
}
