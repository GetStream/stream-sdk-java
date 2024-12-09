package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class S3Request {

  @NotNull
  @JsonProperty("s3_region")
  private String s3Region;

  @Nullable
  @JsonProperty("s3_api_key")
  private String s3APIKey;

  @Nullable
  @JsonProperty("s3_secret")
  private String s3Secret;
}
