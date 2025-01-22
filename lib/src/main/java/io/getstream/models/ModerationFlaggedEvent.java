package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationFlaggedEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("item")
  private String item;

  @Nullable
  @JsonProperty("object_id")
  private String objectID;

  @Nullable
  @JsonProperty("user")
  private User user;
}
