package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.annotations.Query;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnbanRequest {

  @Nullable
  @JsonProperty("unbanned_by_id")
  private String unbannedByID;

  @Nullable
  @JsonProperty("unbanned_by")
  private UserRequest unbannedBy;

  @Query("target_user_id")
  @JsonIgnore
  private String TargetUserID;

  @Query("channel_cid")
  @JsonIgnore
  private String ChannelCid;

  @Query("created_by")
  @JsonIgnore
  private String CreatedBy;
}
