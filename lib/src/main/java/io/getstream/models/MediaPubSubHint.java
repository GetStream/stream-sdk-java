package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MediaPubSubHint {

  @NotNull
  @JsonProperty("audio_published")
  private Boolean audioPublished;

  @NotNull
  @JsonProperty("audio_subscribed")
  private Boolean audioSubscribed;

  @NotNull
  @JsonProperty("video_published")
  private Boolean videoPublished;

  @NotNull
  @JsonProperty("video_subscribed")
  private Boolean videoSubscribed;
}
