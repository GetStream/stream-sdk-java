package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Segment {

  @JsonProperty("all_sender_channels")
  private Boolean allSenderChannels;

  @JsonProperty("all_users")
  private Boolean allUsers;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("size")
  private Integer size;

  @JsonProperty("type")
  private String type;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("description")
  private String description;

  @Nullable
  @JsonProperty("task_id")
  private String taskID;

  @Nullable
  @JsonProperty("filter")
  private Map<String, Object> filter;
}
