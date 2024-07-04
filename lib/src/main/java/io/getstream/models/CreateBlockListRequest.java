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
public class CreateBlockListRequest {

  /** Block list name */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** List of words to block */
  @NotNull
  @JsonProperty("words")
  private List<String> words;

  /** Block list type. */
  @Nullable
  @JsonProperty("type")
  private String type;
}
