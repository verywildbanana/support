package com.lhd.giveandreview.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.lhd.giveandreview.CouponPageActivity;
import com.lhd.giveandreview.GivePageActivity;
import com.lhd.giveandreview.MainActivity;
import com.lhd.giveandreview.MorePageActivity;
import com.lhd.giveandreview.R;
import com.lhd.giveandreview.ReviewPageActivity;
import com.lhd.view.PkOnOffToggleButton;

public abstract class BaseActivity extends FragmentActivity {


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


	protected PkOnOffToggleButton mHomeMenu;
	protected PkOnOffToggleButton mGivewMenu;
	protected PkOnOffToggleButton mReviewMenu;
	protected PkOnOffToggleButton mCouponMenu;
	protected PkOnOffToggleButton mMoreMenu;


	protected void setTabBtn() {

		mHomeMenu= (PkOnOffToggleButton)findViewById(R.id.HomeMenu);
		mHomeMenu.setOnClickListener(mTabClickListener);

		mGivewMenu = (PkOnOffToggleButton)findViewById(R.id.GivewMenu);
		mGivewMenu.setOnClickListener(mTabClickListener);

		mReviewMenu = (PkOnOffToggleButton)findViewById(R.id.ReviewMenu);
		mReviewMenu.setOnClickListener(mTabClickListener);

		mCouponMenu = (PkOnOffToggleButton)findViewById(R.id.CouponMenu);
		mCouponMenu.setOnClickListener(mTabClickListener);

		mMoreMenu  = (PkOnOffToggleButton)findViewById(R.id.MoreMenu);
		mMoreMenu.setOnClickListener(mTabClickListener);

	}

	View.OnClickListener mTabClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			switch (v.getId()) {

				case R.id.HomeMenu:

					PkIntentManager.getInstance().push(BaseActivity.this, MainActivity.class, true, Intent.FLAG_ACTIVITY_CLEAR_TOP, true);
					break;

				case R.id.GivewMenu:

					PkIntentManager.getInstance().push(BaseActivity.this, GivePageActivity.class, true, Intent.FLAG_ACTIVITY_CLEAR_TOP, true);
					break;

				case R.id.ReviewMenu:

					PkIntentManager.getInstance().push(BaseActivity.this, ReviewPageActivity.class, true, Intent.FLAG_ACTIVITY_CLEAR_TOP, true);
					break;

				case R.id.CouponMenu:

					PkIntentManager.getInstance().push(BaseActivity.this, CouponPageActivity.class, true, Intent.FLAG_ACTIVITY_CLEAR_TOP, true);
					break;
				case R.id.MoreMenu:

					PkIntentManager.getInstance().push(BaseActivity.this, MorePageActivity.class, true, Intent.FLAG_ACTIVITY_CLEAR_TOP, true);
					break;

			}

		}
	};


}
