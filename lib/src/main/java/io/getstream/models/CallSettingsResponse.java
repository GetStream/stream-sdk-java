package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallSettingsResponse {

  @JsonProperty("audio")
  private AudioSettingsResponse audio;

  @JsonProperty("backstage")
  private BackstageSettingsResponse backstage;

  @JsonProperty("broadcasting")
  private BroadcastSettingsResponse broadcasting;

  @JsonProperty("geofencing")
  private GeofenceSettingsResponse geofencing;

  @JsonProperty("limits")
  private LimitsSettingsResponse limits;

  @JsonProperty("recording")
  private RecordSettingsResponse recording;

  @JsonProperty("ring")
  private RingSettingsResponse ring;

  @JsonProperty("screensharing")
  private ScreensharingSettingsResponse screensharing;

  @JsonProperty("session")
  private SessionSettingsResponse session;

  @JsonProperty("thumbnails")
  private ThumbnailsSettingsResponse thumbnails;

  @JsonProperty("transcription")
  private TranscriptionSettingsResponse transcription;

  @JsonProperty("video")
  private VideoSettingsResponse video;
}
