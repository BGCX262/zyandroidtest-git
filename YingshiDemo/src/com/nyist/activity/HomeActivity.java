package com.nyist.activity;

import com.nyist.viewflowtest.CircleFlowIndicator;
import com.nyist.viewflowtest.ImageAdapter;
import com.nyist.viewflowtest.ViewFlow;

import android.app.Activity;
import android.os.Bundle;


public class HomeActivity extends Activity {


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewflowdemo);
		circleimage();
	}

	void circleimage() {
		
		ViewFlow viewFlow;
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		viewFlow.setAdapter(new ImageAdapter(this));
		viewFlow.setmSideBuffer(4); // 实际图片张数， 我的ImageAdapter实际图片张数为3
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(3 * 1000); // 设置初始位置
		viewFlow.startAutoFlowTimer(); // 启动自动播放

	}
}
