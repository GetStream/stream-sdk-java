package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportChannelsResult {

  /** URL of result */
  @NotNull
  @JsonProperty("url")
  private String url;

  /** S3 path of result */
  @Nullable
  @JsonProperty("path")
  private String path;

  /** S3 bucket name result */
  @Nullable
  @JsonProperty("s3_bucket_name")
  private String s3BucketName;
}
