package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LayoutSettings {

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
