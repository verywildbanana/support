package com.lhd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;


public class PkExpandableListView extends ExpandableListView implements OnScrollListener {

	public final String TAG = this.getClass().getSimpleName().trim();

	boolean	mIsScrollEnable = true;

	private boolean mLastItemVisible;
	private OnScrollListener mOnScrollListener;
//	private boolean mIsUseLastItemUpdate = true;	// 리스트 마지막 이동시 자동 업데이트 이용 여부 설정

	Context mContext;

	public PkExpandableListView(Context context) {
		super(context);
		init(context);
	}

	public PkExpandableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PkExpandableListView(Context context, AttributeSet attrs,
								int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}


	private void init(Context context) {

		mContext = context;
		super.setOnScrollListener(this);
	}
	

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(mIsScrollEnable)
		{
			return super.dispatchTouchEvent(ev);
		}
		
		ev.setAction(MotionEvent.ACTION_CANCEL);
		super.dispatchTouchEvent(ev);

		return true;
	}

	public boolean IsScrollEnable() {
		return mIsScrollEnable;
	}

	public void setScrollEnable(boolean b) {
		mIsScrollEnable = b;
	}
	
	@Override
	public void setOnScrollListener(OnScrollListener listener) {
		
		mOnScrollListener = listener;
	}
	


	@Override
	public void onScrollStateChanged(AbsListView view, int state) {
		
		

		if (null != mOnScrollListener) {
			
			mOnScrollListener.onScrollStateChanged(view, state);
		}
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {


		if (null != mOnScrollListener) {
			mOnScrollListener.onScroll(view, firstVisibleItem,
					visibleItemCount, totalItemCount);
		}
		
	}
}
