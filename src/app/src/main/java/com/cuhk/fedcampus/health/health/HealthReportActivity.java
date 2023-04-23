package com.cuhk.fedcampus.health.health;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.huawei.health.demo.R;

public class HealthReportActivity extends Activity {
    private static final String TAG = "Health Report";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate HealthReportActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_report);
    }
}
