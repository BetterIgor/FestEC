package com.igor.festec.example.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.igor.festec.example.R;
import com.igor.latte.delegates.LatteDelegate;

/**
 * Created by Igor on 2017/9/1.
 */

public class MainDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
