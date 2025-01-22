package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RTMPBroadcastRequest {

  @JsonProperty("name")
  private String name;

  @JsonProperty("stream_url")
  private String streamUrl;

  @Nullable
  @JsonProperty("quality")
  private String quality;

  @Nullable
  @JsonProperty("stream_key")
  private String streamKey;

  @Nullable
  @JsonProperty("layout")
  private LayoutSettingsRequest layout;
}
