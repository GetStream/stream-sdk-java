package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ChannelPushPreferences {

  @Nullable
  @JsonProperty("chat_level")
  private String chatLevel;

  @Nullable
  @JsonProperty("disabled_until")
  private Date disabledUntil;
}
