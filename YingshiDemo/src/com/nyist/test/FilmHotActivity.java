package com.nyist.test;



import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.nyist.activity.R;




import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FilmHotActivity  extends Activity
{
    private ListView listView;
    private Button return_spe;
    private Myadpter adapter;
    private List<film_info> list=new ArrayList<film_info>();
    private int images[]={R.drawable.ironman1,R.drawable.yong3,R.drawable.yiluo1,R.drawable.craods1};
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.film_hot);
	        listView=(ListView)findViewById(R.id.hot_listview);
	        
	        try
			{
	        	SAXParserFactory xmlFactory = SAXParserFactory.newInstance();
				XMLReader reader = xmlFactory.newSAXParser().getXMLReader();
			    XmlFilmFactory  xmltuqu = new XmlFilmFactory(list);
				reader.setContentHandler(xmltuqu);
				InputStream in = getResources().openRawResource(R.raw.hot_info);
				reader.parse(new InputSource(in));
	        	
			} catch (Exception e)
			{
				e.printStackTrace();
			}
	        adapter=new Myadpter(FilmHotActivity.this, list);
	        listView.setAdapter(adapter);
	       listView.setOnItemClickListener(new OnItemClickListener()
		   {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int postion, long arg3)
			{
				// TODO Auto-generated method stub
				
				Intent intent=new Intent();
				intent.setClass(FilmHotActivity.this,FilmHotDetailActivity.class);
				intent.putExtra("image", postion);
				intent.putExtra("name",list.get(postion).getname());
				intent.putExtra("brief",list.get(postion).getbrief());
				intent.putExtra("time", list.get(postion).gettime());
				intent.putExtra("price", list.get(postion).getprice());				
				startActivity(intent);				
			}
		    });       
}
	 public final class Viewholder
     {
	          public ImageView imageView;
	          public TextView textView1;
	          public TextView textView2;
	          public TextView textView3;
	  }
	 public class Myadpter extends BaseAdapter
	 {
		 private LayoutInflater inflater;
	        private List<film_info> filmInfos;
	        public Myadpter(FilmHotActivity activity,List<film_info> filmInfos)
	        {
	        	this.inflater=LayoutInflater.from(activity);
	        	this.filmInfos=filmInfos;
	        }
	    	

		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return filmInfos.size()-1;
		}

		@Override
		public Object getItem(int arg0)
		{
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			Viewholder viewholder=new Viewholder();
			if(convertView==null)
			{
				convertView=inflater.inflate(R.layout.film_tiem, null);
				
			}
			viewholder.imageView=(ImageView)convertView.findViewById(R.id.image);
			viewholder.textView1=(TextView)convertView.findViewById(R.id.name);
			viewholder.textView2=(TextView)convertView.findViewById(R.id.brief);
			viewholder.textView3=(TextView)convertView.findViewById(R.id.time);
			viewholder.imageView.setImageResource(images[position]);
			viewholder.textView1.setText(filmInfos.get(position).getname());
			viewholder.textView2.setText(filmInfos.get(position).getbrief());
			viewholder.textView3.setText(filmInfos.get(position).gettime());					
			return convertView;
		}
		 
		 
	 }

}
