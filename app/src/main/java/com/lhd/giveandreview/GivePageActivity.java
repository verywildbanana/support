package com.lhd.giveandreview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.view.PkCirclePageIndicator;
import com.lhd.view.PkSlidingTabLayout;
import com.lhd.view.PkViewPager;

import java.util.ArrayList;

public class GivePageActivity extends BaseActivity {

    PkSlidingTabLayout mSlideTabLay;
    PkViewPager mViewPager;
    PkCirclePageIndicator mNavigation;
    ViewPagerAdapter mViewPagerAdapter;

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

        mSlideTabLay = (PkSlidingTabLayout)findViewById(R.id.SlideTabLay);
        mViewPager = (PkViewPager)findViewById(R.id.ViewPager);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        mNavigation = (PkCirclePageIndicator)findViewById(R.id.Navigation);

        mViewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);

        mSlideTabLay.setViewPager(mViewPager);
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


    class ViewPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        ArrayList<String> mPageTitles = new ArrayList<String>();

        public ViewPagerAdapter(Context context) {

            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
            mPageTitles.add("내주변");
            mPageTitles.add("강남");
            mPageTitles.add("이태원");
            mPageTitles.add("홍대");

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPageTitles.get(position);
        }

        @Override
        public int getCount() {

            return 3;

        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {

            View  view = new View(GivePageActivity.this);
            view.setBackgroundColor(getResources().getColor(R.color.red));

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

}
