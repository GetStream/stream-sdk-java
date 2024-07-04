package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("asset_url")
  private String assetUrl;

  @Nullable
  @JsonProperty("author_icon")
  private String authorIcon;

  @Nullable
  @JsonProperty("author_link")
  private String authorLink;

  @Nullable
  @JsonProperty("author_name")
  private String authorName;

  @Nullable
  @JsonProperty("color")
  private String color;

  @Nullable
  @JsonProperty("fallback")
  private String fallback;

  @Nullable
  @JsonProperty("footer")
  private String footer;

  @Nullable
  @JsonProperty("footer_icon")
  private String footerIcon;

  @Nullable
  @JsonProperty("image_url")
  private String imageUrl;

  @Nullable
  @JsonProperty("og_scrape_url")
  private String ogScrapeUrl;

  @Nullable
  @JsonProperty("original_height")
  private Integer originalHeight;

  @Nullable
  @JsonProperty("original_width")
  private Integer originalWidth;

  @Nullable
  @JsonProperty("pretext")
  private String pretext;

  @Nullable
  @JsonProperty("text")
  private String text;

  @Nullable
  @JsonProperty("thumb_url")
  private String thumbUrl;

  @Nullable
  @JsonProperty("title")
  private String title;

  @Nullable
  @JsonProperty("title_link")
  private String titleLink;

  /** Attachment type (e.g. image, video, url, poll) */
  @Nullable
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("actions")
  private List<Action> actions;

  @Nullable
  @JsonProperty("fields")
  private List<Field> fields;

  @Nullable
  @JsonProperty("giphy")
  private Images giphy;
}
