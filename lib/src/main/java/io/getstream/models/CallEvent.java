package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallEvent {

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("end_timestamp")
  private Integer endTimestamp;

  @NotNull
  @JsonProperty("internal")
  private Boolean internal;

  @NotNull
  @JsonProperty("kind")
  private String kind;

  @NotNull
  @JsonProperty("severity")
  private Integer severity;

  @NotNull
  @JsonProperty("timestamp")
  private Integer timestamp;

  @NotNull
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("category")
  private String category;

  @Nullable
  @JsonProperty("component")
  private String component;

  @Nullable
  @JsonProperty("issue_tags")
  private List<String> issueTags;
}
