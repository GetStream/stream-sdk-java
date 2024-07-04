package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeviceRequest {

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("push_provider")
  private String pushProvider;

  @Nullable
  @JsonProperty("push_provider_name")
  private String pushProviderName;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("voip_token")
  private Boolean voipToken;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
