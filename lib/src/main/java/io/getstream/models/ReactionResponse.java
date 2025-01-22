package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReactionResponse {

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

  @JsonProperty("user_id")
  private String userID;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @JsonProperty("user")
  private UserResponse user;
}
