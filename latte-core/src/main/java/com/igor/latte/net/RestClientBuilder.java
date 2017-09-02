package com.igor.latte.net;

import android.content.Context;

import com.igor.latte.net.callback.IError;
import com.igor.latte.net.callback.IFailure;
import com.igor.latte.net.callback.IRequest;
import com.igor.latte.net.callback.ISuccess;
import com.igor.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Igor on 2017/9/2.
 */

public class RestClientBuilder {


    private String mUrl;
    private static Map<String, Object> PARAMS = RestCreator.getParams();
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private IRequest mIRequest;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    RestClientBuilder() {

    }

    public RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public RestClientBuilder params(String key, String value) {
        this.params(key, value);
        return this;
    }

    public RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public RestClientBuilder request(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallPulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure,
                mIError, mBody, mContext, mLoaderStyle);
    }
}
