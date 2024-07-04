package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadRequest {

  @Nullable
  @JsonProperty("file")
  private String file;

  /** field with JSON-encoded array of image size configurations */
  @Nullable
  @JsonProperty("upload_sizes")
  private List<ImageSize> uploadSizes;

  @Nullable
  @JsonProperty("user")
  private OnlyUserID user;
}
