package io.getstream.models.framework;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

/**
 * SRT address an encoder (OBS, ffmpeg, ...) publishes to. The address contains a user token, so
 * {@link #toString()} leaves it out; call {@code getAddress()} when you need it.
 */
@Builder
@Getter
public class SRTCredentials {
  String address;

  /** When the token in the address expires. */
  Instant expiresAt;

  @Override
  public String toString() {
    return "SRTCredentials(address=[redacted], expiresAt=" + expiresAt + ")";
  }
}
