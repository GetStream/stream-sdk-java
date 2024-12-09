package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageUpdate {

  @Nullable
  @JsonProperty("old_text")
  private String oldText;

  @Nullable
  @JsonProperty("change_set")
  private MessageChangeSet changeSet;
}
