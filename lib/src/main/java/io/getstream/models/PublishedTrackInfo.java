package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
