package com.lhd.giveandreview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.giveandreview.base.PkIntentManager;
import com.lhd.view.PkCirclePageIndicator;
import com.lhd.view.PkSlidingTabLayout;
import com.lhd.view.PkViewPager;

import java.util.ArrayList;

public class GivePageActivity extends BaseActivity {

    PkSlidingTabLayout mSlideTabLay;
    PkViewPager mViewPager;
    PkCirclePageIndicator mNavigation;
    ViewPagerAdapter  mViewPagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {


        setContentView(R.layout.activity_give_page);
        setTabBtn();
        mGivewMenu.setOn(true);
        mGivewMenu.setClickable(false);

        mSlideTabLay = (PkSlidingTabLayout)findViewById(R.id.SlideTabLay);
        PkViewPager viewPager = (PkViewPager)findViewById(R.id.ViewPager);
        viewPager.setOnPageChangeListener(mOnPageChangeListener);

        ProvincePagerAdapter viewPagerAdapter = new ProvincePagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        mSlideTabLay.setViewPager(viewPager);

        mViewPager  = (PkViewPager)findViewById(R.id.ContentViewPager);
        mNavigation = (PkCirclePageIndicator)findViewById(R.id.Navigation);

        ViewPagerAdapter  mViewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);

        mNavigation = (PkCirclePageIndicator)findViewById(R.id.Navigation);
        float density = getResources().getDisplayMetrics().density;
        mNavigation.setViewPager(mViewPager);
        mNavigation.setRadius(4 * density);
        mNavigation.setPageColor(0xffa3a3a3);
        mNavigation.setFillColor(getResources().getColor(R.color.red));
        mNavigation.setStrokeColor(0xffa3a3a3);
        mNavigation.setStrokeWidth(3 * density);
        mNavigation.setCentered(true);

    }


    android.support.v4.view.ViewPager.OnPageChangeListener mOnPageChangeListener = new android.support.v4.view.ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mSlideTabLay.mInternalViewPagerListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            mSlideTabLay.mInternalViewPagerListener.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mSlideTabLay.mInternalViewPagerListener.onPageScrollStateChanged(state);
        }
    };


    class ProvincePagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        ArrayList<String> mPageTitles = new ArrayList<String>();

        public ProvincePagerAdapter(Context context) {

            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            mPageTitles.add("내주변");
            mPageTitles.add("강남");
            mPageTitles.add("이태원");
            mPageTitles.add("홍대");
            mPageTitles.add("신사동");
            mPageTitles.add("가로수");
            mPageTitles.add("압구정");


        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPageTitles.get(position);
        }

        @Override
        public int getCount() {

            return mPageTitles.size();

        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {

            View  view = new View(GivePageActivity.this);
            container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            return view;
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


    class ViewPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;
        View[] mView;

        public ViewPagerAdapter(Context context) {

            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            int count = getCount();

            mView = new View[count];

            for (int i = 0; i < count ; i++) {

                mView[i]  = mLayoutInflater.inflate(R.layout.layout_give_viewpager_item, null);

            }

        }

        @Override
        public int getCount() {

            return 10;

        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {

            container.addView(mView[position], ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            mView[position].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PkIntentManager.getInstance().push(GivePageActivity.this, GiveDetailActivity.class, true);
                }
            });

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
