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

public class StartActivity extends BaseActivity {


    private PkViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_strart);
        mViewPager = (PkViewPager) findViewById(R.id.ViewPager);
        mViewPager.setOffscreenPageLimit(3);
        mViewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);


    }

    class ViewPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;
        View[] mView = new View[3];

        public ViewPagerAdapter(Context context) {

            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            mView[0] = mLayoutInflater.inflate(R.layout.layout_strart_view_pager_1, null);
            mView[1] = mLayoutInflater.inflate(R.layout.layout_strart_view_pager_2, null);
            mView[2] = mLayoutInflater.inflate(R.layout.layout_strart_view_pager_3, null);

            mView[2].findViewById(R.id.StartBtnImg).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SmartLog.getInstance().w(TAG, "START BTN CLICK");

                    PkIntentManager.getInstance().push(StartActivity.this, LoginActivity.class, true);

                }
            });


        }

        @Override
        public int getCount() {

            return 3;

        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {



            container.addView(mView[position], ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return mView[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {


            container.removeView((View) object);

        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


}
