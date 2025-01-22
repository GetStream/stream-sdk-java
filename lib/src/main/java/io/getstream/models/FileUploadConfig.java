package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FileUploadConfig {

  @JsonProperty("size_limit")
  private Integer sizeLimit;

  @Nullable
  @JsonProperty("allowed_file_extensions")
  private List<String> allowedFileExtensions;

  @Nullable
  @JsonProperty("allowed_mime_types")
  private List<String> allowedMimeTypes;

  @Nullable
  @JsonProperty("blocked_file_extensions")
  private List<String> blockedFileExtensions;

  @Nullable
  @JsonProperty("blocked_mime_types")
  private List<String> blockedMimeTypes;
}
