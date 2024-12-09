package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CheckSQSRequest {

  @Nullable
  @JsonProperty("sqs_key")
  private String sqsKey;

  @Nullable
  @JsonProperty("sqs_secret")
  private String sqsSecret;

  @Nullable
  @JsonProperty("sqs_url")
  private String sqsUrl;
}
