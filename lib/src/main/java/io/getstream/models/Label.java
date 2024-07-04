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
public class Label {

  @NotNull
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("harm_labels")
  private List<String> harmLabels;

  @Nullable
  @JsonProperty("phrase_list_ids")
  private List<Integer> phraseListIds;
}
