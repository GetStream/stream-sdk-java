package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GoLiveRequest {

  @Nullable
  @JsonProperty("recording_storage_name")
  private String recordingStorageName;

  @Nullable
  @JsonProperty("start_closed_caption")
  private Boolean startClosedCaption;

  @Nullable
  @JsonProperty("start_hls")
  private Boolean startHLS;

  @Nullable
  @JsonProperty("start_rtmp_broadcasts")
  private Boolean startRTMPBroadcasts;

  @Nullable
  @JsonProperty("start_recording")
  private Boolean startRecording;

  @Nullable
  @JsonProperty("start_transcription")
  private Boolean startTranscription;

  @Nullable
  @JsonProperty("transcription_storage_name")
  private String transcriptionStorageName;
}
