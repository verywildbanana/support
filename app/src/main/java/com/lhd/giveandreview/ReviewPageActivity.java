package com.lhd.giveandreview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.lhd.fragment.PoiListForReviewSearchFragment;
import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.giveandreview.base.PkIntentManager;
import com.lhd.view.PkImageView;
import com.lhd.view.PkViewPager;

import java.util.ArrayList;


public class ReviewPageActivity extends BaseActivity {

    public final static String  SORT_TYPE_STORE = "store";
    public final static String  SORT_TYPE_REVIEW = "REVIEW";

    PkViewPager mViewPager;
    ViewPagerAdapter mViewPagerAdapter;
    PkImageView mOneBtn, mTwoBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {


        setContentView(R.layout.activity_review_page);
        setTabBtn();
        mReviewMenu.setOn(true);
        mReviewMenu.setClickable(false);

        mOneBtn = (PkImageView) findViewById(R.id.OneBtn);
        mTwoBtn = (PkImageView) findViewById(R.id.TwoBtn);

        mOneBtn.setOnClickListener(mButtonClickListener);
        mTwoBtn.setOnClickListener(mButtonClickListener);

        mViewPager = (PkViewPager) findViewById(R.id.ContentViewPager);

        findViewById(R.id.WrireReviewBtn).setOnClickListener(mButtonClickListener);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);

    }


    View.OnClickListener mButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.OneBtn:

                    if (mViewPager.getCurrentItem() != 0) {

                        mViewPager.setCurrentItem(0);
                    }

                    break;

                case R.id.TwoBtn:

                    if (mViewPager.getCurrentItem() != 1) {

                        mViewPager.setCurrentItem(1);
                    }

                    break;

                case R.id.WrireReviewBtn:

                    PkIntentManager.getInstance().push(ReviewPageActivity.this, WriteDetailActivity.class, true);

                    break;
            }

        }
    };

    private class ViewPagerAdapter extends FragmentPagerAdapter {


        PoiListForReviewSearchFragment mFragment1;
        PoiListForReviewSearchFragment mFragment2;
        ArrayList<Fragment> views = new ArrayList<Fragment>();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            init();
        }


        public void init() {

            mFragment1 = PoiListForReviewSearchFragment.newInstance(SORT_TYPE_STORE);
            views.add(mFragment1);

            mFragment2 = PoiListForReviewSearchFragment.newInstance(SORT_TYPE_REVIEW);
            views.add(mFragment2);

        }

        @Override
        public Fragment getItem(int position) {


            return views.get(position);

        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {

            return 2;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object o) {
            super.destroyItem(collection, position, o);
        }

    }


    int mSelectTabId;

    private void setTabView(int id) {


        if (id == R.id.OneBtn
                && mSelectTabId != id) {

            mOneBtn.setImageResource(R.drawable.g_5_1_list1);
            mTwoBtn.setImageResource(R.drawable.g_5_1_list2);


        } else if (id == R.id.TwoBtn
                && mSelectTabId != id) {


            mOneBtn.setImageResource(R.drawable.g_5_2_list1);
            mTwoBtn.setImageResource(R.drawable.g_5_2_list2);

        }

        mSelectTabId = id;

    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {


            if (position == 0) {

                setTabView(R.id.OneBtn);

            } else if (position == 1) {

                setTabView(R.id.TwoBtn);
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        @Override
        public void onPageScrollStateChanged(int arg0) {


        }
    };


}
