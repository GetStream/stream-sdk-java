package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckSNSRequest {

  /** AWS SNS access key */
  @Nullable
  @JsonProperty("sns_key")
  private String snsKey;

  /** AWS SNS key secret */
  @Nullable
  @JsonProperty("sns_secret")
  private String snsSecret;

  /** AWS SNS topic ARN */
  @Nullable
  @JsonProperty("sns_topic_arn")
  private String snsTopicArn;
}
