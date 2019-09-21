package pl.dexbytes.forexdemo.net;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApiResponse {
    private final Status mStatus;
    @Nullable
    private final Object mResult;
    @Nullable
    private final Throwable mError;

    public ApiResponse(Status status, @Nullable Object result, @Nullable Throwable error) {
        mStatus = status;
        mResult = result;
        mError = error;
    }

    public Status getStatus() {
        return mStatus;
    }

    @Nullable
    public Object getResult() {
        return mResult;
    }

    @Nullable
    public Throwable getError() {
        return mError;
    }

    public static ApiResponse loading() {
        return new ApiResponse(Status.LOADING, null, null);
    }

    public static ApiResponse success(@NonNull Object result) {
        return new ApiResponse(Status.SUCCESS, result, null);
    }

    public static ApiResponse error(@NonNull Throwable throwable) {
        return new ApiResponse(Status.ERROR, null, throwable);
    }
}
