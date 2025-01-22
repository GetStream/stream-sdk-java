package io.getstream.models;

public class ChannelOwnCapability {
  private final String value;

  private ChannelOwnCapability(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ChannelOwnCapability that = (ChannelOwnCapability) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  public static ChannelOwnCapability BAN_CHANNEL_MEMBERS =
      new ChannelOwnCapability("ban-channel-members");
  public static ChannelOwnCapability CAST_POLL_VOTE = new ChannelOwnCapability("cast-poll-vote");
  public static ChannelOwnCapability CONNECT_EVENTS = new ChannelOwnCapability("connect-events");
  public static ChannelOwnCapability CREATE_ATTACHMENT =
      new ChannelOwnCapability("create-attachment");
  public static ChannelOwnCapability DELETE_ANY_MESSAGE =
      new ChannelOwnCapability("delete-any-message");
  public static ChannelOwnCapability DELETE_CHANNEL = new ChannelOwnCapability("delete-channel");
  public static ChannelOwnCapability DELETE_OWN_MESSAGE =
      new ChannelOwnCapability("delete-own-message");
  public static ChannelOwnCapability FLAG_MESSAGE = new ChannelOwnCapability("flag-message");
  public static ChannelOwnCapability FREEZE_CHANNEL = new ChannelOwnCapability("freeze-channel");
  public static ChannelOwnCapability JOIN_CHANNEL = new ChannelOwnCapability("join-channel");
  public static ChannelOwnCapability LEAVE_CHANNEL = new ChannelOwnCapability("leave-channel");
  public static ChannelOwnCapability MUTE_CHANNEL = new ChannelOwnCapability("mute-channel");
  public static ChannelOwnCapability PIN_MESSAGE = new ChannelOwnCapability("pin-message");
  public static ChannelOwnCapability QUERY_POLL_VOTES =
      new ChannelOwnCapability("query-poll-votes");
  public static ChannelOwnCapability QUOTE_MESSAGE = new ChannelOwnCapability("quote-message");
  public static ChannelOwnCapability READ_EVENTS = new ChannelOwnCapability("read-events");
  public static ChannelOwnCapability SEARCH_MESSAGES = new ChannelOwnCapability("search-messages");
  public static ChannelOwnCapability SEND_CUSTOM_EVENTS =
      new ChannelOwnCapability("send-custom-events");
  public static ChannelOwnCapability SEND_LINKS = new ChannelOwnCapability("send-links");
  public static ChannelOwnCapability SEND_MESSAGE = new ChannelOwnCapability("send-message");
  public static ChannelOwnCapability SEND_POLL = new ChannelOwnCapability("send-poll");
  public static ChannelOwnCapability SEND_REACTION = new ChannelOwnCapability("send-reaction");
  public static ChannelOwnCapability SEND_REPLY = new ChannelOwnCapability("send-reply");
  public static ChannelOwnCapability SEND_TYPING_EVENTS =
      new ChannelOwnCapability("send-typing-events");
  public static ChannelOwnCapability SET_CHANNEL_COOLDOWN =
      new ChannelOwnCapability("set-channel-cooldown");
  public static ChannelOwnCapability SKIP_SLOW_MODE = new ChannelOwnCapability("skip-slow-mode");
  public static ChannelOwnCapability SLOW_MODE = new ChannelOwnCapability("slow-mode");
  public static ChannelOwnCapability TYPING_EVENTS = new ChannelOwnCapability("typing-events");
  public static ChannelOwnCapability UPDATE_ANY_MESSAGE =
      new ChannelOwnCapability("update-any-message");
  public static ChannelOwnCapability UPDATE_CHANNEL = new ChannelOwnCapability("update-channel");
  public static ChannelOwnCapability UPDATE_CHANNEL_MEMBERS =
      new ChannelOwnCapability("update-channel-members");
  public static ChannelOwnCapability UPDATE_OWN_MESSAGE =
      new ChannelOwnCapability("update-own-message");
  public static ChannelOwnCapability UPDATE_THREAD = new ChannelOwnCapability("update-thread");
  public static ChannelOwnCapability UPLOAD_FILE = new ChannelOwnCapability("upload-file");
}
