package com.nyist.test;







import com.nyist.activity.R;
import com.nyist.viewflowtest.CircleFlowIndicator;
import com.nyist.viewflowtest.ViewFlow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FilmTeDetailActivity extends Activity {  
	  private ImageView imageView=null;
	  private TextView textView1,textView2,textView3,textView4;	
	  private int Flag;
	  public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.film_tiem_detail);
	        Intent intent=null;
			intent = getIntent();
	        Flag  =intent.getIntExtra("image",0); 
	        
	        this.textView1=(TextView)findViewById(R.id.film_name);
	        this.textView2=(TextView)findViewById(R.id.film_brief);
	        this.textView3=(TextView)findViewById(R.id.film_time);
	        this.textView4=(TextView)findViewById(R.id.film_price);
	        circleimage();
		 
	        textView1.setText(intent.getStringExtra("name"));
	        textView2.setText("简  介:"+intent.getStringExtra("brief"));
	        textView3.setText(intent.getStringExtra("time")); 
	        textView4.setText("价 格:"+intent.getStringExtra("price")); 
	        
	        
	}
	
	  void circleimage() {
			
			ViewFlow viewFlow;
			viewFlow = (ViewFlow) findViewById(R.id.viewflow);
			viewFlow.setAdapter(new  TeImageAdapter (this,Flag));
			viewFlow.setmSideBuffer(3); 
			CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.film_hot_flow);
			viewFlow.setFlowIndicator(indic);
			viewFlow.setTimeSpan(4500);
			viewFlow.setSelection(3 * 1000); // 设置初始位置
			viewFlow.startAutoFlowTimer(); // 启动自动播放

		}
}
