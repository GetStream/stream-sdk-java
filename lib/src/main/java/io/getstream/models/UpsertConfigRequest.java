package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpsertConfigRequest {

  @NotNull
  @JsonProperty("key")
  private String key;

  @Nullable
  @JsonProperty("async")
  private Boolean async;

  @Nullable
  @JsonProperty("ai_image_config")
  private AIImageConfig aiImageConfig;

  @Nullable
  @JsonProperty("ai_text_config")
  private AITextConfig aiTextConfig;

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
  @JsonProperty("aws_rekognition_config")
  private AIImageConfig awsRekognitionConfig;

  @Nullable
  @JsonProperty("block_list_config")
  private BlockListConfig blockListConfig;

  @Nullable
  @JsonProperty("bodyguard_config")
  private AITextConfig bodyguardConfig;

  @Nullable
  @JsonProperty("google_vision_config")
  private GoogleVisionConfig googleVisionConfig;

  @Nullable
  @JsonProperty("velocity_filter_config")
  private VelocityFilterConfig velocityFilterConfig;
}
