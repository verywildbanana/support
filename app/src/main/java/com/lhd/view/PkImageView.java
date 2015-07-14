package com.lhd.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.lhd.util.SmartLog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class PkImageView extends ImageView {



	public PkImageView(Context context) {
		super(context);
	}

	public PkImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PkImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

	}
	
	public interface DownloadFailListener {
		public void onDownloadFail();
	}
	
	public void setOnDownloadFailListener(DownloadFailListener listener) {
		mDownloadFailListener = listener;
	}

	private ImageLoadingListener mAnimateFirstListener = new DownImgDisplayListener();
	private DownloadFailListener mDownloadFailListener = null;

	String mCurrentUrl;
	boolean mAlphaAni;

	public void setImagedUrl(String url, DisplayImageOptions options, ImageLoader UILImageLoader, boolean alphaAni) {
		
		// url 주소가 null 일 경우 예외처리 하도록 소스 추가함.
		if (url == null || url.isEmpty()) {
			SmartLog.getInstance().e("PkImageView", "-- url is null return");
			return;
		}

		mAlphaAni = alphaAni;

		if (mCurrentUrl == null
				|| mCurrentUrl.toString().equals(url) == false) {

			mCurrentUrl = new String(url);
			setImageBitmap(null);
//			setImageResource(R.drawable.fffff);

			UILImageLoader.displayImage(url, this, options, mAnimateFirstListener);


		}

	}

	private void setDownImage(final Bitmap bitmap) {
		
		this.post(new Runnable() {
			
			@Override
			public void run() {

				setImageBitmap(bitmap);
				
			}
		});
		
		
		if (mAlphaAni) {

			AlphaAnimation mAlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
			mAlphaAnimation.setDuration(300);
			mAlphaAnimation.setRepeatCount(0);
			startAnimation(mAlphaAnimation);

		}
		
	}
	

	private class DownImgDisplayListener implements ImageLoadingListener {


		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

			if (loadedImage != null) {

				setDownImage(loadedImage);
			}
		}

		@Override
		public void onLoadingStarted(String imageUri, View view) {


		}

		@Override
		public void onLoadingFailed(String imageUri, View view,
				FailReason failReason) {
			
			if (mDownloadFailListener != null) {
				mDownloadFailListener.onDownloadFail();
			}
		}

		@Override
		public void onLoadingCancelled(String imageUri, View view) {

		}
	}

}
