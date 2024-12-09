package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FileUploadResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("file")
  private String file;

  @Nullable
  @JsonProperty("thumb_url")
  private String thumbUrl;
}
