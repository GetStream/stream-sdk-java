package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PrivacySettings {

  @Nullable
  @JsonProperty("read_receipts")
  private ReadReceipts readReceipts;

  @Nullable
  @JsonProperty("typing_indicators")
  private TypingIndicators typingIndicators;
}
