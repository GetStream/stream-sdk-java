package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Segment {

  @NotNull
  @JsonProperty("all_sender_channels")
  private Boolean allSenderChannels;

  @NotNull
  @JsonProperty("all_users")
  private Boolean allUsers;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("size")
  private Integer size;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
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
