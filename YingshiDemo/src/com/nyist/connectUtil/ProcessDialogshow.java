package com.nyist.connectUtil;

import com.nyist.sale.Sale;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;


public class ProcessDialogshow {
	static ProgressDialog myProgressDialog;
	
	public static void processshow(Context context) {
		
		myProgressDialog = ProgressDialog.show(context,      
                "请稍等...", "正在努力加载数据...", true);
      
      new Thread() {
           public void run() {
                try{
                     // Do some Fake-Work
                     sleep(5000);
                } catch (Exception e) {  }
                // Dismiss the Dialog 
                myProgressDialog.dismiss();
           }
      }.start();
	}
}
