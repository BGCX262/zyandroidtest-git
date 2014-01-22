package com.nyist.liuyan;




import com.nyist.activity.R;
import com.nyist.connectUtil.ProcessDialogshow;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LiuyanActivity extends Activity {
	EditText liuyantext;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liuyan);
        liuyantext=(EditText) findViewById(R.id.liuyantext);
    }
    public void save(View v){
		boolean result=false;
		String text=liuyantext.getText().toString();
		try {
			result=postService.save(text);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result) {
			ProcessDialogshow.processshow(LiuyanActivity.this);
			Toast.makeText(this, "感谢您的留言 ，稍后工作人员将会对此进行处理",1).show();
		liuyantext.setText("	感谢您的留言 ，稍后工作人员将会对此进行处理。");
		}else {
			ProcessDialogshow.processshow(LiuyanActivity.this);
			Toast.makeText(this, "感谢您的留言 ，稍后工作人员将会对此进行处理",1).show();
			liuyantext.setText("	感谢您的留言 ，稍后工作人员将会对此进行处理。");
		}
		
	}
}