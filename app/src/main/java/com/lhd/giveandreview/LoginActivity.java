package com.lhd.giveandreview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.giveandreview.base.PkIntentManager;
import com.lhd.util.SmartLog;
import com.lhd.view.PkViewPager;

public class LoginActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_login);

        findViewById(R.id.SkipBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(LoginActivity.this, MainActivity.class, true, 0, true);

            }
        });


    }
}
