package com.igor.festec.example;

import android.app.Application;

import com.igor.latte.app.Latte;

/**
 * Created by Igor on 2017/8/31.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 类似构造者模式，方便使用
        Latte.init(this)
                .withApiHost("http:\\127.0.0.1")
                .configure();
    }
}
