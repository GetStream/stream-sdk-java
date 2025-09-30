package io.getstream.services;

import io.getstream.services.framework.StreamSDKClient;
import org.jetbrains.annotations.NotNull;

public class ChatClient extends ChatImpl implements Chat {
  StreamSDKClient client;

  public ChatClient(StreamSDKClient client) {
    super(client.getHttpClient());
    this.client = client;
  }

  public StreamSDKClient getSDKClient() {
    return client;
  }

  @NotNull
  public Channel channel(String channelType, String channelID) {
    return new Channel(channelType, channelID, this);
  }
}
