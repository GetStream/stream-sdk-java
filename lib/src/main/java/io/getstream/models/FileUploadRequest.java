package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadRequest {

  /** file field */
  @Nullable
  @JsonProperty("file")
  private String file;

  @Nullable
  @JsonProperty("user")
  private OnlyUserID user;
}
