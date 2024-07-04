package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOGResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  /** URL of detected video or audio */
  @Nullable
  @JsonProperty("asset_url")
  private String assetUrl;

  @Nullable
  @JsonProperty("author_icon")
  private String authorIcon;

  /** og:site */
  @Nullable
  @JsonProperty("author_link")
  private String authorLink;

  /** og:site_name */
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

  /** URL of detected image */
  @Nullable
  @JsonProperty("image_url")
  private String imageUrl;

  /** extracted url from the text */
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

  /** og:description */
  @Nullable
  @JsonProperty("text")
  private String text;

  /** URL of detected thumb image */
  @Nullable
  @JsonProperty("thumb_url")
  private String thumbUrl;

  /** og:title */
  @Nullable
  @JsonProperty("title")
  private String title;

  /** og:url */
  @Nullable
  @JsonProperty("title_link")
  private String titleLink;

  /** Attachment type, could be empty, image, audio or video */
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
