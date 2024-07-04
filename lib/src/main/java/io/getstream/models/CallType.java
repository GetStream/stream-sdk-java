package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallType {

  @NotNull
  @JsonProperty("AppPK")
  private Integer appPK;

  @NotNull
  @JsonProperty("CreatedAt")
  private Date createdAt;

  @NotNull
  @JsonProperty("ExternalStorage")
  private String externalStorage;

  @NotNull
  @JsonProperty("Name")
  private String name;

  @NotNull
  @JsonProperty("PK")
  private Integer pK;

  @NotNull
  @JsonProperty("UpdatedAt")
  private Date updatedAt;

  @Nullable
  @JsonProperty("NotificationSettings")
  private NotificationSettings notificationSettings;

  @Nullable
  @JsonProperty("Settings")
  private CallSettings settings;
}
