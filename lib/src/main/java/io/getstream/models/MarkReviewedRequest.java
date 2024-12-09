package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MarkReviewedRequest {

  @Nullable
  @JsonProperty("content_to_mark_as_reviewed_limit")
  private Integer contentToMarkAsReviewedLimit;

  @Nullable
  @JsonProperty("disable_marking_content_as_reviewed")
  private Boolean disableMarkingContentAsReviewed;
}
