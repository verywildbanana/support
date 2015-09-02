package com.lhd.giveandreview;

import android.os.Bundle;
import android.view.View;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.giveandreview.base.PkIntentManager;

public class MorePageActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_more);
        setTabBtn();
        mMoreMenu.setOn(true);
        mMoreMenu.setClickable(false);

        findViewById(R.id.NoticeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(MorePageActivity.this, NoticeActivity.class, true);
            }
        });

        findViewById(R.id.RegisterBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(MorePageActivity.this, RegisterStoreActivity.class, true);
            }
        });
        findViewById(R.id.RecommendBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(MorePageActivity.this, RecommendActivity.class, true);
            }
        });

        findViewById(R.id.TermBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(MorePageActivity.this, TermActivity.class, true);
            }
        });




    }
}
