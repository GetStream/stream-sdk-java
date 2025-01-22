package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Reaction {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("message_id")
  private String messageID;

  @JsonProperty("score")
  private Integer score;

  @JsonProperty("type")
  private String type;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("user")
  private User user;
}
