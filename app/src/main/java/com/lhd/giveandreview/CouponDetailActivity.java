package com.lhd.giveandreview;

import android.os.Bundle;

import com.lhd.giveandreview.base.BaseActivity;

public class CouponDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_coupon_detail);
        setTabBtn();

    }
}
