package io.getstream.services;

import io.getstream.services.framework.StreamSDKClient;
import org.jetbrains.annotations.NotNull;

public class VideoClient extends VideoImpl implements Video {
  StreamSDKClient client;

  public VideoClient(StreamSDKClient client) {
    super(client.getHttpClient());
    this.client = client;
  }

  @Override
  public StreamSDKClient getSDKClient() {
    return this.client;
  }

  @NotNull
  public Call call(String callType, String callID) {
    return new Call(callType, callID, client.video());
  }
}
