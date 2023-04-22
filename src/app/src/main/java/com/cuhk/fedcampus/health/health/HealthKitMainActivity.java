/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *   http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cuhk.fedcampus.health.health;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cuhk.fedcampus.health.health.auth.HealthKitAuthActivity;
import com.google.android.material.tabs.TabLayout;
import com.huawei.health.demo.R;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.service.AccountAuthService;
import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;

/**
 * functional description
 *
 * @since 2020-03-19
 */
public class HealthKitMainActivity extends AppCompatActivity {
    private static final String TAG = "HealthKitMainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthkit_main);


    }

    /**
     * Data Controller
     *
     * @param view UI object
     */
    public void hihealthDataControllerOnclick(View view) {
        Intent intent = new Intent(this, HealthKitDataControllerActivity.class);
        startActivity(intent);
    }

    /**
     * Setting Controller
     *
     * @param view UI object
     */
    public void hihealthSettingControllerOnclick(View view) {
        Intent intent = new Intent(this, HealthKitSettingControllerActivity.class);
        startActivity(intent);
    }

    /**
     * Auto Recorder
     *
     * @param view UI object
     */
    public void hihealthAutoRecorderOnClick(View view) {
        Intent intent = new Intent(this, HealthKitAutoRecorderControllerActivity.class);
        startActivity(intent);
    }

    /**
     * Activity Records Controller
     *
     * @param view UI object
     */
    public void hihealthActivityRecordOnClick(View view) {
        Intent intent = new Intent(this, HealthKitActivityRecordControllerActivity.class);
        startActivity(intent);
    }

    /**
     * signing In and applying for Scopes, and enable the health app authorization.
     *
     * @param view UI object
     */
    public void onLoginClick(View view) {
        Intent intent = new Intent(this, HealthKitAuthActivity.class);
        startActivity(intent);
    }

    /**
     * health Records Controller
     *
     * @param view UI object
     */
    public void hihealthHealthControllerOnclick(View view) {
        Intent intent = new Intent(this, HealthKitHealthRecordControllerActivity.class);
        startActivity(intent);
    }

    public void testOnClick(View view) {

        TextView t = findViewById(R.id.abc);




        String val = t.getText().toString();
        System.out.println(val);

        System.out.println(111);

        AsyncHttpClient myClient = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        myClient.setCookieStore(myCookieStore);

        System.out.println(myCookieStore.getCookies());

        BasicClientCookie newCookie = new BasicClientCookie("cookiesarea", "awesomea");
        newCookie.setVersion(2);
        newCookie.setDomain("mydomain.com");
        newCookie.setPath("/");
        myCookieStore.addCookie(newCookie);

        System.out.println(myCookieStore.getCookies());

        myClient.get("http://dku-vcm-2630.vm.duke.edu:8004/api/list?limit=10&start=1",
                new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                        // called when response HTTP status is "200 OK"
                        System.out.println(statusCode);
                        System.out.println(new String(response));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                }



                );

    }

    /**
     * To improve privacy security, your app should allow users to cancel authorization.
     * After calling this function, you need to call login and authorize again.
     *
     * @param view (indicating a UI object)
     */
    public void cancelScope(View view) {
        AccountAuthParams authParams =
            new AccountAuthParamsHelper().setAccessToken().setScopeList(new ArrayList<>()).createParams();
        final AccountAuthService authService = AccountAuthManager.getService(getApplicationContext(), authParams);
        authService.cancelAuthorization().addOnCompleteListener(new TaskOnCompleteListener());
    }

    private static class TaskOnCompleteListener implements OnCompleteListener<Void> {
        @Override
        public void onComplete(Task<Void> task) {
            if (task.isSuccessful()) {
                // Processing after successful cancellation of authorization
                Log.i(TAG, "cancelAuthorization success");
            } else {
                // Processing after failed cancellation of authorization
                Exception exception = task.getException();
                Log.i(TAG, "cancelAuthorization fail");
                if (exception instanceof ApiException) {
                    int statusCode = ((ApiException) exception).getStatusCode();
                    Log.e(TAG, "cancelAuthorization fail for errorCode: " + statusCode);
                }
            }
        }
    }
}
