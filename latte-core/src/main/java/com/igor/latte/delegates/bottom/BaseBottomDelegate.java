package com.igor.latte.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.igor.latte.R;
import com.igor.latte.R2;
import com.igor.latte.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Igor on 2017/9/21.
 */

public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    /**
     * 底部item的集合
     */
    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();

    /**
     * fragment的集合，与每个底部item对应
     */
    private final ArrayList<BottomItemDelegate> ITEMS_DELEGATES = new ArrayList<>();

    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    /**
     * 第一次进入显示的界面索引
     */
    private int mIndexDelegate = 0;
    private int mCurrentDelegate = 0;
    private int mClickedColor = Color.RED;

    /**
     * 底部item的容器
     */
    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat mBottomBar = null;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> getItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    public abstract int getIndexDelegate();

    @ColorInt
    public abstract int getClickedColor();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIndexDelegate = getIndexDelegate();
        mClickedColor = getClickedColor();

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = getItems(builder);
        ITEMS.putAll(items);

        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            TAB_BEANS.add(item.getKey());
            ITEMS_DELEGATES.add(item.getValue());
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        /**
         * 初始化底部的items
         */
        final int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout, mBottomBar);
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);

            // 设置每个item的点击事件
            item.setTag(i);
            item.setOnClickListener(this);

            final IconTextView icon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView title = (AppCompatTextView) item.getChildAt(1);
            final BottomTabBean bean = TAB_BEANS.get(i);
            icon.setText(bean.getIcon());
            title.setText(bean.getTitle());

            if (i == mIndexDelegate) {
                icon.setTextColor(mClickedColor);
                title.setTextColor(mClickedColor);
            }
        }

        /**
         * 初始化fragments
         */
        final SupportFragment[] deleteArray = ITEMS_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mIndexDelegate, deleteArray);


    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) v;
        final IconTextView icon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView title = (AppCompatTextView) item.getChildAt(1);
        icon.setTextColor(mClickedColor);
        title.setTextColor(mClickedColor);

        // tag是点击后delegate的位置，mCurrentDelegate是点击前交互delagate的位置
        showHideFragment(ITEMS_DELEGATES.get(tag), ITEMS_DELEGATES.get(mCurrentDelegate));
        mCurrentDelegate = tag;
    }


    private void resetColor() {
        final int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            final IconTextView icon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView title = (AppCompatTextView) item.getChildAt(1);
            icon.setTextColor(Color.GRAY);
            title.setTextColor(Color.GRAY);
        }
    }
}
