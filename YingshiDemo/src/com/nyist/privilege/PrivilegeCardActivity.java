package com.nyist.privilege;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


import com.nyist.activity.R;
import com.nyist.privilege.PrivilegeGroupActivity.Myadpter;
import com.nyist.privilege.PrivilegeGroupActivity.Viewholder;
import com.nyist.privilege_member.privilege_info;
import com.nyist.xmlfactory.XmlPrivilegeFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PrivilegeCardActivity  extends Activity
{
    private ListView listView;
    private Button return_spe;
    private Myadpter adapter;
    private List<privilege_info> list=new ArrayList<privilege_info>();
    private int images[]={R.drawable.yiluo2,R.drawable.yiluo1};
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.privilege_card);
            listView=(ListView)findViewById(R.id.will_listview);
	        
	        try
			{
	        	SAXParserFactory xmlFactory = SAXParserFactory.newInstance();
				XMLReader reader = xmlFactory.newSAXParser().getXMLReader();
			    XmlPrivilegeFactory  xmltuqu = new XmlPrivilegeFactory(list);
				reader.setContentHandler(xmltuqu);
				InputStream in = getResources().openRawResource(R.raw.card_info);
				reader.parse(new InputSource(in));
	        	
			} catch (Exception e)
			{
				e.printStackTrace();
			}
	        adapter=new Myadpter(PrivilegeCardActivity.this,list);
	        listView.setAdapter(adapter);
	       listView.setOnItemClickListener(new OnItemClickListener()
		   {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int postion,
					long arg3)
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(PrivilegeCardActivity.this,PrivilegeCardDetailActivity.class);
				intent.putExtra("image", postion);
				intent.putExtra("name",list.get(postion).getname());
				intent.putExtra("brief",list.get(postion).getbrief());			
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
	        private List<privilege_info> filmInfos;
	        public Myadpter(PrivilegeCardActivity filmWillActivity,List<privilege_info> filmInfos)
	        {
	        	this.inflater=LayoutInflater.from(filmWillActivity);
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
				convertView=inflater.inflate(R.layout.privilege_tiem, null);
				
			}
			viewholder.imageView=(ImageView)convertView.findViewById(R.id.image);
			viewholder.textView1=(TextView)convertView.findViewById(R.id.name);
			viewholder.textView2=(TextView)convertView.findViewById(R.id.brief);
			viewholder.imageView.setImageResource(images[position]);
			viewholder.textView1.setText(filmInfos.get(position).getname());
			viewholder.textView2.setText(filmInfos.get(position).getbrief());

			
			
			return convertView;
		}
		 
		 
	 }

}
