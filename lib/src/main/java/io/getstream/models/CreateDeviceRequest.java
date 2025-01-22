package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateDeviceRequest {

  @JsonProperty("id")
  private String id;

  @JsonProperty("push_provider")
  private String pushProvider;

  @Nullable
  @JsonProperty("push_provider_name")
  private String pushProviderName;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("voip_token")
  private Boolean voipToken;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
