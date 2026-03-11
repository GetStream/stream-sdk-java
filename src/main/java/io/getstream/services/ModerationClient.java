package io.getstream.services;

import io.getstream.services.framework.StreamSDKClient;

public class ModerationClient extends ModerationImpl implements Moderation {
  StreamSDKClient client;

  public ModerationClient(StreamSDKClient client) {
    super(client.getHttpClient());
    this.client = client;
  }

  public StreamSDKClient getSDKClient() {
    return client;
  }
}
