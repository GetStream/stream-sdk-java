package io.getstream.services.framework;

import io.getstream.exceptions.StreamException;
import io.getstream.models.framework.StreamResponse;
import java.util.function.Consumer;
import retrofit2.Call;

public class StreamRequest<T> {
  private final Call<T> originalCall;

  public StreamRequest(Call<T> call) {
    this.originalCall = call;
  }

  public StreamResponse<T> execute() throws StreamException {
    return new StreamServiceExecutor().execute(originalCall);
  }

  public void executeAsync(
      Consumer<StreamResponse<T>> onSuccess, Consumer<StreamException> onError) {
    new StreamServiceExecutor().executeAsync(originalCall, onSuccess, onError);
  }
}
