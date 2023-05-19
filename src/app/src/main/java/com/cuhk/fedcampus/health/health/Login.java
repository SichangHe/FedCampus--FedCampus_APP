package com.cuhk.fedcampus.health.health;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huawei.health.demo.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.impl.cookie.BasicClientCookie;

public class Login extends Activity {

    private static final String TAG = "Login Page";

    private final Login loginPage = this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        Button login = (Button) findViewById(R.id.login);

        Button cancel = (Button) findViewById(R.id.cancel);

        EditText email = (EditText) findViewById(R.id.email);

        EditText pswd = (EditText) findViewById(R.id.pswd);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.i("login email:", email.getText().toString());
                Log.i("login password:",pswd.getText().toString());

                // send http request to the server
                AsyncHttpClient myClient = new AsyncHttpClient();

                PersistentCookieStore myCookieStore = new PersistentCookieStore(getApplicationContext());
                myClient.setCookieStore(myCookieStore);
                System.out.println(myCookieStore.getCookies());

                myClient.addHeader("token","123");

                RequestParams params = new RequestParams();

                params.put("username", email.getText().toString());
                params.put("password", pswd.getText().toString());

                myClient.post("http://dku-vcm-2630.vm.duke.edu:8005/api/login", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] response) {


                        // pares the response to string
                        String res = new String(response, StandardCharsets.UTF_8);

                        try {
                            JSONObject j = new JSONObject(res);

                            BasicClientCookie newCookie = new BasicClientCookie("Authorization", "Token " + j.getString("token") );
                            myCookieStore.addCookie(newCookie);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                        Log.i("response",res);
                        Log.i("cookie", String.valueOf(myCookieStore.getCookies()));
                        Login.this.finish();
                    }


                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        Toast.makeText(Login.this, "Bad credentials", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v){
                loginPage.finish();

            }
        });
    }
}
