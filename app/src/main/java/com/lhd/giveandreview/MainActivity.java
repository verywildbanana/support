package com.lhd.giveandreview;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.lhd.giveandreview.base.BaseActivity;

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
        setTabBtn();
        mHomeMenu.setOn(true);
        mHomeMenu.setClickable(false);

        mFlowTxtLay = (ViewGroup) findViewById(R.id.FlowTxtLay);

        startFlowAni(true);


    }


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
