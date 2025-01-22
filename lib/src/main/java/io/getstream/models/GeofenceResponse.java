package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GeofenceResponse {

  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("description")
  private String description;

  @Nullable
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("country_codes")
  private List<String> countryCodes;
}
