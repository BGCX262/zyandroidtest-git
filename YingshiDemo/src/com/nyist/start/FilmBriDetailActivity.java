package com.nyist.start;








import com.nyist.activity.R;
import com.nyist.tool.CircleFlowIndicator;
import com.nyist.tool.CirImageAdapter;
import com.nyist.tool.ViewFlow;
import com.nyist.tool.FaceImageAdapter;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FilmBriDetailActivity extends Activity {  
	  private ImageView imageView=null;
	  private TextView textView1,textView2,textView3,textView4;	
	  private int Flag;
	  public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.start_tiem_detail);
	        Intent intent=null;
			intent = getIntent();
	        Flag  =intent.getIntExtra("image",0); 
	        
	        this.textView1=(TextView)findViewById(R.id.film_name);
	        this.textView2=(TextView)findViewById(R.id.film_brief);

	        circleimage();
		 
	        textView1.setText(intent.getStringExtra("name"));
	        textView2.setText("简  介:"+intent.getStringExtra("brief"));        
	        
	}
	
	  void circleimage() {
			
			ViewFlow viewFlow;
			viewFlow = (ViewFlow) findViewById(R.id.viewflow);
			viewFlow.setAdapter(new  CirImageAdapter (this,Flag));
			viewFlow.setmSideBuffer(3); 
			CircleFlowIndicator indic = (CircleFlowIndicator) findViewById(R.id.film_hot_flow);
			viewFlow.setFlowIndicator(indic);
			viewFlow.setTimeSpan(4500);
			viewFlow.setSelection(3 * 1000); // 设置初始位置
			viewFlow.startAutoFlowTimer(); // 启动自动播放

		}
}
