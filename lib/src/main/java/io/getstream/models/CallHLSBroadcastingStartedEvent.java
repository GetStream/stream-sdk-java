package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallHLSBroadcastingStartedEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("hls_playlist_url")
  private String hlsPlaylistUrl;

  @JsonProperty("call")
  private CallResponse call;

  @JsonProperty("type")
  private String type;
}
