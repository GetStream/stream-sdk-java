package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpsertModerationTemplateResponse {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("name")
  private String name;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("config")
  private FeedsModerationTemplateConfig config;
}
