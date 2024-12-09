package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReactivateUserRequest {

  @Nullable
  @JsonProperty("created_by_id")
  private String createdByID;

  @Nullable
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("restore_messages")
  private Boolean restoreMessages;
}
