package com.lhd.giveandreview;

import android.os.Bundle;
import android.view.View;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.giveandreview.base.PkIntentManager;
import com.lhd.view.PkEditText;

public class WriteDetailActivity extends BaseActivity {

    PkEditText mWriteEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_write);
        mWriteEditTxt = (PkEditText)findViewById(R.id.WriteEditTxt);
        mWriteEditTxt.postDelayed(new Runnable() {
            @Override
            public void run() {

                mWriteEditTxt.setVisibility(View.VISIBLE);

            }
        }, 2000);


        findViewById(R.id.SearchLay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PkIntentManager.getInstance().push(WriteDetailActivity.this, WriteSearchPlaceActivity.class, true);
            }
        });

    }
}
