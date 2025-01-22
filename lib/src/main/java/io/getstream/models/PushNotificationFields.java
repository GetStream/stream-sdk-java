package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PushNotificationFields {

  @JsonProperty("offline_only")
  private Boolean offlineOnly;

  @JsonProperty("version")
  private String version;

  @JsonProperty("apn")
  private APNConfigFields apn;

  @JsonProperty("firebase")
  private FirebaseConfigFields firebase;

  @JsonProperty("huawei")
  private HuaweiConfigFields huawei;

  @JsonProperty("xiaomi")
  private XiaomiConfigFields xiaomi;

  @Nullable
  @JsonProperty("providers")
  private List<PushProvider> providers;
}
