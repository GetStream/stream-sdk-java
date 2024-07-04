package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallRequest {

  @Nullable
  @JsonProperty("created_by_id")
  private String createdById;

  @Nullable
  @JsonProperty("starts_at")
  private Date startsAt;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("members")
  private List<MemberRequest> members;

  @Nullable
  @JsonProperty("created_by")
  private UserRequest createdBy;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("settings_override")
  private CallSettingsRequest settingsOverride;
}
