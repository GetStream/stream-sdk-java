package io.getstream.models.framework;

public interface StreamResponseWithRateLimit {
  RateLimit getRateLimit();

  void setRateLimit(RateLimit rateLimit);
}
