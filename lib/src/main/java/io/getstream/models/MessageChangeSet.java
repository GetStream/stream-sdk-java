package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageChangeSet {

  @NotNull
  @JsonProperty("attachments")
  private Boolean attachments;

  @NotNull
  @JsonProperty("custom")
  private Boolean custom;

  @NotNull
  @JsonProperty("html")
  private Boolean html;

  @NotNull
  @JsonProperty("mentioned_user_ids")
  private Boolean mentionedUserIds;

  @NotNull
  @JsonProperty("mml")
  private Boolean mml;

  @NotNull
  @JsonProperty("pin")
  private Boolean pin;

  @NotNull
  @JsonProperty("quoted_message_id")
  private Boolean quotedMessageID;

  @NotNull
  @JsonProperty("silent")
  private Boolean silent;

  @NotNull
  @JsonProperty("text")
  private Boolean text;
}
