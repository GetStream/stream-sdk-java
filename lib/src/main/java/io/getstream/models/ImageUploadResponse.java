package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  /** URL to the uploaded asset. Should be used to put to `asset_url` attachment field */
  @Nullable
  @JsonProperty("file")
  private String file;

  /**
   * URL of the file thumbnail for supported file formats. Should be put to `thumb_url` attachment
   * field
   */
  @Nullable
  @JsonProperty("thumb_url")
  private String thumbUrl;

  @Nullable
  @JsonProperty("upload_sizes")
  private List<ImageSize> uploadSizes;
}
