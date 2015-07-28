package com.lhd.giveandreview;

import android.os.Bundle;
import android.view.View;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.giveandreview.base.PkIntentManager;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_main);

        findViewById(R.id.GiveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(MainActivity.this, GivePageActivity.class, true);

            }
        });

        findViewById(R.id.ReviewBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(MainActivity.this, GivePageActivity.class, true);

            }
        });


    }
}
