package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReactivateUsersRequest {

  @NotNull
  @JsonProperty("user_ids")
  private List<String> userIds;

  @Nullable
  @JsonProperty("created_by_id")
  private String createdByID;

  @Nullable
  @JsonProperty("restore_channels")
  private Boolean restoreChannels;

  @Nullable
  @JsonProperty("restore_messages")
  private Boolean restoreMessages;
}
