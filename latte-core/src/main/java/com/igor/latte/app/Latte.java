package com.igor.latte.app;

import android.content.Context;

import java.util.Map;

/**
 * Created by Igor on 2017/8/31.
 */

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),
                context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static Map<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
