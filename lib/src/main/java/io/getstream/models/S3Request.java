package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class S3Request {

  @NotNull
  @JsonProperty("s3_region")
  private String s3Region;

  @Nullable
  @JsonProperty("s3_api_key")
  private String s3ApiKey;

  @Nullable
  @JsonProperty("s3_secret")
  private String s3Secret;
}
