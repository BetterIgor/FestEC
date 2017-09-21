package com.igor.latte.ec.main;

import android.graphics.Color;

import com.igor.latte.delegates.bottom.BaseBottomDelegate;
import com.igor.latte.delegates.bottom.BottomItemDelegate;
import com.igor.latte.delegates.bottom.BottomTabBean;
import com.igor.latte.delegates.bottom.ItemBuilder;
import com.igor.latte.ec.main.index.IndexDelegate;
import com.igor.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Igor on 2017/9/21.
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> getItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
        ITEMS.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        ITEMS.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        ITEMS.put(new BottomTabBean("{fa-compass}", "发现"), new IndexDelegate());
        ITEMS.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        ITEMS.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(ITEMS).build();
    }

    @Override
    public int getIndexDelegate() {
        return 0;
    }

    @Override
    public int getClickedColor() {
        return Color.BLUE;
    }
}
