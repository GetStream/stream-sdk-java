package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationUsageStats {

  @JsonProperty("app_pk")
  private Integer appPk;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("organization_id")
  private Integer organizationID;

  @JsonProperty("reference_date")
  private Date referenceDate;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("usage_amount")
  private Integer usageAmount;

  @JsonProperty("usage_type")
  private String usageType;
}
