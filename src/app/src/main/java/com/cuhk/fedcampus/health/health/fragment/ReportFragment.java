package com.cuhk.fedcampus.health.health.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cuhk.fedcampus.health.health.HealthKitDataControllerActivity;
import com.cuhk.fedcampus.health.health.Login;
import com.cuhk.fedcampus.health.utils.UnAuth;
import com.huawei.health.demo.R;
import com.huawei.hms.common.api.Response;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.ResponseHandlerInterface;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.ResponseHandler;
import cz.msebera.android.httpclient.cookie.Cookie;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static boolean isLogIn = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_record, container, false);

        Button exerciseRecord = (Button) view.findViewById(R.id.exercise_report);

        exerciseRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                if (!isLogIn) {
//            login
                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                } else {
//            redirect to the Huawei Page
                    Intent intent = new Intent(getActivity(), HealthKitDataControllerActivity.class);
                    startActivity(intent);
                }

            }
        });

        Button healthReportButton = (Button) view.findViewById(R.id.health_report);

        healthReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncHttpClient myClient = new AsyncHttpClient();
                PersistentCookieStore myCookieStore = new PersistentCookieStore(getActivity().getApplicationContext());
                myClient.setCookieStore(myCookieStore);
                List<Cookie> a = myCookieStore.getCookies();
//                for (int i = 0 ; i < a.size(); i ++){
//                    Cookie c = a.get(i);
//                    c.getName();
//                }
                myClient.get("http://dku-vcm-2630.vm.duke.edu:8005/api/test", new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        System.out.println("success");

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Intent i = new Intent(getActivity(), Login.class);
                        startActivity(i);
                    }
                } );



            }
        });


        return view;
    }
}