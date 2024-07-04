package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  /** Device ID */
  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("push_provider")
  private String pushProvider;

  @NotNull
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("disabled")
  private Boolean disabled;

  @Nullable
  @JsonProperty("disabled_reason")
  private String disabledReason;

  /** Name of the push provider configuration */
  @Nullable
  @JsonProperty("push_provider_name")
  private String pushProviderName;

  @Nullable
  @JsonProperty("voip")
  private Boolean voip;
}
