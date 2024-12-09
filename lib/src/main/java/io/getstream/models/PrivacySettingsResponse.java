package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PrivacySettingsResponse {

  @Nullable
  @JsonProperty("read_receipts")
  private ReadReceiptsResponse readReceipts;

  @Nullable
  @JsonProperty("typing_indicators")
  private TypingIndicatorsResponse typingIndicators;
}
