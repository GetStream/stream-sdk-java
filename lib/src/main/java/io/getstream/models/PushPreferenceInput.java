package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PushPreferenceInput {

  @Nullable
  @JsonProperty("channel_cid")
  private String channelCid;

  @Nullable
  @JsonProperty("chat_level")
  private String chatLevel;

  @Nullable
  @JsonProperty("disabled_until")
  private Date disabledUntil;

  @Nullable
  @JsonProperty("remove_disable")
  private Boolean removeDisable;

  @Nullable
  @JsonProperty("user_id")
  private String userID;
}
