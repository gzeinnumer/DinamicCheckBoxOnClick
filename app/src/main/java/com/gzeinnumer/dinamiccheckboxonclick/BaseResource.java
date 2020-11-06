package com.gzeinnumer.dinamiccheckboxonclick;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BaseResource<T> {

    @Nullable
    final public T data;

    public BaseResource(@Nullable T data) {
        this.data = data;
    }

    public static <T> BaseResource<T> success(@NonNull String msg, @Nullable T data) {
        return new BaseResource<>(data);
    }
}
