package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChannelOwnCapability {
  @JsonProperty("ban-channel-members")
  BAN_CHANNEL_MEMBERS,

  @JsonProperty("cast-poll-vote")
  CAST_POLL_VOTE,

  @JsonProperty("connect-events")
  CONNECT_EVENTS,

  @JsonProperty("create-attachment")
  CREATE_ATTACHMENT,

  @JsonProperty("create-call")
  CREATE_CALL,

  @JsonProperty("delete-any-message")
  DELETE_ANY_MESSAGE,

  @JsonProperty("delete-channel")
  DELETE_CHANNEL,

  @JsonProperty("delete-own-message")
  DELETE_OWN_MESSAGE,

  @JsonProperty("flag-message")
  FLAG_MESSAGE,

  @JsonProperty("freeze-channel")
  FREEZE_CHANNEL,

  @JsonProperty("join-call")
  JOIN_CALL,

  @JsonProperty("join-channel")
  JOIN_CHANNEL,

  @JsonProperty("leave-channel")
  LEAVE_CHANNEL,

  @JsonProperty("mute-channel")
  MUTE_CHANNEL,

  @JsonProperty("pin-message")
  PIN_MESSAGE,

  @JsonProperty("query-poll-votes")
  QUERY_POLL_VOTES,

  @JsonProperty("quote-message")
  QUOTE_MESSAGE,

  @JsonProperty("read-events")
  READ_EVENTS,

  @JsonProperty("search-messages")
  SEARCH_MESSAGES,

  @JsonProperty("send-custom-events")
  SEND_CUSTOM_EVENTS,

  @JsonProperty("send-links")
  SEND_LINKS,

  @JsonProperty("send-message")
  SEND_MESSAGE,

  @JsonProperty("send-poll")
  SEND_POLL,

  @JsonProperty("send-reaction")
  SEND_REACTION,

  @JsonProperty("send-reply")
  SEND_REPLY,

  @JsonProperty("send-typing-events")
  SEND_TYPING_EVENTS,

  @JsonProperty("set-channel-cooldown")
  SET_CHANNEL_COOLDOWN,

  @JsonProperty("skip-slow-mode")
  SKIP_SLOW_MODE,

  @JsonProperty("slow-mode")
  SLOW_MODE,

  @JsonProperty("typing-events")
  TYPING_EVENTS,

  @JsonProperty("update-any-message")
  UPDATE_ANY_MESSAGE,

  @JsonProperty("update-channel")
  UPDATE_CHANNEL,

  @JsonProperty("update-channel-members")
  UPDATE_CHANNEL_MEMBERS,

  @JsonProperty("update-own-message")
  UPDATE_OWN_MESSAGE,

  @JsonProperty("update-thread")
  UPDATE_THREAD,

  @JsonProperty("upload-file")
  UPLOAD_FILE
}
