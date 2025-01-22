package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallTranscription {

  @JsonProperty("end_time")
  private Date endTime;

  @JsonProperty("filename")
  private String filename;

  @JsonProperty("start_time")
  private Date startTime;

  @JsonProperty("url")
  private String url;
}
