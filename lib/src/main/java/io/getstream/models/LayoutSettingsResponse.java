package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class LayoutSettingsResponse {

  @JsonProperty("external_app_url")
  private String externalAppUrl;

  @JsonProperty("external_css_url")
  private String externalCssUrl;

  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("detect_orientation")
  private Boolean detectOrientation;

  @Nullable
  @JsonProperty("options")
  private Map<String, Object> options;
}
