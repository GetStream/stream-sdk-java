package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockList {

  /** Block list name */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** Block list type. */
  @NotNull
  @JsonProperty("type")
  private String type;

  /** List of words to block */
  @NotNull
  @JsonProperty("words")
  private List<String> words;

  /** Date/time of creation */
  @Nullable
  @JsonProperty("created_at")
  private Date createdAt;

  /** Date/time of the last update */
  @Nullable
  @JsonProperty("updated_at")
  private Date updatedAt;
}
