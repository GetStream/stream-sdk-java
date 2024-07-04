package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoLiveRequest {

  @Nullable
  @JsonProperty("recording_storage_name")
  private String recordingStorageName;

  @Nullable
  @JsonProperty("start_hls")
  private Boolean startHls;

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
