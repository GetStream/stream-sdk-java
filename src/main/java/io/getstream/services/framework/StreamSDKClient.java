package io.getstream.services.framework;

import io.getstream.services.*;
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
}
