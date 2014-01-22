package com.nyist.sale;

import java.io.File;
import java.util.List;

import com.nyist.activity.R;
import com.nyist.activity.TabRadioDemoActivity;
import com.nyist.connectUtil.ProcessDialogshow;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Sale extends Activity {
	ListView listView;
	File cache;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			listView.setAdapter(new ContactAdapter(Sale.this,
					(List<Contact>) msg.obj, R.layout.listview_item, cache));
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale);
		
		
		
		
		listView = (ListView) this.findViewById(R.id.listView);
		Button back=(Button) findViewById(R.id.go_back);
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				startActivity(new Intent(Sale.this,TabRadioDemoActivity.class));
				finish();
			}
		});

		ProcessDialogshow.processshow(Sale.this);
		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		if (!cache.exists())
			cache.mkdirs();
		new Thread(new Runnable() {
			
			public void run() {
				try {
					 List<Contact>    data = ContactService.getContacts();
				
					handler.sendMessage(handler.obtainMessage(22, data));
				} catch (Exception e) {
					e.printStackTrace();
				
				}
			}
		}).start();
		 Toast.makeText(Sale.this, "服务器繁忙，请稍后再试！", 1).show();
	
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(Sale.this, "你点击了" + arg2, 1).show();
				
				
				
				
				
			}
		});
	}


	// @Override
	// 删除下载好的图片
	// protected void onDestroy() {
	// for(File file : cache.listFiles()){
	// file.delete();
	// }
	// cache.delete();
	// super.onDestroy();
	// }

}
