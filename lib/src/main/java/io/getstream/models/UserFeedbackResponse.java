package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserFeedbackResponse {

  @JsonProperty("cid")
  private String cid;

  @JsonProperty("rating")
  private Integer rating;

  @JsonProperty("reason")
  private String reason;

  @JsonProperty("sdk")
  private String sdk;

  @JsonProperty("sdk_version")
  private String sdkVersion;

  @JsonProperty("session_id")
  private String sessionID;

  @JsonProperty("user_id")
  private String userID;

  @JsonProperty("platform")
  private PlatformDataResponse platform;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
