/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lhd.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.lhd.giveandreview.R;


class PkSlidingTabStrip extends LinearLayout {

	private static final int DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS = 0;
	private static final byte DEFAULT_BOTTOM_BORDER_COLOR_ALPHA = 0x26;
	private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 5;
//	private static final int DEFAULT_SELECTED_INDICATOR_COLOR = 0xFF6EB14D;
	private static final int DEFAULT_SELECTED_INDICATOR_COLOR = 0xffff0000;
	
	private static final int DEFAULT_ARROW_CONTROL_DIPS = 20;
	
	private int mArrowContrlDip;
	
	
	private final int mBottomBorderThickness;
	private final Paint mBottomBorderPaint;

	private final int mSelectedIndicatorThickness;
	private final Paint mSelectedIndicatorPaint;

	private final int mDefaultBottomBorderColor;


	private int mSelectedPosition;
	private float mSelectionOffset;

	private PkSlidingTabLayout.TabColorizer mCustomTabColorizer;
	private final SimpleTabColorizer mDefaultTabColorizer;
	final float mDensity;

	PkSlidingTabStrip(Context context) {
		this(context, null);
	}

	PkSlidingTabStrip(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);

		mDensity = getResources().getDisplayMetrics().density;

		TypedValue outValue = new TypedValue();
		context.getTheme().resolveAttribute(R.attr.colorForeground, outValue, true);
		final int themeForegroundColor =  outValue.data;

		mDefaultBottomBorderColor = setColorAlpha(themeForegroundColor,
				DEFAULT_BOTTOM_BORDER_COLOR_ALPHA);

		mDefaultTabColorizer = new SimpleTabColorizer();
		mDefaultTabColorizer.setIndicatorColors(DEFAULT_SELECTED_INDICATOR_COLOR);

		mBottomBorderThickness = (int) (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS * mDensity);
		mBottomBorderPaint = new Paint();
		mBottomBorderPaint.setColor(mDefaultBottomBorderColor);

		mSelectedIndicatorThickness = (int) (SELECTED_INDICATOR_THICKNESS_DIPS * mDensity);
		mSelectedIndicatorPaint = new Paint();

		mArrowContrlDip = (int) (DEFAULT_ARROW_CONTROL_DIPS * mDensity);
		
	}

	void setCustomTabColorizer(PkSlidingTabLayout.TabColorizer customTabColorizer) {
		mCustomTabColorizer = customTabColorizer;
		invalidate();
	}

	void setSelectedIndicatorColors(int... colors) {
		// Make sure that the custom colorizer is removed
		mCustomTabColorizer = null;
		mDefaultTabColorizer.setIndicatorColors(colors);
		invalidate();
	}

	void setDividerColors(int... colors) {
		// Make sure that the custom colorizer is removed
		mCustomTabColorizer = null;
		mDefaultTabColorizer.setDividerColors(colors);
		invalidate();
	}

	void onViewPagerPageChanged(int position, float positionOffset) {
		mSelectedPosition = position;
		mSelectionOffset = positionOffset;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		final int height = getHeight();
		final int childCount = getChildCount();
		final PkSlidingTabLayout.TabColorizer tabColorizer = mCustomTabColorizer != null
				? mCustomTabColorizer
						: mDefaultTabColorizer;

		// Thick colored underline below the current selection
		if (childCount > 0) {
			View selectedTitle = getChildAt(mSelectedPosition);
			int left = selectedTitle.getLeft();
			int right = selectedTitle.getRight();
			int color = tabColorizer.getIndicatorColor(mSelectedPosition);

			if (mSelectionOffset > 0f && mSelectedPosition < (getChildCount() - 1)) {
				int nextColor = tabColorizer.getIndicatorColor(mSelectedPosition + 1);
				if (color != nextColor) {
					color = blendColors(nextColor, color, mSelectionOffset);
				}

				// Draw the selection partway between the tabs
				View nextTitle = getChildAt(mSelectedPosition + 1);
				left = (int) (mSelectionOffset * nextTitle.getLeft() +
						(1.0f - mSelectionOffset) * left);
				right = (int) (mSelectionOffset * nextTitle.getRight() +
						(1.0f - mSelectionOffset) * right);
			}

			mSelectedIndicatorPaint.setColor(color);

			Rect selectedIndicator = new Rect(left, height - mSelectedIndicatorThickness, right, height);
			canvas.drawRect(selectedIndicator, mSelectedIndicatorPaint);

//			int dstWidth = mArrow.getWidth();
//			int dstHeight = mArrow.getHeight();
//
//			int start = left+(right-left)/2;
//			start = start - mArrowContrlDip;
//			
//			Rect srcRect = new Rect(0, 0, dstWidth, dstHeight);
//			Rect dstRect = new Rect(start, mArrowContrlDip, start+dstWidth, dstHeight+mArrowContrlDip);
//
//			Paint paint = new Paint();
//			canvas.drawBitmap(mArrow, srcRect, dstRect, paint);


		}

		// Thin underline along the entire bottom edge
//		canvas.drawRect(0, height - mBottomBorderThickness, getWidth(), height, mBottomBorderPaint);

	}

	/**
	 * Set the alpha value of the {@code color} to be the given {@code alpha} value.
	 */
	private static int setColorAlpha(int color, byte alpha) {
		return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
	}

	/**
	 * Blend {@code color1} and {@code color2} using the given ratio.
	 *
	 * @param ratio of which to blend. 1.0 will return {@code color1}, 0.5 will give an even blend,
	 *              0.0 will return {@code color2}.
	 */
	private static int blendColors(int color1, int color2, float ratio) {
		final float inverseRation = 1f - ratio;
		float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
		float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
		float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
		return Color.rgb((int) r, (int) g, (int) b);
	}

	private static class SimpleTabColorizer implements PkSlidingTabLayout.TabColorizer {
		private int[] mIndicatorColors;
		private int[] mDividerColors;

		@Override
		public final int getIndicatorColor(int position) {
			return mIndicatorColors[position % mIndicatorColors.length];
		}

		@Override
		public final int getDividerColor(int position) {
			return mDividerColors[position % mDividerColors.length];
		}

		void setIndicatorColors(int... colors) {
			mIndicatorColors = colors;
		}

		void setDividerColors(int... colors) {
			mDividerColors = colors;
		}
	}
}