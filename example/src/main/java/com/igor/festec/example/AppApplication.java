package com.igor.festec.example;

import android.app.Application;

import com.igor.latte.app.Latte;
import com.igor.latte.ec.database.DatabaseManager;
import com.igor.latte.ec.icon.FontEcModule;
import com.igor.latte.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Igor on 2017/8/31.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withIconFont(new FontEcModule())   // 自定义资源, 使用：IconTextView，text:icon-coffee
                .withIconFont(new FontAwesomeModule())  // 初始化第三方资源
                .withApiHost("http:\\127.0.0.1")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
    }
}
