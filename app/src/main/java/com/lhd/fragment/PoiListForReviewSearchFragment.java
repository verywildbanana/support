package com.lhd.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.lhd.giveandreview.R;
import com.lhd.giveandreview.ReviewPageActivity;
import com.lhd.view.PkListView;
import com.lhd.view.PkTextView;

import java.util.ArrayList;


public class PoiListForReviewSearchFragment extends PkBaseFragment {



    private PkListView mListView;
    private PkPoiListForDoPickAdapter mListAdapter;
    private RelativeLayout mParentLay;
    private String mSortType;


    public static PoiListForReviewSearchFragment newInstance(String sortType) {

        PoiListForReviewSearchFragment mAlarmFragment = new PoiListForReviewSearchFragment();
        mAlarmFragment.mSortType = sortType;

        return mAlarmFragment;
    }


    @Override
    public void onStart() {

        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        mParentLay = new RelativeLayout(mActivity);
        mParentLay.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        initView();

        return mParentLay;
    }

    private void initView() {


        LayoutInflater inflater = LayoutInflater.from(mActivity);
        mListView = (PkListView) inflater.inflate(R.layout.view_list, mParentLay, false);
        mParentLay.addView(mListView);

        View header = new View(mActivity);

        ArrayList<String> mDataArray = new ArrayList<String>();
        mDataArray.add("1");
        mDataArray.add("2");
        mDataArray.add("3");
        mDataArray.add("4");
        mDataArray.add("5");
        mDataArray.add("6");
        mDataArray.add("7");
        mDataArray.add("8");
        mDataArray.add("9");
        mDataArray.add("10");
        mDataArray.add("11");

        mListAdapter = new PkPoiListForDoPickAdapter(mActivity, mDataArray);
        mListAdapter.setListItemClickListener(mListItemCliclListener);
        mListView.setAdapter(mListAdapter);

    }


    public View.OnClickListener mListItemCliclListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

        }
    };


    public class PkPoiListForDoPickAdapter extends BaseAdapter {

        private Context mContext;
        private ArrayList<String> mData;

        public PkPoiListForDoPickAdapter(Context c, ArrayList<String> data) {
            mContext = c;
            mData = data;
        }

        public void setData(ArrayList<String> data) {

            mData = data;
            notifyDataSetInvalidated();
        }

        @Override
        public int getCount() {

            if (mData != null) {
                return mData.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            if (mData != null) {
                return mData.get(position);
            } else {
                return null;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if(mSortType == ReviewPageActivity.SORT_TYPE_STORE) {

                ViewHolder holder = null;

                if (convertView == null) {

                    LayoutInflater lif = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = lif.inflate(R.layout.layout_review_listview_item, null);

                    holder = new ViewHolder();


                    holder.mTitleTxt = (PkTextView) convertView.findViewById(R.id.TitleTxt);

                    convertView.setTag(holder);

                } else {

                    holder = (ViewHolder) convertView.getTag();
                }


                holder.index = position;



                return convertView;

            }
            else {


                ViewHolder holder = null;

                if (convertView == null) {

                    LayoutInflater lif = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = lif.inflate(R.layout.layout_review_listview_item_review_sort, null);

                    holder = new ViewHolder();


                    holder.mTitleTxt = (PkTextView) convertView.findViewById(R.id.TitleTxt);

                    convertView.setTag(holder);

                } else {

                    holder = (ViewHolder) convertView.getTag();
                }


                holder.index = position;

                return convertView;

            }


        }

        private View.OnClickListener mItemClickListener;

        public void setListItemClickListener(View.OnClickListener l) {

            mItemClickListener = l;
        }

        public class ViewHolder {

            PkTextView mTitleTxt;
            public int index;
        }

    }

}
