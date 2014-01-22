package com.nyist.activity;

import com.nyist.viewflowtest.CircleFlowIndicator;
import com.nyist.viewflowtest.ViewFlow;
import com.nyist.viewflowtest.companyImageAdapter;
import com.zzypop.Button.ObtainPosition;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CompanyInfo  extends Activity {
	Button button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.company_info);
		
		button1=(Button)findViewById(R.id.companyinfolocation);
		button1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String lon="112.556824";
				String lat="32.97739";
				String name="南阳理工学院";
				Intent intent;
				intent=new Intent(getApplicationContext(),ObtainPosition.class);
				intent.putExtra("long", lon);
				intent.putExtra("lattude", lat);
				intent.putExtra("poname", name);
				startActivity(intent);
			}
		});
		
		circleimage();
		
	}
	
void circleimage() {
		
		ViewFlow viewFlow;
		viewFlow = (ViewFlow) findViewById(R.id.viewflow);
		viewFlow.setAdapter(new  companyImageAdapter (this)
				
				);
		viewFlow.setmSideBuffer(4); // 实际图片张数， 我的ImageAdapter实际图片张数为3
		CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.viewflowindic);
		viewFlow.setFlowIndicator(indic);
		viewFlow.setTimeSpan(4500);
		viewFlow.setSelection(3 * 1000); // 设置初始位置
		viewFlow.startAutoFlowTimer(); // 启动自动播放

	}

}
