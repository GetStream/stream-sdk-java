package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListRecordingsResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("recordings")
  private List<CallRecording> recordings;
}
