package com.igor.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.igor.latte.delegates.bottom.BottomItemDelegate;
import com.igor.latte.ec.R;

/**
 * Created by Igor on 2017/9/21.
 */

public class SortDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
