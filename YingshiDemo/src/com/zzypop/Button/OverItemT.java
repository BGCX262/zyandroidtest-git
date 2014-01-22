package com.zzypop.Button;

import java.util.ArrayList;
import java.util.List;



import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.OverlayItem;
import com.baidu.mapapi.Projection;
import com.nyist.activity.R;

class OverItemT extends ItemizedOverlay<OverlayItem>
{

	private Drawable marker;
	private List<OverlayItem> GeoList = new ArrayList<OverlayItem>();
	GeoPoint mpoint;
	private ObtainPosition mcontext;
	private Context context;
	private TextView my_location;
	private ImageView im_route,im_search;
	String position;
	public OverItemT(Drawable arg0,ObtainPosition context,GeoPoint point,String position)
	{
		super(arg0);
		// TODO Auto-generated constructor stub
		this.marker=arg0;
		this.mcontext=context;
		this.mpoint=point;	
		this.position=position;
		GeoList.add(new OverlayItem(point,"",""));
		populate();
	}

	@Override
	protected OverlayItem createItem(int i)
	{
		// TODO Auto-generated method stub
		return GeoList.get(i);
	}

	@Override
	public int size()
	{
		// TODO Auto-generated method stub
		return GeoList.size();
	}
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
	{
		// ±ê¼Ç
		super.draw(canvas, mapView, shadow);
	}


 @Override
 public boolean onTap(GeoPoint arg0, MapView mapView)
 {
// TODO Auto-generated method stub
   Point point = new Point();
   Projection projection = mapView.getProjection();
   projection.toPixels(mpoint, point);
   int x = point.x;
   int y = point.y;
   
   Point point1 = new Point();
   Projection projection1 = mapView.getProjection();
   projection.toPixels(arg0, point1);
   int x1 = point1.x;
   int y1 = point1.y;
   
	MapView.LayoutParams geoLP = (MapView.LayoutParams) mcontext.popView.getLayoutParams();
	geoLP.point = mpoint;
	mcontext.bmapsView.updateViewLayout(mcontext.popView, geoLP);
	im_route=(ImageView)mcontext.popView.findViewById(R.id.sroute);
	im_route.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			Intent intent=new Intent(mcontext,RouteSearch.class);
			mcontext.startActivity(intent);
			
		}
	});
	im_search=(ImageView)mcontext.popView.findViewById(R.id.in2);
	im_search.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			mcontext.setSearch();
			
		}
	});
	my_location=(TextView)mcontext.popView.findViewById(R.id.myloca);
	my_location.setOnClickListener(new OnClickListener()
	{
		
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
		 mcontext.setJump(position);
			
		}
	});
   if ((x-30<x1&&x1<x+30)&&(y-30<y1&&y1<y+30))
   {
  
	    mcontext.popView.setVisibility(View.VISIBLE);	    
   }
   else
   {
	mcontext.popView.setVisibility(View.GONE);
   }

	 return true;
  }


}	