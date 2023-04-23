package com.cuhk.fedcampus.health.health;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.huawei.health.demo.R;

public class Login  extends Activity {

    private static final String TAG = "Login Page";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_x);
    }
}
