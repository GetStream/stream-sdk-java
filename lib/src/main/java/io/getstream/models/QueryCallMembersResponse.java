package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryCallMembersResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("members")
  private List<MemberResponse> members;

  @Nullable
  @JsonProperty("next")
  private String next;

  @Nullable
  @JsonProperty("prev")
  private String prev;
}
