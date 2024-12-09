package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallSettingsResponse {

  @NotNull
  @JsonProperty("audio")
  private AudioSettingsResponse audio;

  @NotNull
  @JsonProperty("backstage")
  private BackstageSettingsResponse backstage;

  @NotNull
  @JsonProperty("broadcasting")
  private BroadcastSettingsResponse broadcasting;

  @NotNull
  @JsonProperty("geofencing")
  private GeofenceSettingsResponse geofencing;

  @NotNull
  @JsonProperty("limits")
  private LimitsSettingsResponse limits;

  @NotNull
  @JsonProperty("recording")
  private RecordSettingsResponse recording;

  @NotNull
  @JsonProperty("ring")
  private RingSettingsResponse ring;

  @NotNull
  @JsonProperty("screensharing")
  private ScreensharingSettingsResponse screensharing;

  @NotNull
  @JsonProperty("thumbnails")
  private ThumbnailsSettingsResponse thumbnails;

  @NotNull
  @JsonProperty("transcription")
  private TranscriptionSettingsResponse transcription;

  @NotNull
  @JsonProperty("video")
  private VideoSettingsResponse video;
}
