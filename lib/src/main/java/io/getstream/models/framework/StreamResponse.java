package io.getstream.models.framework;

import org.jetbrains.annotations.NotNull;

public class StreamResponse<T> {
  private String duration;
  private RateLimit rateLimit;
  private T data;

  public String getDuration() {
    return duration;
  }

  public void setDuration(@NotNull String duration) {
    this.duration = duration;
  }

  public RateLimit getRateLimit() {
    return rateLimit;
  }

  public void setRateLimit(RateLimit rateLimit) {
    this.rateLimit = rateLimit;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
