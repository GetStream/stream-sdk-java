package io.getstream.models;

public class OwnCapability {
  private final String value;

  private OwnCapability(String value) {
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
    OwnCapability that = (OwnCapability) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  public static OwnCapability BLOCK_USERS = new OwnCapability("block-users");
  public static OwnCapability CHANGE_MAX_DURATION = new OwnCapability("change-max-duration");
  public static OwnCapability CREATE_CALL = new OwnCapability("create-call");
  public static OwnCapability CREATE_REACTION = new OwnCapability("create-reaction");
  public static OwnCapability ENABLE_NOISE_CANCELLATION =
      new OwnCapability("enable-noise-cancellation");
  public static OwnCapability END_CALL = new OwnCapability("end-call");
  public static OwnCapability JOIN_BACKSTAGE = new OwnCapability("join-backstage");
  public static OwnCapability JOIN_CALL = new OwnCapability("join-call");
  public static OwnCapability JOIN_ENDED_CALL = new OwnCapability("join-ended-call");
  public static OwnCapability MUTE_USERS = new OwnCapability("mute-users");
  public static OwnCapability PIN_FOR_EVERYONE = new OwnCapability("pin-for-everyone");
  public static OwnCapability READ_CALL = new OwnCapability("read-call");
  public static OwnCapability REMOVE_CALL_MEMBER = new OwnCapability("remove-call-member");
  public static OwnCapability SCREENSHARE = new OwnCapability("screenshare");
  public static OwnCapability SEND_AUDIO = new OwnCapability("send-audio");
  public static OwnCapability SEND_VIDEO = new OwnCapability("send-video");
  public static OwnCapability START_BROADCAST_CALL = new OwnCapability("start-broadcast-call");
  public static OwnCapability START_CLOSED_CAPTIONS_CALL =
      new OwnCapability("start-closed-captions-call");
  public static OwnCapability START_RECORD_CALL = new OwnCapability("start-record-call");
  public static OwnCapability START_TRANSCRIPTION_CALL =
      new OwnCapability("start-transcription-call");
  public static OwnCapability STOP_BROADCAST_CALL = new OwnCapability("stop-broadcast-call");
  public static OwnCapability STOP_CLOSED_CAPTIONS_CALL =
      new OwnCapability("stop-closed-captions-call");
  public static OwnCapability STOP_RECORD_CALL = new OwnCapability("stop-record-call");
  public static OwnCapability STOP_TRANSCRIPTION_CALL =
      new OwnCapability("stop-transcription-call");
  public static OwnCapability UPDATE_CALL = new OwnCapability("update-call");
  public static OwnCapability UPDATE_CALL_MEMBER = new OwnCapability("update-call-member");
  public static OwnCapability UPDATE_CALL_PERMISSIONS =
      new OwnCapability("update-call-permissions");
  public static OwnCapability UPDATE_CALL_SETTINGS = new OwnCapability("update-call-settings");
}
