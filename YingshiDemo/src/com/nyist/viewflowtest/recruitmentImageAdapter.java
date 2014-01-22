package com.nyist.viewflowtest;


import com.nyist.activity.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class recruitmentImageAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	  Uri uri;
	private static final int[] ids = { R.drawable.recruitment1,
			R.drawable.recruitment2, R.drawable.recruitment3,
			R.drawable.recruitment4};

	public recruitmentImageAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		 //返回很大的值使得getView中的position不断增大来实现循环
		return Integer.MAX_VALUE;  
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.image_item, null);
		}
		((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(ids[position%ids.length]);
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (position%ids.length==0) {
					  uri = Uri.parse("http://www.58.com"); 
				}
				if (position%ids.length==1) {
					  uri = Uri.parse("http://www.51job.com"); 
				}
				if (position%ids.length==2) {
					  uri = Uri.parse("http://www.zhaopin.com"); 
				}
				if (position%ids.length==3) {
					  uri = Uri.parse("http://www.dajie.com"); 
					 
				}
				 Intent intent = new Intent(Intent.ACTION_VIEW, uri);  
				  mContext.startActivity(intent);
				  
				//在这里可以设置跳转界面
//				Intent intent = new Intent(mContext,DetailActivity.class);
//				Bundle bundle = new Bundle();
//				bundle.putInt("image_id", ids[position%ids.length]);
//				intent.putExtras(bundle);
//				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

}
