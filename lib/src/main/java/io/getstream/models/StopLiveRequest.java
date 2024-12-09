package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class StopLiveRequest {

  @Nullable
  @JsonProperty("continue_hls")
  private Boolean continueHLS;

  @Nullable
  @JsonProperty("continue_rtmp_broadcast")
  private Boolean continueRTMPBroadcast;

  @Nullable
  @JsonProperty("continue_recording")
  private Boolean continueRecording;

  @Nullable
  @JsonProperty("continue_transcription")
  private Boolean continueTranscription;
}
