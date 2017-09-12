package com.igor.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.igor.latte.delegates.LatteDelegate;
import com.igor.latte.ec.R;
import com.igor.latte.ui.launcher.LauncherHolderCreator;
import com.igor.latte.ui.launcher.ScrollLauncherTag;
import com.igor.latte.utils.storage.LattePreference;

import java.util.ArrayList;

/**
 * Created by Igor on 2017/9/12.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;

    @SuppressWarnings("unchecked")
    private final ArrayList<Integer> INTEGERS = new ArrayList<Integer>() {{
        add(R.mipmap.launcher_01);
        add(R.mipmap.launcher_02);
        add(R.mipmap.launcher_03);
        add(R.mipmap.launcher_04);
        add(R.mipmap.launcher_05);
    }};

    private void initBanner() {
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{
                        R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true);
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {

        // 如果点击的是最后一个
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            // 检查登录的状态
        }
    }
}
