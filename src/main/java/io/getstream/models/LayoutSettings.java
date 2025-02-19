/*
 * ========================================================================
 * WARNING: GENERATED CODE -- DO NOT EDIT!
 * ========================================================================
 *
 * This file was auto-generated by GetStream internal OpenAPI
 *
 * Any modifications to this file will be lost upon regeneration.
 * To make changes, please modify the source templates and regenerate.
 *
 * ========================================================================
 */
package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class LayoutSettings {

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
