package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateBlockListRequest {

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("words")
  private List<String> words;

  @Nullable
  @JsonProperty("type")
  private String type;
}
