package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ConfigResponse {

  @JsonProperty("async")
  private Boolean async;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("key")
  private String key;

  @JsonProperty("team")
  private String team;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("ai_image_config")
  private AIImageConfig aiImageConfig;

  @Nullable
  @JsonProperty("ai_text_config")
  private AITextConfig aiTextConfig;

  @Nullable
  @JsonProperty("ai_video_config")
  private AIVideoConfig aiVideoConfig;

  @Nullable
  @JsonProperty("automod_platform_circumvention_config")
  private AutomodPlatformCircumventionConfig automodPlatformCircumventionConfig;

  @Nullable
  @JsonProperty("automod_semantic_filters_config")
  private AutomodSemanticFiltersConfig automodSemanticFiltersConfig;

  @Nullable
  @JsonProperty("automod_toxicity_config")
  private AutomodToxicityConfig automodToxicityConfig;

  @Nullable
  @JsonProperty("block_list_config")
  private BlockListConfig blockListConfig;

  @Nullable
  @JsonProperty("velocity_filter_config")
  private VelocityFilterConfig velocityFilterConfig;
}
