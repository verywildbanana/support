package com.lhd.giveandreview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.giveandreview.base.PkIntentManager;
import com.lhd.view.PkOnOffToggleButton;

public class MainActivity extends BaseActivity {


    ViewGroup mFlowTxtLay;
    TranslateAnimation animationTranSlate = null;
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

        mFlowTxtLay = (ViewGroup) findViewById(R.id.FlowTxtLay);


        PkOnOffToggleButton mGivewMenu = (PkOnOffToggleButton)findViewById(R.id.GivewMenu);
        mGivewMenu.setOnClickListener(mClickListener);
        startFlowAni(true);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.GivewMenu:

                    PkIntentManager.getInstance().push(MainActivity.this, GivePageActivity.class, true);
                    break;


            }

        }
    };


    private void startFlowAni(boolean first) {

        if(first) {

            animationTranSlate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, -1,
                    Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0);

            animationTranSlate.setDuration(3000);

        }
        else {

            animationTranSlate = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 3,
                    Animation.RELATIVE_TO_SELF, -1,
                    Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0);

            animationTranSlate.setDuration(6000);


        }



        animationTranSlate.setAnimationListener(mAnimationListener);
        mFlowTxtLay.startAnimation(animationTranSlate);


    }

    Animation.AnimationListener mAnimationListener =   new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            startFlowAni(false);

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

}
