package io.getstream.services;

import io.getstream.services.framework.StreamSDKClient;
import org.jetbrains.annotations.NotNull;

public class FeedsClient extends FeedsImpl implements Feeds {
  StreamSDKClient client;

  public FeedsClient(StreamSDKClient client) {
    super(client.getHttpClient());
    this.client = client;
  }

  public StreamSDKClient getSDKClient() {
    return client;
  }

  @NotNull
  public Feed feed(String channelType, String channelID) {
    return new Feed(channelType, channelID, client.feeds());
  }
}
