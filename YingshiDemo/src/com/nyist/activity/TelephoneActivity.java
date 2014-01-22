package com.nyist.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TelephoneActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.telephone_activity);
		Button telephone=(Button) findViewById(R.id.telephone);
		telephone.setOnClickListener(new OnClickListener() {
			
			@Override                                                              
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setAction("android.intent.action.DIAL");
			    intent.setData(Uri.parse("tel:0377-63253518"));
                 startActivity(intent);
			}
		});
	}
}
