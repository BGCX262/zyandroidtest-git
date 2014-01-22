package com.zzypop.Button;



import com.nyist.activity.R;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter
{
	private LayoutInflater mInflater;
	private Context con;
	private int  flag;
	private int[] images={R.drawable.icon_class_meishi,R.drawable.icon_class_xiaochikuaican,R.drawable.icon_class_hotel
			,R.drawable.icon_class_bus_station,R.drawable.icon_class_bank,R.drawable.icon_class_ktv
			,R.drawable.icon_class_petrolstation,R.drawable.icon_class_viewspot,R.drawable.icon_class_coffee,
			R.drawable.icon_class_cinema,R.drawable.icon_class_hospital};
	private String[] text={"美食","小吃快餐","宾馆","公交站","银行","KTV","加油站","景点","咖啡厅","电影院","医院"};
	
	private int[] setImag={R.drawable.icon_nav_share,R.drawable.icon_poi_favor};
	private String[] setText={"分享我的位置","收藏我的位置"};
	
    public ListAdapter(Context context,int flag)
    {
		this.con=context;
		mInflater=LayoutInflater.from(con);
    	this.flag=flag;
    }	
	public final class ViewHolder{  
		
		public TextView list_description;
		public ImageView list_image;
	}
	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		if (flag==1)
		{
			return setImag.length;
		}
		else {
			return images.length;
		}
		
	}
	@Override
	public Object getItem(int position)
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
		// TODO Auto-generated method stub
		    ViewHolder holder=new ViewHolder();
		if (convertView==null)
		{
			convertView=mInflater.inflate(R.layout.my_item,null);						
		}
		if (flag==1)
		{
			holder.list_image=(ImageView)convertView.findViewById(R.id.setimage);
			holder.list_description=(TextView)convertView.findViewById(R.id.setname);
			holder.list_description.setText(setText[position]);
			holder.list_image.setImageResource(setImag[position]);
		}else {
		
			holder.list_image=(ImageView)convertView.findViewById(R.id.setimage);
			holder.list_description=(TextView)convertView.findViewById(R.id.setname);
			holder.list_description.setText(text[position]);
			holder.list_image.setImageResource(images[position]);
		}

		
		return convertView;
	}
}
