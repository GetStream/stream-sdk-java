package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageUpdate {

  @Nullable
  @JsonProperty("old_text")
  private String oldText;

  @Nullable
  @JsonProperty("change_set")
  private MessageChangeSet changeSet;
}
