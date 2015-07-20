package com.lhd.giveandreview.base;

import android.app.Activity;
import android.os.Bundle;

import com.lhd.giveandreview.R;
import com.lhd.view.PkViewPager;

public abstract class BaseActivity extends Activity {


	public final String TAG = this.getClass().getSimpleName().trim();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setIntent();
		initActivity();
	}


	abstract public void setIntent();
	abstract public void initActivity();


	@Override
	public void onBackPressed() {

		if(PkIntentManager.getInstance().isFirstActivity()) {

			PkIntentManager.getInstance().popForNoAnim(this);
		}
		else {
			PkIntentManager.getInstance().pop(this);
		}
	}
}
