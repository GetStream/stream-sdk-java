package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("image")
  private String image;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("roles")
  private List<String> roles;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
