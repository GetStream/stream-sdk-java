package io.getstream.services;

import io.getstream.exceptions.StreamException;
import io.getstream.models.*;
import io.getstream.models.framework.SRTCredentials;
import io.getstream.models.framework.StreamResponse;
import io.getstream.services.framework.TokenBuilder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class Call extends BaseCall {
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

  /**
   * Creates the SRT address an encoder (OBS, ffmpeg, ...) uses to publish into this call. The token
   * in it expires after {@link #DEFAULT_SRT_TOKEN_VALIDITY}.
   *
   * @see #createSRTCredentials(String, Duration)
   */
  public SRTCredentials createSRTCredentials(@NotNull String userID) throws StreamException {
    return createSRTCredentials(userID, DEFAULT_SRT_TOKEN_VALIDITY);
  }

  /**
   * Creates the SRT address an encoder (OBS, ffmpeg, ...) uses to publish into this call.
   *
   * <p>The address embeds a user token that can only join this call and expires after {@code
   * validity}. The token is checked again whenever the stream reconnects, and at least every 6
   * hours during a stream, so it must stay valid until the stream ends. Treat the address as a
   * secret.
   *
   * @param userID the user the encoder publishes as
   * @param validity how long the address works, at least one second
   */
  public SRTCredentials createSRTCredentials(@NotNull String userID, @NotNull Duration validity)
      throws StreamException {
    if (this.data == null) {
      throw StreamException.build(
          "Call object is not initialized, please call get() or getOrCreate() first");
    }
    return buildSRTCredentials(
        data.getIngress().getSrt().getAddress(),
        this.service.getSDKClient().tokenBuilder(),
        userID,
        callType + ":" + callID,
        validity);
  }

  /** Default lifetime of the token in an SRT address. */
  public static final Duration DEFAULT_SRT_TOKEN_VALIDITY = Duration.ofHours(24);

  /** SRT caps the stream id at 512 bytes; encoders refuse a longer one. */
  static final int MAX_SRT_STREAM_ID_BYTES = 512;

  private static final Pattern SRT_STREAM_ID = Pattern.compile("[?&]streamid=([^&]*)");

  static SRTCredentials buildSRTCredentials(
      @NotNull String addressTemplate,
      @NotNull TokenBuilder tokenBuilder,
      @NotNull String userID,
      @NotNull String callCID,
      @NotNull Duration validity)
      throws StreamException {
    if (validity == null || validity.compareTo(Duration.ofSeconds(1)) < 0) {
      throw new IllegalArgumentException("validity must be at least one second");
    }

    Instant expiresAt = Instant.now().plus(validity).truncatedTo(ChronoUnit.SECONDS);
    String token = tokenBuilder.createScopedCallToken(userID, callCID, expiresAt);
    String[] segments = token.split("\\.");
    if (segments.length != 3) {
      throw StreamException.build("Invalid token format");
    }

    String address = addressTemplate.replace("{passphrase}", segments[2]).replace("{token}", token);
    Matcher streamID = SRT_STREAM_ID.matcher(address);
    if (streamID.find()) {
      int size = streamID.group(1).getBytes(StandardCharsets.UTF_8).length;
      if (size > MAX_SRT_STREAM_ID_BYTES) {
        throw StreamException.build(
            "The SRT stream id is "
                + size
                + " bytes, over the "
                + MAX_SRT_STREAM_ID_BYTES
                + "-byte SRT limit. Use a shorter user ID or call ID.");
      }
    }

    return SRTCredentials.builder().address(address).expiresAt(expiresAt).build();
  }
}
