package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckPushResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("rendered_apn_template")
  private String renderedApnTemplate;

  @Nullable
  @JsonProperty("rendered_firebase_template")
  private String renderedFirebaseTemplate;

  /** Don't require existing devices to render templates */
  @Nullable
  @JsonProperty("skip_devices")
  private Boolean skipDevices;

  /** List of general errors */
  @Nullable
  @JsonProperty("general_errors")
  private List<String> generalErrors;

  /** Object with device errors */
  @Nullable
  @JsonProperty("device_errors")
  private Map<String, DeviceErrorInfo> deviceErrors;

  @Nullable
  @JsonProperty("rendered_message")
  private Map<String, String> renderedMessage;
}
