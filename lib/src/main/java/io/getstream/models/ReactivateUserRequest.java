package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReactivateUserRequest {

  /** ID of the user who's reactivating the user */
  @Nullable
  @JsonProperty("created_by_id")
  private String createdById;

  /** Set this field to put new name for the user */
  @Nullable
  @JsonProperty("name")
  private String name;

  /** Restore previously deleted messages */
  @Nullable
  @JsonProperty("restore_messages")
  private Boolean restoreMessages;
}
