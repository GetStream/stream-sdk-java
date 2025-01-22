package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListExternalStorageResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("external_storages")
  private Map<String, ExternalStorageResponse> externalStorages;
}
