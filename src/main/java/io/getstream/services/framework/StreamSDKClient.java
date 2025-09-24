package io.getstream.services.framework;

import io.getstream.services.*;
import java.util.Properties;
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

  public StreamSDKClient(StreamHTTPClient httpClient) {
    super(httpClient);
    this.httpClient = httpClient;
  }

  public Video video() {
    return new VideoImpl(this);
  }

  public StreamHTTPClient getHttpClient() {
    return httpClient;
  }

  public Chat chat() {
    return new ChatImpl(httpClient);
  }

  public TokenBuilder tokenBuilder() {
    var tb = new TokenBuilder(httpClient.getApiSecret());
    return tb;
  }
}
