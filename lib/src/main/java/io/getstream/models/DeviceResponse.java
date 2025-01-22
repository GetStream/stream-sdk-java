package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeviceResponse {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("push_provider")
  private String pushProvider;

  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("disabled")
  private Boolean disabled;

  @Nullable
  @JsonProperty("disabled_reason")
  private String disabledReason;

  @Nullable
  @JsonProperty("push_provider_name")
  private String pushProviderName;

  @Nullable
  @JsonProperty("voip")
  private Boolean voip;
}
