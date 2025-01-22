package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SegmentTargetResponse {

  @JsonProperty("app_pk")
  private Integer appPk;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("segment_id")
  private String segmentID;

  @JsonProperty("target_id")
  private String targetID;
}
