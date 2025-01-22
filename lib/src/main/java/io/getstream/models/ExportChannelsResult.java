package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ExportChannelsResult {

  @JsonProperty("url")
  private String url;

  @Nullable
  @JsonProperty("path")
  private String path;

  @Nullable
  @JsonProperty("s3_bucket_name")
  private String s3BucketName;
}
