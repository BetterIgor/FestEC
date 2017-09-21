package com.igor.festec.example.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.igor.latte.activities.ProxyActivity;
import com.igor.latte.delegates.LatteDelegate;
import com.igor.latte.ec.launcher.LauncherDelegate;
import com.igor.latte.ec.main.EcBottomDelegate;
import com.igor.latte.ec.sign.ISignListener;
import com.igor.latte.ec.sign.SignInDelegate;
import com.igor.latte.ui.launcher.ILauncherListener;
import com.igor.latte.ui.launcher.OnLauncherFinishTag;

public class MainActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        startWithPop(new EcBottomDelegate());
        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        startWithPop(new EcBottomDelegate());
        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(getApplicationContext(), "启动结束，已登录", Toast.LENGTH_SHORT).show();
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(getApplicationContext(), "启动结束，未登录", Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
