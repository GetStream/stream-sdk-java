package io.getstream.exceptions;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;

/**
 * Thrown when a network-layer failure prevents the SDK from receiving an HTTP response.
 * Carries an {@link #getErrorType()} string matching the logging spec §6.4 enum:
 * {@code connection_reset}, {@code timeout}, {@code dns_failure},
 * {@code tls_handshake_failed}, {@code unknown}. Per CHA-2958 §5.3 / §6.1.
 *
 * <p>The original transport error is preserved on the cause chain ({@link Throwable#getCause()}).
 */
public class StreamTransportException extends StreamException {
  private static final long serialVersionUID = 1L;

  public static final String CONNECTION_RESET = "connection_reset";
  public static final String TIMEOUT = "timeout";
  public static final String DNS_FAILURE = "dns_failure";
  public static final String TLS_HANDSHAKE_FAILED = "tls_handshake_failed";
  public static final String UNKNOWN = "unknown";

  private final String errorType;

  public StreamTransportException(String errorType, String message, Throwable cause) {
    super(message, cause);
    this.errorType = errorType;
  }

  public StreamTransportException(String errorType, Throwable cause) {
    super(cause);
    this.errorType = errorType;
  }

  public String getErrorType() {
    return errorType;
  }

  /**
   * Classifies an {@link IOException} from the HTTP client into one of the {@code errorType}
   * enum values. The OkHttp call-timeout path throws {@link InterruptedIOException} with the
   * message "timeout", so that is mapped to {@link #TIMEOUT} alongside
   * {@link SocketTimeoutException}.
   */
  public static StreamTransportException fromIOException(IOException e) {
    String type = classify(e);
    return new StreamTransportException(type, e.getMessage(), e);
  }

  private static String classify(IOException e) {
    // TLS first: SSLException is a subclass of IOException, and SSLHandshakeException is a
    // subclass of SSLException — both flow through here.
    if (e instanceof SSLException) {
      return TLS_HANDSHAKE_FAILED;
    }
    if (e instanceof UnknownHostException) {
      return DNS_FAILURE;
    }
    if (e instanceof SocketTimeoutException) {
      return TIMEOUT;
    }
    // OkHttp's call-timeout path raises a plain InterruptedIOException with message "timeout".
    if (e instanceof InterruptedIOException) {
      return TIMEOUT;
    }
    if (e instanceof ConnectException || e instanceof NoRouteToHostException) {
      return CONNECTION_RESET;
    }
    // "Connection reset" / "Broken pipe" surface as a vanilla IOException with that message on
    // some JVMs. Match by message as a last-resort heuristic before falling through to unknown.
    String msg = e.getMessage();
    if (msg != null) {
      String lower = msg.toLowerCase();
      if (lower.contains("connection reset")
          || lower.contains("connection refused")
          || lower.contains("connection closed")
          || lower.contains("broken pipe")) {
        return CONNECTION_RESET;
      }
      if (lower.contains("timeout") || lower.contains("timed out")) {
        return TIMEOUT;
      }
    }
    return UNKNOWN;
  }
}
