package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UploadImageRequest {

  @Nullable
  @JsonProperty("file")
  private String file;

  @Nullable
  @JsonProperty("upload_sizes")
  private List<ImageSize> uploadSizes;

  @Nullable
  @JsonProperty("user")
  private OnlyUserID user;
}
