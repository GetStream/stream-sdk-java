package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateBlockListRequest {

  @JsonProperty("name")
  private String name;

  @JsonProperty("words")
  private List<String> words;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("type")
  private String type;
}
