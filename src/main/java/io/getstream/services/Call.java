package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.models.framework.StreamResponse;
import org.jetbrains.annotations.NotNull;

public class Call extends CallGenerated {
  private CallResponse data;

  public Call(String type, String id, Video service) {
    super(type, id, service);
  }

  @NotNull
  @Override
  public StreamResponse<GetCallResponse> get(GetCallRequest request) throws StreamException {
    var resp = super.get(request);
    if (resp.getData() != null) {
      this.data = resp.getData().getCall();
    }
    return resp;
  }

  public StreamResponse<GetCallResponse> get() throws StreamException {
    return this.get(new GetCallRequest());
  }

  @NotNull
  @Override
  public StreamResponse<GetOrCreateCallResponse> getOrCreate(GetOrCreateCallRequest request)
      throws StreamException {
    var resp = super.getOrCreate(request);
    if (resp.getData() != null) {
      this.data = resp.getData().getCall();
    }
    return resp;
  }

  public StreamResponse<GetOrCreateCallResponse> getOrCreate() throws StreamException {
    return this.getOrCreate(new GetOrCreateCallRequest());
  }

  public String createSRTToken(@NotNull String userID) throws StreamException {
    if (this.data == null) {
      throw StreamException.build(
          "Call object is not initialized, please call get() or getOrCreate() first");
    }

    String token = this.service.getSDKClient().tokenBuilder().createToken(userID);
    String[] segments = token.split("$.", 3);
    String passphrase = segments[2];

    return data.getIngress()
        .getSrt()
        .getAddress()
        .replace("{passphrase}", passphrase)
        .replace("{token}", token);
  }
}
