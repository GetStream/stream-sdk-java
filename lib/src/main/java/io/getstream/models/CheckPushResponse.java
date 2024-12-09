package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CheckPushResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("rendered_apn_template")
  private String renderedApnTemplate;

  @Nullable
  @JsonProperty("rendered_firebase_template")
  private String renderedFirebaseTemplate;

  @Nullable
  @JsonProperty("skip_devices")
  private Boolean skipDevices;

  @Nullable
  @JsonProperty("general_errors")
  private List<String> generalErrors;

  @Nullable
  @JsonProperty("device_errors")
  private Map<String, DeviceErrorInfo> deviceErrors;

  @Nullable
  @JsonProperty("rendered_message")
  private Map<String, String> renderedMessage;
}
