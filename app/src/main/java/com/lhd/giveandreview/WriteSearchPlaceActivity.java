package com.lhd.giveandreview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lhd.giveandreview.base.BaseActivity;
import com.lhd.view.PkListView;
import com.lhd.view.PkTextView;

import java.util.ArrayList;

public class WriteSearchPlaceActivity extends BaseActivity {


    PkListView mListView;
    private PlaceListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setIntent() {

    }

    @Override
    public void initActivity() {

        setContentView(R.layout.activity_write_search_place);
        setTabBtn();

        mListView = (PkListView) findViewById(R.id.ListView);
        ArrayList<String> data = new ArrayList<String>();
        data.add("0");
        data.add("1");
        data.add("2");
        data.add("3");


        mListAdapter = new PlaceListAdapter(this, data);
        mListView.setAdapter(mListAdapter);

    }


    public class PlaceListAdapter extends BaseAdapter {

        private Context mContext;
        private ArrayList<String> mData;

        public PlaceListAdapter(Context c, ArrayList<String> data) {
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


            ViewHolder holder = null;

            if (convertView == null) {

                LayoutInflater lif = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = lif.inflate(R.layout.layout_write_seach_place_listview_item, null);

                holder = new ViewHolder();


                holder.mTitleTxt = (PkTextView) convertView.findViewById(R.id.TitleTxt);

                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }


            holder.index = position;

            return convertView;


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
