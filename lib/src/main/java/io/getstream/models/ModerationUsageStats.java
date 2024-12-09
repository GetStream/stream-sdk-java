package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationUsageStats {

  @NotNull
  @JsonProperty("app_pk")
  private Integer appPk;

  @NotNull
  @JsonProperty("id")
  private Integer id;

  @NotNull
  @JsonProperty("organization_id")
  private Integer organizationID;

  @NotNull
  @JsonProperty("reference_date")
  private Date referenceDate;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("usage_amount")
  private Integer usageAmount;

  @NotNull
  @JsonProperty("usage_type")
  private String usageType;
}
