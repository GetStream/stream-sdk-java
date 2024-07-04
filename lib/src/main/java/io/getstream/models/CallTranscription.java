package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallTranscription {

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
