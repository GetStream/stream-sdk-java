package io.getstream.services.framework;

import io.getstream.exceptions.StreamException;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponse;
import java.io.IOException;
import java.util.Date;
import java.util.function.Consumer;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StreamServiceExecutor {
  public <T> StreamResponse<T> execute(Call<T> call) throws StreamException {
    try {
      Response<T> response = call.execute();
      if (response.isSuccessful()) {
        return enrichResponse(response);
      }
      throw StreamException.build(response);
    } catch (IOException e) {
      throw StreamException.build(e);
    }
  }

  public <T> void executeAsync(
      Call<T> call, Consumer<StreamResponse<T>> onSuccess, Consumer<StreamException> onError) {
    call.enqueue(
        new Callback<T>() {
          @Override
          public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
              if (onSuccess != null) {
                onSuccess.accept(enrichResponse(response));
              }
            } else if (onError != null) {
              onError.accept(StreamException.build(response));
            }
          }

          @Override
          public void onFailure(Call<T> call, Throwable throwable) {
            if (onError != null) {
              onError.accept(StreamException.build(throwable));
            }
          }
        });
  }

  private <T> StreamResponse<T> enrichResponse(Response<T> response) {
    T result = response.body();

    StreamResponse<T> streamResponse = new StreamResponse<>();
    streamResponse.setData(result);

    Headers headers = response.headers();
    RateLimit rateLimit = new RateLimit();
    if (headers.get("X-Ratelimit-Limit") != null) {
      rateLimit.setLimit(Integer.parseInt(headers.get("X-Ratelimit-Limit")));
    }

    if (headers.get("X-Ratelimit-Remaining") != null) {
      rateLimit.setRemaining(Integer.parseInt(headers.get("X-Ratelimit-Remaining")));
    }

    if (headers.get("X-Ratelimit-Reset") != null) {
      rateLimit.setReset(new Date(Long.parseLong(headers.get("X-Ratelimit-Reset")) * 1000));
    }
    streamResponse.setRateLimit(rateLimit);

    return streamResponse;
  }
}
