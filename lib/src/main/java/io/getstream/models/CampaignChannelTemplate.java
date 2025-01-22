package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CampaignChannelTemplate {

  @JsonProperty("type")
  private String type;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("members")
  private List<String> members;
}
