package com.igor.latte.net.callback;

import com.igor.latte.ui.LatteLoader;
import com.igor.latte.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Igor on 2017/9/2.
 */

public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoaderStyle LOADER_STYLE;

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure,
                            IError error, LoaderStyle loaderStyle) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        stopLoading();
        if (response.isSuccessful() && call.isExecuted() && SUCCESS != null) {
            SUCCESS.onSuccess(response.body());
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        stopLoading();
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onEnd();
        }
    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            LatteLoader.stopLoading();
        }
    }
}
