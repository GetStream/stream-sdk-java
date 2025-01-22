package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Label {

  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("harm_labels")
  private List<String> harmLabels;

  @Nullable
  @JsonProperty("phrase_list_ids")
  private List<Integer> phraseListIds;
}
