package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

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
