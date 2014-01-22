package com.nyist.activity;

import com.nyist.viewflowtest.CircleFlowIndicator;
import com.nyist.viewflowtest.ViewFlow;
import com.nyist.viewflowtest.recruitmentImageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Zhaopin extends Activity {

	Button go_back;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recruitment);
		go_back=(Button) findViewById(R.id.go_back);
		go_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				finish();
			}
		});
		circleimage();
		
	}
	
void circleimage() {
		
		ViewFlow viewFlow;
		viewFlow = (ViewFlow) findViewById(R.id.recruitmentviewflow);
		viewFlow.setAdapter(new  recruitmentImageAdapter (this)
				
				);
		viewFlow.setmSideBuffer(4); // 实际图片张数， 我的ImageAdapter实际图片张数为3
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.recitmentindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(3 * 1000); // 设置初始位置
		viewFlow.startAutoFlowTimer(); // 启动自动播放

	}

}
