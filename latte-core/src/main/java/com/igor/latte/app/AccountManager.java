package com.igor.latte.app;

import com.igor.latte.utils.storage.LattePreference;

/**
 * Created by Igor on 2017/9/14.
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    // 用户登录后的标记
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
