package com.igor.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

/**
 * Created by Igor on 2017/8/31.
 */

public class Configurator {

    private static final Map<Object, Object> LATTE_CONFIGS = new HashMap<>();

    private static final List<IconFontDescriptor> ICON_FONTS = new ArrayList<>();

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

    // 静态内部类，只在首次加载，线程安全的单例，目前最棒的写法
    private static class Holder {
        private static Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final void configure() {
        initIconFonts();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuratin is not ready, call configure() please");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

    final Map<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    private void initIconFonts() {
        if (ICON_FONTS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICON_FONTS.get(0));
            final int len = ICON_FONTS.size();

            for (int i = 1; i < len; i++) {
                initializer.with(ICON_FONTS.get(i));
            }
        }
    }

    public Configurator withIconFont(IconFontDescriptor descriptor) {
        ICON_FONTS.add(descriptor);
        return this;
    }

    public Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
}
