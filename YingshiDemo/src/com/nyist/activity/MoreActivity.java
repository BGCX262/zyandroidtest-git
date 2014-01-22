package com.nyist.activity;

import com.nyist.connectUtil.ProcessDialogshow;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MoreActivity extends Activity {

	private Button aboutours,contact_us,refresh;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_activity);
	
		
		 aboutours=(Button) findViewById(R.id.aboutours);
		 contact_us=(Button) findViewById(R.id.contact_us);
		 refresh=(Button) findViewById(R.id.refresh);
		   refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ProcessDialogshow.processshow(MoreActivity.this);
				
			}
		});
		 
		 aboutours.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView msg = new TextView(MoreActivity.this);
		        msg.setText(R.string.ours_info);  
		  
		        new AlertDialog.Builder(MoreActivity.this)  
		                .setTitle("关于我们")  
		                .setView(msg) 
		                  .setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						}).create()  
			            .show(); 

			}
		});
		 contact_us.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView msg = new TextView(MoreActivity.this);
				msg.setLinksClickable(true);
		        msg.setText(R.string.email);  
		        
		        new AlertDialog.Builder(MoreActivity.this)  
		                .setTitle("联系我们")  
		                .setView(msg) 
		                  .setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								
							}
						}).create()  
			            .show(); 
			}
		});
	}

}
