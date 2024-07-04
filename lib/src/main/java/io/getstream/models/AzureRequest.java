package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AzureRequest {

  @NotNull
  @JsonProperty("abs_account_name")
  private String absAccountName;

  @NotNull
  @JsonProperty("abs_client_id")
  private String absClientId;

  @NotNull
  @JsonProperty("abs_client_secret")
  private String absClientSecret;

  @NotNull
  @JsonProperty("abs_tenant_id")
  private String absTenantId;
}
