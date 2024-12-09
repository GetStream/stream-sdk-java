package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListExternalStorageResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("external_storages")
  private Map<String, ExternalStorageResponse> externalStorages;
}
