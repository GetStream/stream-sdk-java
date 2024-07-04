package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateExternalStorageRequest {

  @NotNull
  @JsonProperty("bucket")
  private String bucket;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("storage_type")
  private String storageType;

  @Nullable
  @JsonProperty("gcs_credentials")
  private String gcsCredentials;

  @Nullable
  @JsonProperty("path")
  private String path;

  @Nullable
  @JsonProperty("aws_s3")
  private S3Request awsS3;

  @Nullable
  @JsonProperty("azure_blob")
  private AzureRequest azureBlob;
}
