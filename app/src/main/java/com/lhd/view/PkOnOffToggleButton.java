package com.lhd.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.lhd.Constants;
import com.lhd.util.SmartLog;


public class PkOnOffToggleButton extends ImageView {

	private static final String TAG = PkOnOffToggleButton.class.getSimpleName();
	
	private boolean mSelected = false;

	private OnOffButtonListener mListener;

	int mOnImageResource;
	int mOffImageResource;
	public boolean toggleButtonClickable = true;
	public boolean toggleButtonTouchOnOffAble  = true;

	Object mTag;

	public PkOnOffToggleButton(Context context) {
		super(context);

		init();
	}

	public PkOnOffToggleButton(Context context, AttributeSet attrs) {

		super(context, attrs);

		init();
		initAttr(attrs);
	}

	private void init() {

		setClickable(true);
		setScaleType(ScaleType.CENTER);
		setOnClickListener(mClickListener);
	}

	private void initAttr(AttributeSet attrs) {

		int ToggleButtonOnImage = attrs.getAttributeResourceValue(
				Constants.KIWI_SCHEME, "ToggleButtonOnImage", 0);
		int ToggleButtonOffImage = attrs.getAttributeResourceValue(
				Constants.KIWI_SCHEME, "ToggleButtonOffImage", 0);

		SmartLog.getInstance().w(TAG,
				"on:" + ToggleButtonOnImage + "  off:" + ToggleButtonOffImage);
		// 한개가 이미지 없는 경우 chekbox가 box하고 check가 따로 옴
		// if(ToggleButtonOnImage != 0 && ToggleButtonOffImage != 0 ) {

		setToggleImage(ToggleButtonOnImage, ToggleButtonOffImage);

		toggleButtonClickable = attrs.getAttributeBooleanValue(
				Constants.KIWI_SCHEME, "ToggleButtonClickable", true);

		toggleButtonTouchOnOffAble = attrs.getAttributeBooleanValue(
				Constants.KIWI_SCHEME, "ToggleTouchOnOffAble", true);


		if (!toggleButtonClickable) {
			setOnClickListener(null);
			setClickable(false);
		}
	}

	public void setToggleClickable(boolean clickable) {

		toggleButtonClickable = clickable;
		if (!toggleButtonClickable) {
			setOnClickListener(null);
			setClickable(false);
		} else {
			setOnClickListener(mClickListener);
			setClickable(true);
		}

	}

	public void setToggleImage(int on, int off) {

		mOnImageResource = on;
		mOffImageResource = off;

		setImageResource(mOffImageResource);

	}

	@Override
	public void setSelected(boolean selected) {

		mSelected = selected;

		if (mSelected) {
			setImageResource(mOnImageResource);
		} else {
			setImageResource(mOffImageResource);
		}
	}
	
	public boolean getSelected() {
		
		return mSelected;
	}

	public void setOnToggleListener(OnOffButtonListener listener) {
		mListener = listener;
	}

	public void setToggleListener(Object tag, OnOffButtonListener listener) {
		mTag = tag;
		mListener = listener;
	}

	public void setOn(boolean on) {
		mSelected = on;

		this.setSelected(mSelected);
	}

	public boolean isOn() {
		return mSelected;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if(toggleButtonTouchOnOffAble == false) {

			return super.onTouchEvent(event);
		}

		if (isEnabled() && toggleButtonClickable) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				// setSelected(!isSelected());
				mSelected = !mSelected;
			}

			boolean result = super.onTouchEvent(event);

			if (event.getAction() == MotionEvent.ACTION_UP) {
				this.setSelected(mSelected);
			}

			return result;
		} else {
			return super.onTouchEvent(event);
		}
	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (mListener != null) {

				if (mTag != null) {

					v.setTag(mTag);
				}

				mListener.onToggled(v, mSelected);
			}
		}
	};

	public interface OnOffButtonListener {
		
		public abstract void onToggled(View v, boolean on);
	}
}