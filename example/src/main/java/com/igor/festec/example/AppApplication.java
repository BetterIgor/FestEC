package com.igor.festec.example;

import android.app.Application;

import com.igor.latte.app.Latte;
import com.igor.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Igor on 2017/8/31.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 类似构造者模式，方便使用
        Latte.init(this)
                .withIconFont(new FontEcModule())   // 自定义资源, 使用：IconTextView，text:icon-coffee
                .withIconFont(new FontAwesomeModule())  // 初始化第三方资源
                .withApiHost("http:\\127.0.0.1")
                .configure();
    }
}
