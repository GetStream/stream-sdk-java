package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallClosedCaption {

  @JsonProperty("end_time")
  private Date endTime;

  @JsonProperty("speaker_id")
  private String speakerID;

  @JsonProperty("start_time")
  private Date startTime;

  @JsonProperty("text")
  private String text;

  @JsonProperty("user")
  private UserResponse user;
}
