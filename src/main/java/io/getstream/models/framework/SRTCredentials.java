package io.getstream.models.framework;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

/**
 * SRT address an encoder (OBS, ffmpeg, ...) publishes to. The address contains a user token, so
 * keep it secret.
 */
@Builder
@Getter
public class SRTCredentials {
  String address;

  /** When the token in the address expires. */
  Instant expiresAt;
}
