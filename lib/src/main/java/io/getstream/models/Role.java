package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  /** Whether this is a custom role or built-in */
  @NotNull
  @JsonProperty("custom")
  private Boolean custom;

  /** Unique role name */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  /**
   * List of scopes where this role is currently present. `.app` means that role is present in
   * app-level grants
   */
  @NotNull
  @JsonProperty("scopes")
  private List<String> scopes;
}
