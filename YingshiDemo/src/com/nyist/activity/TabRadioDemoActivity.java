package com.nyist.activity;

import com.nyist.connectUtil.isConnect_Internet;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class TabRadioDemoActivity extends TabActivity implements
		OnCheckedChangeListener {
	private static final String TAG = "TabRadioDemoActivity";
	private RadioButton rb_home, rb_search, rb_camera, rb_selfinfo, rb_more;
	private Intent homeIntent, searchIntent, cameraIntent, selfinfoIntent,
			moreIntent;
	private TabHost mTabHost;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_radio);
		/*****
		 * ÅÐ¶ÏÍøÂç
		 * ****/
		if (!isConnect_Internet.isConnect(getApplicationContext())) {
			isConnect_Internet.setNetworkMethod(TabRadioDemoActivity.this);
		}
		homeIntent = new Intent(TabRadioDemoActivity.this, HomeActivity.class);
		searchIntent = new Intent(TabRadioDemoActivity.this, MenuActivity.class);
		cameraIntent = new Intent(TabRadioDemoActivity.this, CompanyInfo.class);
		selfinfoIntent = new Intent(TabRadioDemoActivity.this,
				TelephoneActivity.class);
		moreIntent = new Intent(TabRadioDemoActivity.this, MoreActivity.class);
		setupIntent();
		findView();
	}

	private void setupIntent() {
		mTabHost = getTabHost();
		TabHost mainTabHost = this.mTabHost;
		mainTabHost.addTab(buildTabSpec("home", R.string.main_rb_home,
				this.homeIntent));
		mainTabHost.addTab(buildTabSpec("search", R.string.main_rb_search,
				this.searchIntent));
		mainTabHost.addTab(buildTabSpec("photoes", R.string.main_rb_camera,
				this.cameraIntent));
		mainTabHost.addTab(buildTabSpec("notes", R.string.main_rb_selfinfo,
				this.selfinfoIntent));
		mainTabHost.addTab(buildTabSpec("more", R.string.main_rb_more,
				this.moreIntent));
		mainTabHost.setCurrentTab(0);
	}

	private void findView() {
		rb_home = (RadioButton) this.findViewById(R.id.main_rb_home);
		rb_search = (RadioButton) this.findViewById(R.id.main_rb_search);
		rb_camera = (RadioButton) this.findViewById(R.id.main_rb_camera);
		rb_selfinfo = (RadioButton) this.findViewById(R.id.main_rb_selfinfo);
		rb_more = (RadioButton) this.findViewById(R.id.main_rb_more);
		rb_home.setOnCheckedChangeListener(this);
		rb_search.setOnCheckedChangeListener(this);
		rb_camera.setOnCheckedChangeListener(this);
		rb_selfinfo.setOnCheckedChangeListener(this);
		rb_more.setOnCheckedChangeListener(this);
	}

	private TabHost.TabSpec buildTabSpec(String tag, int resLabel,
			final Intent content) {
		return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel))
				.setContent(content);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			switch (buttonView.getId()) {
			case R.id.main_rb_home:
				mTabHost.setCurrentTabByTag("home");
				break;
			case R.id.main_rb_search:
				mTabHost.setCurrentTabByTag("search");
				break;
			case R.id.main_rb_camera:
				mTabHost.setCurrentTabByTag("photoes");
				break;
			case R.id.main_rb_selfinfo:
				mTabHost.setCurrentTabByTag("notes");
				break;
			case R.id.main_rb_more:
				mTabHost.setCurrentTabByTag("more");
			}
		}
	}
}