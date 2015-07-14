package com.lhd.view;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lhd.util.SpannableStringUtil;
import com.lhd.util.StringUtil;


public class PkTextView extends TextView {
	
	public boolean mIsNot9999Limit = false;	// 숫자의 경우 특정 길이 넘을 때 9999 출력 원치않을 때 사용 (ex barcode) 

	public PkTextView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public PkTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public PkTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * text 추가 시 color 적용
	 * 
	 * @param txt
	 *            추가로 입력되는 text
	 * @param color
	 *            추가로 입력되는 text의 color
	 */
	public void append(String txt, String color) {
		if (!StringUtil.isNull(txt)) {
			final SpannableStringBuilder sp = new SpannableStringBuilder(txt);
			sp.setSpan(new ForegroundColorSpan(Color.parseColor(color)), 0, sp.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			append(sp);
		}
	}

	public void setText(String txt) {
		
		if (StringUtil.isNull(txt)) {
			
			super.setText(" ");
			
		}
		else {
		
			
			if(!mIsNot9999Limit && isNumeric(txt)) {
				
				double number = 0;
				
				try {
					
					number = Double.parseDouble(txt);
					
				} catch (NumberFormatException e) {
				
					super.setText(txt);
					
				}
				
				if(number > 9999) {
					
					super.setText("9999");
				}
				else {
					
					super.setText(txt);
				}
			}
			else {
			
				super.setText(txt);
			}
		}
	}
	
	public void setText(String txt, int maxNum) {
		
		if (StringUtil.isNull(txt)) {
			
			super.setText(" ");
			
		}
		else {
		
			
			if(!mIsNot9999Limit && isNumeric(txt)) {
				
				double number = 0;
				
				try {
					
					number = Double.parseDouble(txt);
					
				} catch (NumberFormatException e) {
				
					super.setText(txt);
					
				}
				
				if(number > maxNum) {
					
					super.setText(String.valueOf(maxNum));
				}
				else {
					
					super.setText(txt);
				}
			}
			else {
			
				super.setText(txt);
			}
		}
	}
	
	public void setTextInListView(String txt) {
		
		if (txt != null) {
		
			super.setText(txt);
			
		}
		else {
			
			super.setText(" ");
			
		}
	}
	
	public void setMultiTextColor(String totalText, String[] textSet, int[] colorSet) {
		
		
		SpannableString str = new SpannableString(totalText);
		
		int length = colorSet.length;
		for (int i = 0; i < length; i++) {
			
			if (StringUtil.isNull(textSet[i]) == false) {
			
				SpannableStringUtil.setColorSpan(str, textSet[i], colorSet[i]);
				
			} 
			
		}
		
		setText(str);
	}
	
	
	
	public boolean isNumeric(String str)
	{                          
	    return str.matches("^-?\\d+\\.?\\d*$");
	}
	
}
