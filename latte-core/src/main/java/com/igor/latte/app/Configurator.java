package com.igor.latte.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Igor on 2017/8/31.
 */

public class Configurator {

    private static final Map<String, Object> LATTE_CONFIGS = new HashMap<>();

    private static final List<IconFontDescriptor> ICON_FONTS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
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
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuratin is not ready, call configure() please");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

    final Map<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
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
}
