//package com.zzypop.Button;
//
//
//
//import com.nyist.activity.R;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//
//public class MapTest extends Activity
//{
//	private Button button1,button2;
//	private Intent intent;
//	protected void onCreate(Bundle savedInstanceState)
//	{
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		
//		setContentView(R.layout.data_test);
//		
//		button1=(Button)findViewById(R.id.button);
//		
//		button2=(Button)findViewById(R.id.button2);
//		button1.setOnClickListener(new OnClickListener()
//		{
//			
//			@Override
//			public void onClick(View v)
//			{
//				// TODO Auto-generated method stub
//				String lon="112.556824";
//				String lat="32.97739";
//				String name="南阳理工学院";
//				intent=new Intent(getApplicationContext(),ObtainPosition.class);
//				intent.putExtra("long", lon);
//				intent.putExtra("lattude", lat);
//				intent.putExtra("poname", name);
//				startActivity(intent);
//			}
//		});
//		button2.setOnClickListener(new OnClickListener()
//		{
//			
//			@Override
//			public void onClick(View v)
//			{
//				// TODO Auto-generated method stub
//				Intent intent1=new Intent(getApplicationContext(),MyLocation.class);
//				startActivity(intent1);
//				
//			}
//		});
//		
//	}
//	
//
//}
