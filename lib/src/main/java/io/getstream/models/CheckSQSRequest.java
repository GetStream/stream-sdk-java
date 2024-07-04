package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckSQSRequest {

  /** AWS SQS access key */
  @Nullable
  @JsonProperty("sqs_key")
  private String sqsKey;

  /** AWS SQS key secret */
  @Nullable
  @JsonProperty("sqs_secret")
  private String sqsSecret;

  /** AWS SQS endpoint URL */
  @Nullable
  @JsonProperty("sqs_url")
  private String sqsUrl;
}
