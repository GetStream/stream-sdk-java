package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AzureRequest {

  @JsonProperty("abs_account_name")
  private String absAccountName;

  @JsonProperty("abs_client_id")
  private String absClientID;

  @JsonProperty("abs_client_secret")
  private String absClientSecret;

  @JsonProperty("abs_tenant_id")
  private String absTenantID;
}
