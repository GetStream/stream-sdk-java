package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationV2Response {

  @JsonProperty("action")
  private String action;

  @JsonProperty("original_text")
  private String originalText;

  @Nullable
  @JsonProperty("blocklist_matched")
  private String blocklistMatched;

  @Nullable
  @JsonProperty("platform_circumvented")
  private Boolean platformCircumvented;

  @Nullable
  @JsonProperty("semantic_filter_matched")
  private String semanticFilterMatched;

  @Nullable
  @JsonProperty("image_harms")
  private List<String> imageHarms;

  @Nullable
  @JsonProperty("text_harms")
  private List<String> textHarms;
}
