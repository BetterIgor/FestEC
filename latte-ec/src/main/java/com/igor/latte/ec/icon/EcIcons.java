package com.igor.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by Igor on 2017/8/31.
 */

public enum EcIcons implements Icon{
    icon_coffee('\ue743');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return this.name().replace('_', '-');
    }

    @Override
    public char character() {
        return this.character;
    }
}
