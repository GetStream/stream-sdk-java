package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallSettingsRequest {

  @Nullable
  @JsonProperty("audio")
  private AudioSettingsRequest audio;

  @Nullable
  @JsonProperty("backstage")
  private BackstageSettingsRequest backstage;

  @Nullable
  @JsonProperty("broadcasting")
  private BroadcastSettingsRequest broadcasting;

  @Nullable
  @JsonProperty("geofencing")
  private GeofenceSettingsRequest geofencing;

  @Nullable
  @JsonProperty("limits")
  private LimitsSettingsRequest limits;

  @Nullable
  @JsonProperty("recording")
  private RecordSettingsRequest recording;

  @Nullable
  @JsonProperty("ring")
  private RingSettingsRequest ring;

  @Nullable
  @JsonProperty("screensharing")
  private ScreensharingSettingsRequest screensharing;

  @Nullable
  @JsonProperty("session")
  private SessionSettingsRequest session;

  @Nullable
  @JsonProperty("thumbnails")
  private ThumbnailsSettingsRequest thumbnails;

  @Nullable
  @JsonProperty("transcription")
  private TranscriptionSettingsRequest transcription;

  @Nullable
  @JsonProperty("video")
  private VideoSettingsRequest video;
}
