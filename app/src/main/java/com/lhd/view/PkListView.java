package com.lhd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.lhd.util.SmartLog;


public class PkListView extends ListView implements OnScrollListener {

	public final String TAG = this.getClass().getSimpleName().trim();
	boolean	mIsScrollEnable = true;
	Context mContext;

	public PkListView(Context context) {
		super(context);
		init(context);
	}

	public PkListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PkListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}


	private void init(Context context) {

		mContext = context;
		super.setOnScrollListener(this);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		if(mIsScrollEnable)	{
			try {
				return super.dispatchTouchEvent(ev);
			} catch (IndexOutOfBoundsException e) {
				SmartLog.getInstance().e(TAG, e.getLocalizedMessage());
				return true;
			}

		}
		else {

			return false;
		}

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

	}
}
