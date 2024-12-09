package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateExternalStorageRequest {

  @NotNull
  @JsonProperty("bucket")
  private String bucket;

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
