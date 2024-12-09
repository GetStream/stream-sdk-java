package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CheckSNSRequest {

  @Nullable
  @JsonProperty("sns_key")
  private String snsKey;

  @Nullable
  @JsonProperty("sns_secret")
  private String snsSecret;

  @Nullable
  @JsonProperty("sns_topic_arn")
  private String snsTopicArn;
}
