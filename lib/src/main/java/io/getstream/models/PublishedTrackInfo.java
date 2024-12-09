package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PublishedTrackInfo {

  @Nullable
  @JsonProperty("codec_mime_type")
  private String codecMimeType;

  @Nullable
  @JsonProperty("duration_seconds")
  private Integer durationSeconds;

  @Nullable
  @JsonProperty("track_type")
  private String trackType;
}
