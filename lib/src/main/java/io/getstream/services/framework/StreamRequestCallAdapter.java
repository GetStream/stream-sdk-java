package io.getstream.services.framework;

import java.lang.reflect.Type;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.CallAdapter;

public class StreamRequestCallAdapter<T> implements CallAdapter<T, StreamRequest<T>> {
  private final Type responseType;

  public StreamRequestCallAdapter(Type responseType) {
    this.responseType = responseType;
  }

  @NotNull
  @Override
  public Type responseType() {
    return responseType;
  }

  @NotNull
  @Override
  public StreamRequest<T> adapt(@NotNull Call<T> call) {
    return new StreamRequest<>(call);
  }
}
