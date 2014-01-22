package com.zzypop.Button;



import com.nyist.activity.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class OperaLocation extends Activity
{
    private TextView my_loca;
    private Button viewmap,resultmap,goresult,nearsearch;
    private ListView mylist;
    private int FLAG=1;
    private Intent intent,shareInt;
    private String position,lat,lon;
	protected void onCreate(Bundle savedInstanceState)
    {
	// TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.my_location);		
		init();
		dealList();	
    }
	private void init()
	{
		my_loca=(TextView)findViewById(R.id.myloca);
		viewmap=(Button)findViewById(R.id.viewmap);
		resultmap=(Button)findViewById(R.id.resultmap);
		goresult=(Button)findViewById(R.id.goresult);
		nearsearch=(Button)findViewById(R.id.nearseach);
		mylist=(ListView)findViewById(R.id.mylist);
		intent=getIntent();
		position=intent.getStringExtra("position");
		my_loca.setText(position);
		viewmap.setOnClickListener(new myListener());
		nearsearch.setOnClickListener(new myListener());
	
	}
	
   private void	dealList()
   {
	    ListAdapter adapter=new ListAdapter(OperaLocation.this,FLAG);   
        mylist.setAdapter(adapter);
 
        mylist.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3)
			{
				switch (arg2)
				{
				case 0:
			        shareInt=new Intent(Intent.ACTION_SEND);
			        shareInt.setType("text/plain");   
			        shareInt.putExtra(Intent.EXTRA_SUBJECT, "选择分享方式");   
			        shareInt.putExtra(Intent.EXTRA_TEXT, position);    
			        shareInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			        startActivity(shareInt);
					break;
                
				default:
					break;
				}
				// TODO Auto-generated method stub
				
			}
		})  ;
   }
   private class myListener implements OnClickListener
   {
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.viewmap:
			OperaLocation.this.finish();			
			break;
			
		case R.id.nearseach:
			Intent intent =new Intent(getApplicationContext(),LocalSearch.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
		
		
	}
	   
	   
   }
	
}
