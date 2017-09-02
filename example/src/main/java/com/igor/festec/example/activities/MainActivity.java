package com.igor.festec.example.activities;

import com.igor.festec.example.delegates.MainDelegate;
import com.igor.latte.activities.ProxyActivity;
import com.igor.latte.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new MainDelegate();
    }
}
