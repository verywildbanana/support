package com.lhd.giveandreview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.view.PkExpandableListView;
import com.lhd.view.PkImageView;
import com.lhd.view.PkTextView;

import java.util.ArrayList;
import java.util.Date;

public class NoticeActivity extends BaseActivity {


    private ArrayList<NoticeData> mDataArray = new ArrayList<NoticeData>();
    private PkExpandableListView mExpandListView;
    private ExpandableListAdapter mExpandListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_notice);
        setTabBtn();

        mExpandListView = (PkExpandableListView) findViewById(R.id.ExpandListView);

        mDataArray.add(new NoticeData("0"));
        mDataArray.add(new NoticeData("0"));
        mDataArray.add(new NoticeData("0"));
        mDataArray.add(new NoticeData("0"));

        mExpandListAdapter = new ExpandableListAdapter(this,
                R.layout.layout_list_item_notice,
                R.layout.layout_list_item_notice_group, mDataArray);

        mExpandListView.setAdapter(mExpandListAdapter);
    }

    private class ExpandableListAdapter extends BaseExpandableListAdapter {

        private LayoutInflater mInflater;

        int mItemLayout;
        int mGroupLayout;
        Context mContext;
        private GroupViewHolder groupHolder;
        private ChildViewHolder childHolder;
        ArrayList<NoticeData> mData;

        public ExpandableListAdapter(Context ctx, int itemLayout,
                                     int groupLayout, ArrayList<NoticeData> data) {

            mInflater = LayoutInflater.from(ctx);

            mData = data;

            mContext = ctx;
            mItemLayout = itemLayout;
            mGroupLayout = groupLayout;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public int getChildrenCount(int groupPosition) {

            return 1;
        }

        @Override
        public View getChildView(final int groupPosition,
                                 final int childPosition, boolean isLastChild, View convertView,
                                 ViewGroup parent) {

            childHolder = null;

            if (convertView == null) {

                convertView = mInflater.inflate(mItemLayout, parent, false);

                childHolder = new ChildViewHolder();

                childHolder.mTitleText = (PkTextView) convertView.findViewById(R.id.TitleText);
                childHolder.mTitleSubText = (PkTextView) convertView.findViewById(R.id.TitleSubText);

                convertView.setTag(childHolder);

            } else {

                childHolder = (ChildViewHolder) convertView.getTag();
            }

            final NoticeData data = mData.get(groupPosition);

            childHolder.index = childPosition;

            return convertView;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mData.get(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public int getGroupCount() {

            return mData.size();
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {

            groupHolder = null;

            if (convertView == null) {

                groupHolder = new GroupViewHolder();

                convertView = mInflater.inflate(mGroupLayout, parent, false);

                groupHolder.mTitleText = (PkTextView) convertView.findViewById(R.id.TitleText);
                groupHolder.mArrowBtn = (PkImageView) convertView.findViewById(R.id.ArrowBtn);

                convertView.setTag(groupHolder);

            } else {

                groupHolder = (GroupViewHolder) convertView.getTag();
            }

            final NoticeData data = mData.get(groupPosition);


            groupHolder.index = groupPosition;

            if (mExpandListView.isGroupExpanded(groupPosition)) {

                groupHolder.mArrowBtn.setImageResource(R.drawable.g_13_down);
            } else {

                groupHolder.mArrowBtn.setImageResource(R.drawable.g_13_select);

            }

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public void onGroupCollapsed(int groupPosition) {


            super.onGroupCollapsed(groupPosition);
        }

        @Override
        public void onGroupExpanded(int groupPosition) {


            super.onGroupExpanded(groupPosition);
        }

        private class GroupViewHolder {

            PkImageView mArrowBtn;
            PkTextView mTitleText;
            int index;

        }

        private class ChildViewHolder {

            PkTextView mTitleText;
            PkTextView mTitleSubText;
            int index;
        }

    }


    class NoticeData {

        public long mId = 0;
        public String mTitle;
        public String mContents;
        public Date mCreateAt;

        NoticeData(String txt) {

            mTitle = txt;

        }

    }


}
