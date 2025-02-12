package io.getstream.models.framework;

import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class StreamResponse<T> {
  private String duration;
  @Setter private RateLimit rateLimit;
  @Setter private T data;

  public String getDuration() {
    return duration;
  }

  public void setDuration(@NotNull String duration) {
    this.duration = duration;
  }

  public RateLimit getRateLimit() {
    return rateLimit;
  }

  public T getData() {
    return data;
  }
}
