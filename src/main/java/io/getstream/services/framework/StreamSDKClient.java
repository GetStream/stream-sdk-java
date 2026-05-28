package io.getstream.services.framework;

import io.getstream.exceptions.StreamException;
import io.getstream.exceptions.StreamTaskException;
import io.getstream.exceptions.StreamTransportException;
import io.getstream.models.ErrorResult;
import io.getstream.models.GetTaskResponse;
import io.getstream.services.*;
import java.time.Duration;
import java.util.Properties;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

public class StreamSDKClient extends CommonImpl implements Common {
  StreamHTTPClient httpClient;

  public StreamSDKClient(@NotNull String apiKey, @NotNull String apiSecret) {
    this(new StreamHTTPClient(apiKey, apiSecret));
  }

  public StreamSDKClient(
      @NotNull String apiKey, @NotNull String apiSecret, @NotNull StreamClientOptions options) {
    this(new StreamHTTPClient(apiKey, apiSecret, options));
  }

  public StreamSDKClient() {
    this(new StreamHTTPClient());
  }

  public StreamSDKClient(Properties properties) {
    this(new StreamHTTPClient(properties));
  }

  public StreamSDKClient(
      @NotNull String apiKey, @NotNull String apiSecret, @NotNull OkHttpClient httpClient) {
    this(new StreamHTTPClient(apiKey, apiSecret, httpClient));
  }

  public StreamSDKClient(StreamHTTPClient httpClient) {
    super(httpClient);
    this.httpClient = httpClient;
  }

  public Video video() {
    return new VideoClient(this);
  }

  public StreamHTTPClient getHttpClient() {
    return httpClient;
  }

  public Chat chat() {
    return new ChatClient(this);
  }

  public Feeds feeds() {
    return new FeedsClient(this);
  }

  public Moderation moderation() {
    return new ModerationClient(this);
  }

  public TokenBuilder tokenBuilder() {
    var tb = new TokenBuilder(httpClient.getApiSecret());
    return tb;
  }

  public StreamSDKClient getSDKClient() {
    return this;
  }

  /**
   * Verify a webhook signature using this client's API secret.
   *
   * <p>Convenience wrapper around {@link io.getstream.Webhook#verifySignature(byte[], String,
   * String)}.
   *
   * @param body the raw HTTP request body bytes
   * @param signature the value of the X-Signature header
   * @return true if the signature matches
   */
  public boolean verifySignature(byte[] body, String signature) {
    return io.getstream.Webhook.verifySignature(body, signature, this.httpClient.getApiSecret());
  }

  /**
   * Verify and parse a webhook payload in one call, using this client's API secret.
   *
   * <p>Handles gzip-compressed bodies transparently via magic-byte detection. Throws {@link
   * io.getstream.Webhook.InvalidWebhookError} on signature mismatch or parse failures.
   *
   * @param body the raw HTTP request body bytes (possibly gzip-compressed)
   * @param signature the value of the X-Signature header
   * @return the parsed event (typed event or {@link io.getstream.Webhook.UnknownEvent})
   */
  public Object verifyAndParseWebhook(byte[] body, String signature)
      throws io.getstream.Webhook.InvalidWebhookError {
    return io.getstream.Webhook.verifyAndParseWebhook(
        body, signature, this.httpClient.getApiSecret());
  }

  /**
   * Decode + parse a Stream-delivered SQS message body.
   *
   * <p>Convenience wrapper around {@link io.getstream.Webhook#parseSqs(String)}. No signature is
   * required; SQS deliveries are authenticated via AWS IAM.
   *
   * @param messageBody the SQS message body string
   * @return the parsed event (typed event or {@link io.getstream.Webhook.UnknownEvent})
   */
  public Object parseSqs(String messageBody) throws io.getstream.Webhook.InvalidWebhookError {
    return io.getstream.Webhook.parseSqs(messageBody);
  }

  /**
   * Decode + parse a Stream-delivered SNS notification body.
   *
   * <p>Accepts either the raw SNS HTTP envelope JSON or the pre-extracted Message string.
   * Convenience wrapper around {@link io.getstream.Webhook#parseSns(String)}. No signature is
   * required; SNS deliveries are authenticated via AWS IAM.
   *
   * @param notificationBody the SNS notification body (envelope JSON or extracted Message)
   * @return the parsed event (typed event or {@link io.getstream.Webhook.UnknownEvent})
   */
  public Object parseSns(String notificationBody) throws io.getstream.Webhook.InvalidWebhookError {
    return io.getstream.Webhook.parseSns(notificationBody);
  }

  /** Default poll interval for {@link #waitForTask(String)}. */
  private static final Duration DEFAULT_TASK_POLL_INTERVAL = Duration.ofSeconds(1);

  /** Default max wait for {@link #waitForTask(String)}. */
  private static final Duration DEFAULT_TASK_TIMEOUT = Duration.ofSeconds(60);

  /**
   * Polls {@code getTask(taskId)} until the task reaches a terminal status (per CHA-2958 §8). Uses
   * a 1-second poll interval and a 60-second timeout.
   *
   * @return the terminal {@link GetTaskResponse} when {@code status == "completed"}
   * @throws StreamTaskException if the task ends with {@code status == "failed"}
   * @throws StreamTransportException with {@code errorType == "timeout"} if the wait elapses
   * @throws StreamException for any other underlying transport / API failure
   */
  @NotNull
  public GetTaskResponse waitForTask(@NotNull String taskId) throws StreamException {
    return waitForTask(taskId, DEFAULT_TASK_POLL_INTERVAL, DEFAULT_TASK_TIMEOUT);
  }

  /**
   * Polls {@code getTask(taskId)} until the task reaches a terminal status (per CHA-2958 §8).
   *
   * @param taskId the task identifier returned by the operation that enqueued the task
   * @param pollInterval delay between polls; clamped to a non-negative value
   * @param timeout max total wait; the timeout window starts before the first poll
   * @throws StreamTaskException if the task ends with {@code status == "failed"}
   * @throws StreamTransportException with {@code errorType == "timeout"} if the wait elapses
   * @throws StreamException for any other underlying transport / API failure
   */
  @NotNull
  public GetTaskResponse waitForTask(
      @NotNull String taskId, @NotNull Duration pollInterval, @NotNull Duration timeout)
      throws StreamException {
    if (pollInterval.isNegative()) {
      throw new IllegalArgumentException("pollInterval must be non-negative, got " + pollInterval);
    }
    if (timeout.isNegative()) {
      throw new IllegalArgumentException("timeout must be non-negative, got " + timeout);
    }
    long deadlineNanos = System.nanoTime() + timeout.toNanos();
    long pollMillis = Math.max(0L, pollInterval.toMillis());
    while (true) {
      GetTaskResponse data = this.getTask(taskId).execute().getData();
      String status = data.getStatus();
      if ("completed".equals(status)) {
        return data;
      }
      if ("failed".equals(status)) {
        ErrorResult err = data.getError();
        throw new StreamTaskException(
            taskId,
            err != null && err.getType() != null ? err.getType() : "",
            err != null && err.getDescription() != null ? err.getDescription() : "",
            err != null ? err.getStacktrace() : null,
            err != null ? err.getVersion() : null);
      }
      if (System.nanoTime() >= deadlineNanos) {
        throw new StreamTransportException(
            StreamTransportException.TIMEOUT,
            "timed out waiting for task " + taskId + " after " + timeout,
            null);
      }
      if (pollMillis > 0) {
        try {
          Thread.sleep(pollMillis);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new StreamTransportException(
              StreamTransportException.TIMEOUT, "interrupted while polling task " + taskId, e);
        }
      }
    }
  }
}
