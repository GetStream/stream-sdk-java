package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReactivateUsersRequest {

  /** User IDs to reactivate */
  @NotNull
  @JsonProperty("user_ids")
  private List<String> userIds;

  /** ID of the user who's reactivating the users */
  @Nullable
  @JsonProperty("created_by_id")
  private String createdById;

  @Nullable
  @JsonProperty("restore_channels")
  private Boolean restoreChannels;

  /** Restore previously deleted messages */
  @Nullable
  @JsonProperty("restore_messages")
  private Boolean restoreMessages;
}
