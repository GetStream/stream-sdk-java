package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EgressRTMPResponse {

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("started_at")
  private Date startedAt;

  @Nullable
  @JsonProperty("stream_key")
  private String streamKey;

  @Nullable
  @JsonProperty("stream_url")
  private String streamUrl;
}
