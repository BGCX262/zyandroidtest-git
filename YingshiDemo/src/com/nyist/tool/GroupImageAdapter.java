package com.nyist.tool;




import com.nyist.activity.R;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class GroupImageAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private int mflag;
	private static final int[] images1 = {R.drawable.ironman1, R.drawable.ironman2, R.drawable.ironman3};
	private static final int[] images2 = {R.drawable.yong4, R.drawable.yong3};
	private static final int[] images3 = {R.drawable.yiluo1, R.drawable.yiluo2, R.drawable.yiluo3};
	private static final int[] images4 = {R.drawable.craods1, R.drawable.craods2, R.drawable.crados3};
	public GroupImageAdapter(Context context,int flag) {
		mContext = context;
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mflag=flag;
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
		if(mflag==0)
		{
			((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(images1[position%images1.length]);	
			
		}else if(mflag==1) {
			((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(images2[position%images2.length]);	
		}else if(mflag==2) {
			((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(images3[position%images3.length]);	
		}else if(mflag==3) {
			((ImageView) convertView.findViewById(R.id.imgView)).setImageResource(images4[position%images4.length]);	
		}

		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
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
