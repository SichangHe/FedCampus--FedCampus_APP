package com.cuhk.fedcampus.health.health;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.huawei.health.demo.R;

public class ExerciseReportActivity extends Activity {
    private static final String TAG = "Exercise Report";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate ExerciseReportActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_report);
    }
}