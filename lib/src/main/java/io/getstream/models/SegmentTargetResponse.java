package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SegmentTargetResponse {

  @NotNull
  @JsonProperty("app_pk")
  private Integer appPk;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("segment_id")
  private String segmentID;

  @NotNull
  @JsonProperty("target_id")
  private String targetID;
}
