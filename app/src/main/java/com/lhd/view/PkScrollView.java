package com.lhd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.lhd.util.DensityUtil;


public class PkScrollView extends ScrollView {

	public static final String TAG = PkScrollView.class.getSimpleName();
	private Context mContext;
	int mContentHeight = 0;

	public PkScrollView(Context context) {
		super(context);
		mContext = context;
	}

	public PkScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public PkScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
	}


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {


		return super.onInterceptTouchEvent(ev);
	}


	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {


		if(mOnScrollChangedListener != null) {

			mOnScrollChangedListener.onScrollChanged(l, t , oldl, oldt);

		}

		if(mActionUp == true
				&& mOnScrollEndListener != null) {

			mOnScrollEndListener.onScrollEnd();

		}

		if (mOnScrollBottomListener != null) {
			View view = (View) getChildAt(getChildCount() - 1);
			int diff = (view.getBottom() - (getHeight() + getScrollY() + view.getTop()));
			if (diff == 0) { // diff 가 0이면 scroll 이 bottom 에 도달했음을 뜻한다.
				mOnScrollBottomListener.onScrollHitBottom();
			}
		}

		super.onScrollChanged(l, t, oldl, oldt);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {


		if(mScrollBlocK) {

			return false;

		}


		return super.onTouchEvent(ev);
	}


	public boolean mScrollBlocK = false;


	boolean mActionUp = false;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {



		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mActionUp = false;
				break;
			case MotionEvent.ACTION_UP:
				mActionUp = true;
				if(mOnScrollEndListener != null) {

					mOnScrollEndListener.onScrollEnd();
				}
				break;
		}


		return super.dispatchTouchEvent(ev);
	}




	public void setContentHeight(int height) {

		mContentHeight = height;
		mContentHeight = mContentHeight + DensityUtil.densityToPixel(mContext.getResources(), 20f);
	}

	OnScrollEndListener mOnScrollEndListener;

	public void setOnScrollEndListener(OnScrollEndListener listener) {

		mOnScrollEndListener = listener;
	}

	public interface OnScrollEndListener {

		public abstract void onScrollEnd();
	}

	onScrollBottomListener mOnScrollBottomListener;

	public void setOnScrollBottomListener(onScrollBottomListener listener) {
		mOnScrollBottomListener = listener;
	}

	/**
	 * Scroll 이 bottom 에 위치할 경우 불리는 listener
	 * @author mustard
	 *
	 */
	public interface onScrollBottomListener {
		public abstract void onScrollHitBottom();
	}





	OnScrollChangedListener mOnScrollChangedListener;

	public void setOnScrollChangedListener(OnScrollChangedListener listener) {

		mOnScrollChangedListener = listener;
	}

	public interface OnScrollChangedListener {

		public abstract void onScrollChanged(int l, int t, int oldl, int oldt);
	}

}
