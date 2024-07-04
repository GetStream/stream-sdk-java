package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrCreateCallRequest {

  @Nullable
  @JsonProperty("members_limit")
  private Integer membersLimit;

  /** if provided it sends a notification event to the members for this call */
  @Nullable
  @JsonProperty("notify")
  private Boolean notify;

  /** if provided it sends a ring event to the members for this call */
  @Nullable
  @JsonProperty("ring")
  private Boolean ring;

  @Nullable
  @JsonProperty("data")
  private CallRequest data;
}
