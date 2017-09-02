package com.igor.festec.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.igor.festec.example.delegates.MainDelegate;

/**
 * Created by Igor on 2017/9/2.
 */

public class TestActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainDelegate.testRestClient();
    }
}
