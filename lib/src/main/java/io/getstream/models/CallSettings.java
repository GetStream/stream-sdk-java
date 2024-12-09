package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallSettings {

  @Nullable
  @JsonProperty("audio")
  private AudioSettings audio;

  @Nullable
  @JsonProperty("backstage")
  private BackstageSettings backstage;

  @Nullable
  @JsonProperty("broadcasting")
  private BroadcastSettings broadcasting;

  @Nullable
  @JsonProperty("geofencing")
  private GeofenceSettings geofencing;

  @Nullable
  @JsonProperty("limits")
  private LimitsSettings limits;

  @Nullable
  @JsonProperty("recording")
  private RecordSettings recording;

  @Nullable
  @JsonProperty("ring")
  private RingSettings ring;

  @Nullable
  @JsonProperty("screensharing")
  private ScreensharingSettings screensharing;

  @Nullable
  @JsonProperty("thumbnails")
  private ThumbnailsSettings thumbnails;

  @Nullable
  @JsonProperty("transcription")
  private TranscriptionSettings transcription;

  @Nullable
  @JsonProperty("video")
  private VideoSettings video;
}
