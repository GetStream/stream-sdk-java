package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageChangeSet {

  @JsonProperty("attachments")
  private Boolean attachments;

  @JsonProperty("custom")
  private Boolean custom;

  @JsonProperty("html")
  private Boolean html;

  @JsonProperty("mentioned_user_ids")
  private Boolean mentionedUserIds;

  @JsonProperty("mml")
  private Boolean mml;

  @JsonProperty("pin")
  private Boolean pin;

  @JsonProperty("quoted_message_id")
  private Boolean quotedMessageID;

  @JsonProperty("silent")
  private Boolean silent;

  @JsonProperty("text")
  private Boolean text;
}
