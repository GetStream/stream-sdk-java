package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UploadFileRequest {

  @Nullable
  @JsonProperty("file")
  private String file;

  @Nullable
  @JsonProperty("user")
  private OnlyUserID user;
}
