package com.lhd.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.lhd.giveandreview.base.BaseActivity;

public class PkBaseFragment extends Fragment {
	
	public final String TAG = this.getClass().getSimpleName().trim();

	protected BaseActivity mActivity;

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = (BaseActivity)activity;
	}
	
	
}
