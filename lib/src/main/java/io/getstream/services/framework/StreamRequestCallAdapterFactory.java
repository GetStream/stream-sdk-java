package io.getstream.services.framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class StreamRequestCallAdapterFactory extends CallAdapter.Factory {
  public static StreamRequestCallAdapterFactory create() {
    return new StreamRequestCallAdapterFactory();
  }

  @Override
  public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
    // Make sure the return type is StreamRequest
    if (getRawType(returnType) != StreamRequest.class) {
      return null;
    }

    // Get the generic type parameter of StreamRequest<T>
    Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);

    return new StreamRequestCallAdapter<>(responseType);
  }
}
