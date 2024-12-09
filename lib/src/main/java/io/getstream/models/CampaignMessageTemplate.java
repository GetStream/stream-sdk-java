package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CampaignMessageTemplate {

  @NotNull
  @JsonProperty("poll_id")
  private String pollID;

  @NotNull
  @JsonProperty("text")
  private String text;

  @NotNull
  @JsonProperty("attachments")
  private List<Attachment> attachments;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
