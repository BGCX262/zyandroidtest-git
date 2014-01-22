package com.zzypop.Button;



import com.nyist.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class LocalSearch extends Activity
{
	private ListView mylist;
	private int FLAG=2;
	private EditText strEdit;
	private Button poisearch;
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.near_search);
		mylist=(ListView)findViewById(R.id.mylist);
		strEdit=(EditText)findViewById(R.id.myedit);
		poisearch=(Button)findViewById(R.id.poisearch);
		
		dealList();
		poisearch.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				poiSearch();
			}
		});	
	}
	private void poiSearch()
	{
	  String strSearch=strEdit.getText().toString();
	if (strSearch.equals(""))
	{
			Toast.makeText(LocalSearch.this, "��Ǹ��Ҫ���ҵ����ݲ��ܿ�!", Toast.LENGTH_SHORT).show();
			
	}
	else {
		Intent intent =new Intent(getApplicationContext(),PoiSearch.class);
		intent.putExtra("strPoiSearch",strSearch);
		startActivity(intent);			
	}			
	}
	private void dealList()
	{	
        ListAdapter adapter=new ListAdapter(LocalSearch.this,FLAG);   
        mylist.setAdapter(adapter);
        mylist.setOnItemClickListener(new OnItemClickListener()
		{
            @Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3)
			{	
				switch (position)
				{
				case 0:
				    doNearSearch("��ʳ");
					break;
				case 1:
					doNearSearch("С�Կ��");
					break;
				case 2:
					doNearSearch("����");
					break;
				case 3:
					doNearSearch("����վ");
					break;
				case 4:
					doNearSearch("����");
					break;
				case 5:
					doNearSearch("KTV");
					break;	
				case 6:
					doNearSearch("����վ");
					break;	
				case 7:
					doNearSearch("����");
					break;
				case 8:
					doNearSearch("������");
					break;
				case 9:
					doNearSearch("��ӰԺ");
					break;
				case 10:
					doNearSearch("ҽԺ");
					break;	
				default:
					break;
				}
				// TODO Auto-generated method stub				
			}
		});		
	}
	private void doNearSearch(String strSearch)
	{
		// TODO Auto-generated method stub
		Intent intent =new Intent(getApplicationContext(),NearSearch.class);
		intent.putExtra("strSearch",strSearch);
		startActivity(intent);	
	}

}
