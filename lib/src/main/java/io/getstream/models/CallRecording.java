package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallRecording {

  @NotNull
  @JsonProperty("end_time")
  private Date endTime;

  @NotNull
  @JsonProperty("filename")
  private String filename;

  @NotNull
  @JsonProperty("start_time")
  private Date startTime;

  @NotNull
  @JsonProperty("url")
  private String url;
}
