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
   * io.getstream.Webhook.InvalidWebhookException} on signature mismatch or parse failures.
   *
   * @param body the raw HTTP request body bytes (possibly gzip-compressed)
   * @param signature the value of the X-Signature header
   * @return the parsed event (typed event or {@link io.getstream.Webhook.UnknownEvent})
   */
  public Object verifyAndParseWebhook(byte[] body, String signature)
      throws io.getstream.Webhook.InvalidWebhookException {
    return io.getstream.Webhook.verifyAndParseWebhook(
        body, signature, this.httpClient.getApiSecret());
  }
}
