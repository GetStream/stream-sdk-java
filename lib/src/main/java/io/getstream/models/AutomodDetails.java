package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AutomodDetails {

  @Nullable
  @JsonProperty("action")
  private String action;

  @Nullable
  @JsonProperty("original_message_type")
  private String originalMessageType;

  @Nullable
  @JsonProperty("image_labels")
  private List<String> imageLabels;

  @Nullable
  @JsonProperty("message_details")
  private FlagMessageDetails messageDetails;

  @Nullable
  @JsonProperty("result")
  private MessageModerationResult result;
}
