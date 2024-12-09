package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AzureRequest {

  @NotNull
  @JsonProperty("abs_account_name")
  private String absAccountName;

  @NotNull
  @JsonProperty("abs_client_id")
  private String absClientID;

  @NotNull
  @JsonProperty("abs_client_secret")
  private String absClientSecret;

  @NotNull
  @JsonProperty("abs_tenant_id")
  private String absTenantID;
}
