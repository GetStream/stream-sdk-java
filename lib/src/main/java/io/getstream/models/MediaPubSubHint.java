package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MediaPubSubHint {

  @JsonProperty("audio_published")
  private Boolean audioPublished;

  @JsonProperty("audio_subscribed")
  private Boolean audioSubscribed;

  @JsonProperty("video_published")
  private Boolean videoPublished;

  @JsonProperty("video_subscribed")
  private Boolean videoSubscribed;
}
