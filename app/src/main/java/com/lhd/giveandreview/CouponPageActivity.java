package com.lhd.giveandreview;

import android.os.Bundle;
import android.view.View;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.giveandreview.base.PkIntentManager;

public class CouponPageActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_coupon);
        setTabBtn();
        mCouponMenu.setOn(true);
        mCouponMenu.setClickable(false);

        findViewById(R.id.CouponItemLay1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(CouponPageActivity.this, CouponDetailActivity.class, true);
            }
        });

        findViewById(R.id.CouponItemLay2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PkIntentManager.getInstance().push(CouponPageActivity.this, CouponDetailActivity.class, true);
            }
        });

    }
}
