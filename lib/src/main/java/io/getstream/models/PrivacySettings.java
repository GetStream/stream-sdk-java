package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrivacySettings {

  @Nullable
  @JsonProperty("read_receipts")
  private ReadReceipts readReceipts;

  @Nullable
  @JsonProperty("typing_indicators")
  private TypingIndicators typingIndicators;
}
