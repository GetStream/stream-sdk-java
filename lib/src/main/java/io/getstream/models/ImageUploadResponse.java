package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ImageUploadResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("file")
  private String file;

  @Nullable
  @JsonProperty("thumb_url")
  private String thumbUrl;

  @Nullable
  @JsonProperty("upload_sizes")
  private List<ImageSize> uploadSizes;
}
