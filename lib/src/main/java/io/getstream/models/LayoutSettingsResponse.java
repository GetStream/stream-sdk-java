package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LayoutSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("external_app_url")
  private String externalAppUrl;

  @NotNull
  @JsonProperty("external_css_url")
  private String externalCssUrl;

  @NotNull
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("options")
  private Map<String, Object> options;
}
