package com.lhd.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class PkEditText extends EditText {
	private static final String TAG = PkEditText.class.getSimpleName();
	private boolean mForShareEditText = false;
	public boolean mFixCursor = false;

	public PkEditText(Context context) {
		super(context);

		initView();
	}

	public PkEditText(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView();
	}

	public PkEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	private void initView() {

		// super.setTypeface(FontManager.shareInstance().getRegular());

		// if(getTypeface() == Typeface.DEFAULT) {
		// super.setTypeface(FontManager.shareInstance().getRegular());
		// }
		// else if(getTypeface() == Typeface.DEFAULT_BOLD) {
		// super.setTypeface(FontManager.shareInstance().getBold());
		// }

		// setLineSpacing(0.f, 1.1f);
		/*
		 * int top = getPaddingTop() +
		 * DensityUtil.densityToPixel(getResources(), 1.5f); int left =
		 * getPaddingLeft(); int right = getPaddingRight(); int bottom =
		 * getPaddingBottom();
		 * 
		 * setPadding(left, top, right, bottom);
		 */

		// setInputType((getInputType() |
		// InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS));
	}

	// @Override
	// public void setTypeface(Typeface tf) {
	//
	// super.setTypeface(FontManager.shareInstance().getRegular());

	// if(tf == Typeface.MONOSPACE) {
	// super.setTypeface(FontManager.shareInstance().getBold());
	// }
	// else {
	// super.setTypeface(FontManager.shareInstance().getRegular());
	// }
	// }

	// @Override
	// public void setTypeface(Typeface tf, int style) {
	//
	// if((style & Typeface.BOLD) == Typeface.BOLD || tf ==
	// Typeface.DEFAULT_BOLD) {
	// super.setTypeface(FontManager.shareInstance().getBold());
	// }
	// else {
	// super.setTypeface(FontManager.shareInstance().getRegular());
	// }
	// }

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (isEnabled() == false) {
			return false;
		}

		return super.onTouchEvent(event);
	}

	public void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getContext()
				.getSystemService(getContext().INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(getWindowToken(), 0);
	}

	public void setDispatchKeyEventEnable(boolean enable) {

		mForShareEditText = enable;

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {


		if (mForShareEditText) {

			setSelection(this.getText().length());

		}

		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onSelectionChanged(int selStart, int selEnd) {


		if (mFixCursor && this.getText() != null) {

			this.setSelection(this.getText().toString().length());
		}
	}

	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {


			if (mOnImeBack != null) mOnImeBack.onImeBack(this.getText().toString());
		}
		return super.dispatchKeyEvent(event);
	}


	public void setOnEditTextImeBackListener(EditTextImeBackListener listener) {
		mOnImeBack = listener;
	}

	private EditTextImeBackListener mOnImeBack;

	public interface EditTextImeBackListener {
		public abstract void onImeBack(String text);
	}





}
