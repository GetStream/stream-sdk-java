package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallType {

  @JsonProperty("AppPK")
  private Integer appPK;

  @JsonProperty("CreatedAt")
  private Date createdAt;

  @JsonProperty("ExternalStorage")
  private String externalStorage;

  @JsonProperty("Name")
  private String name;

  @JsonProperty("PK")
  private Integer pK;

  @JsonProperty("UpdatedAt")
  private Date updatedAt;

  @Nullable
  @JsonProperty("NotificationSettings")
  private NotificationSettings notificationSettings;

  @Nullable
  @JsonProperty("Settings")
  private CallSettings settings;
}
