package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PushNotificationFields {

  @NotNull
  @JsonProperty("offline_only")
  private Boolean offlineOnly;

  @NotNull
  @JsonProperty("version")
  private String version;

  @NotNull
  @JsonProperty("apn")
  private APNConfigFields apn;

  @NotNull
  @JsonProperty("firebase")
  private FirebaseConfigFields firebase;

  @NotNull
  @JsonProperty("huawei")
  private HuaweiConfigFields huawei;

  @NotNull
  @JsonProperty("xiaomi")
  private XiaomiConfigFields xiaomi;

  @Nullable
  @JsonProperty("providers")
  private List<PushProvider> providers;
}
