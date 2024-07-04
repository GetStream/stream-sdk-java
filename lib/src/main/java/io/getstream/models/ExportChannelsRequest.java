package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportChannelsRequest {

  /** Export options for channels */
  @NotNull
  @JsonProperty("channels")
  private List<ChannelExport> channels;

  /** Set if deleted message text should be cleared */
  @Nullable
  @JsonProperty("clear_deleted_message_text")
  private Boolean clearDeletedMessageText;

  @Nullable
  @JsonProperty("export_users")
  private Boolean exportUsers;

  /** Set if you want to include deleted channels */
  @Nullable
  @JsonProperty("include_soft_deleted_channels")
  private Boolean includeSoftDeletedChannels;

  /** Set if you want to include truncated messages */
  @Nullable
  @JsonProperty("include_truncated_messages")
  private Boolean includeTruncatedMessages;

  @Nullable
  @JsonProperty("version")
  private String version;
}
