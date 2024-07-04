package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

public enum OwnCapability {
  @JsonProperty("block-users")
  BLOCK_USERS,

  @JsonProperty("change-max-duration")
  CHANGE_MAX_DURATION,

  @JsonProperty("create-call")
  CREATE_CALL,

  @JsonProperty("create-reaction")
  CREATE_REACTION,

  @JsonProperty("enable-noise-cancellation")
  ENABLE_NOISE_CANCELLATION,

  @JsonProperty("end-call")
  END_CALL,

  @JsonProperty("join-backstage")
  JOIN_BACKSTAGE,

  @JsonProperty("join-call")
  JOIN_CALL,

  @JsonProperty("join-ended-call")
  JOIN_ENDED_CALL,

  @JsonProperty("mute-users")
  MUTE_USERS,

  @JsonProperty("pin-for-everyone")
  PIN_FOR_EVERYONE,

  @JsonProperty("read-call")
  READ_CALL,

  @JsonProperty("remove-call-member")
  REMOVE_CALL_MEMBER,

  @JsonProperty("screenshare")
  SCREENSHARE,

  @JsonProperty("send-audio")
  SEND_AUDIO,

  @JsonProperty("send-video")
  SEND_VIDEO,

  @JsonProperty("start-broadcast-call")
  START_BROADCAST_CALL,

  @JsonProperty("start-record-call")
  START_RECORD_CALL,

  @JsonProperty("start-transcription-call")
  START_TRANSCRIPTION_CALL,

  @JsonProperty("stop-broadcast-call")
  STOP_BROADCAST_CALL,

  @JsonProperty("stop-record-call")
  STOP_RECORD_CALL,

  @JsonProperty("stop-transcription-call")
  STOP_TRANSCRIPTION_CALL,

  @JsonProperty("update-call")
  UPDATE_CALL,

  @JsonProperty("update-call-member")
  UPDATE_CALL_MEMBER,

  @JsonProperty("update-call-permissions")
  UPDATE_CALL_PERMISSIONS,

  @JsonProperty("update-call-settings")
  UPDATE_CALL_SETTINGS
}
