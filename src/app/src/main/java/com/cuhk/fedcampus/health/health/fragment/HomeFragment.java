package com.cuhk.fedcampus.health.health.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cuhk.fedcampus.health.health.HealthKitDataControllerActivity;
import com.cuhk.fedcampus.health.health.HealthKitMainActivity;
import com.cuhk.fedcampus.health.health.auth.HealthKitAuthActivity;
import com.huawei.health.demo.R;
import com.huawei.hms.hihealth.HuaweiHiHealth;
import com.huawei.hms.hihealth.SettingController;
import com.huawei.hms.hihealth.data.Scopes;
import com.huawei.hms.support.account.AccountAuthManager;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.account.request.AccountAuthParamsHelper;
import com.huawei.hms.support.account.service.AccountAuthService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private static boolean isLogin = false;

    private SettingController mSettingController;

    private Button data_button;

    private Button login_button;

    private static final int REQUEST_AUTH = 1002;

    private Button logout_button;

    private static String TAG = "HomeFragmentAuth";

    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View view = inflater.inflate(R.layout.activity_healthkit_main, container, false);

        login_button = (Button) view.findViewById(R.id.login_button);
        login_button.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println(isLogin);

                isLogin=true;
                System.out.println(111122333);
                Intent intent = new Intent(getActivity(), HealthKitAuthActivity.class);
                startActivity(intent);
            }
        }));


        data_button = (Button) view.findViewById(R.id.data);
        data_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
                System.out.println(22222);
                Intent intent = new Intent(getActivity(), HealthKitDataControllerActivity.class);
                startActivity(intent);
            }
        });


        logout_button = (Button) view.findViewById(R.id.logout_button);
        logout_button.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AccountAuthParams authParams =
                        new AccountAuthParamsHelper().setAccessToken().setScopeList(new ArrayList<>()).createParams();
                final AccountAuthService authService = AccountAuthManager.getService(getActivity().getApplicationContext(), authParams);
                authService.cancelAuthorization();
            }
        }));

        return view;


    }
}