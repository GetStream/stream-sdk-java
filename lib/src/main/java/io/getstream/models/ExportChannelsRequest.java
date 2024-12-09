package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ExportChannelsRequest {

  @NotNull
  @JsonProperty("channels")
  private List<ChannelExport> channels;

  @Nullable
  @JsonProperty("clear_deleted_message_text")
  private Boolean clearDeletedMessageText;

  @Nullable
  @JsonProperty("export_users")
  private Boolean exportUsers;

  @Nullable
  @JsonProperty("include_soft_deleted_channels")
  private Boolean includeSoftDeletedChannels;

  @Nullable
  @JsonProperty("include_truncated_messages")
  private Boolean includeTruncatedMessages;

  @Nullable
  @JsonProperty("version")
  private String version;
}
