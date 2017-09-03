package com.igor.festec.example.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.igor.festec.example.R;
import com.igor.latte.app.Latte;
import com.igor.latte.delegates.LatteDelegate;
import com.igor.latte.net.RestClient;
import com.igor.latte.net.callback.IError;
import com.igor.latte.net.callback.IFailure;
import com.igor.latte.net.callback.ISuccess;

/**
 * Created by Igor on 2017/9/1.
 */

public class MainDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }


    public void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("interceptor response: ", response);
                        Toast.makeText(Latte.getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
