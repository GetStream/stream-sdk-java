package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SendMessageRequest {

  @NotNull
  @JsonProperty("message")
  private MessageRequest message;

  @Nullable
  @JsonProperty("force_moderation")
  private Boolean forceModeration;

  @Nullable
  @JsonProperty("keep_channel_hidden")
  private Boolean keepChannelHidden;

  @Nullable
  @JsonProperty("pending")
  private Boolean pending;

  @Nullable
  @JsonProperty("skip_enrich_url")
  private Boolean skipEnrichUrl;

  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;

  @Nullable
  @JsonProperty("pending_message_metadata")
  private Map<String, String> pendingMessageMetadata;
}
