package com.zzypop.Button;

import java.util.List;

import android.R.string;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.Toast;


import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.PoiOverlay;
import com.nyist.activity.R;


public class NearSearch extends MapActivity
{
  
	BMapManager mBMapMan = null;
	MapView bmapsView;
	Intent intent;
	MapController controller;
	LocationListener mLocationListener = null;//onResume时注册此listener，onPause时需要Remove
	MyLocationOverlay mLocationOverlay = null;	//定位图层
	MKSearch mMKSearch;
	ProgressDialog progDialog = null;
    GeoPoint pt;
    List<Overlay> ol;
    String strSearch; 
	protected void onCreate(Bundle arg0)
	{
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.near_map);
		
		//初始化BMapManager
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("3BFB2B30CD6198B25A1FF81887DE3677928FE677", null);
		super.initMapActivity(mBMapMan);
		init();		
	    mLocationListener = new LocationListener(){
			@Override
			public void onLocationChanged(Location location)
			{
				// TODO Auto-generated method stub
				if (location != null){					
					pt = new GeoPoint((int)(location.getLatitude()*1e6),
							(int)(location.getLongitude()*1e6));
				if (pt==null)
				{
					Toast.makeText(getApplicationContext(),
							"请检查网络连接是否正确?", Toast.LENGTH_SHORT).show();
					NearSearch.this.finish();
			
				}	
				else{
					controller.animateTo(pt);
					controller.setCenter(pt);
			        controller.setZoom(15);
				    ol.add(mLocationOverlay);
					createTh();	        
				}			
				}	 
		 }
	};
	}
	private void createTh()
	{
		Thread t = new Thread(new Runnable() 
		{
	 public void run()
		{	
		mMKSearch.poiSearchNearBy(strSearch, pt, 5000);  
	    }
		});	
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setIndeterminate(false);
		progDialog.setCancelable(true);
		progDialog.setMessage("正在获取相关信息 ");
		progDialog.show();
		t.start();	
	}
	/*处理结束 */
 private Handler handler=new Handler(){
	 
	 public void handleMessage(android.os.Message msg) {
		 super.handleMessage(msg);		 
		 progDialog.dismiss();
	 }; 
	 
 };
	private void init()
	{
		
		intent=getIntent();		
		strSearch=intent.getStringExtra("strSearch");
		// 获取界面上的MapView对象
		bmapsView = (MapView) findViewById(R.id.mapsView);
		progDialog=new ProgressDialog(this);
		// 初始化MKSearch
		mMKSearch = new MKSearch();
		mMKSearch.init(mBMapMan, new MySearchListener());
		mLocationOverlay = new MyLocationOverlay(this, bmapsView);	
		// 获取controller对象
		controller = bmapsView.getController();
		// 设置系统内置的缩放按钮
		bmapsView.setBuiltInZoomControls(true);
		// 获得MapView上原有的Overlay对象
	    ol= bmapsView.getOverlays();
	    
	}
	public class MySearchListener implements MKSearchListener{

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1)
		{
			// TODO Auto-generated method stub			
		}
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1)
		{
			// TODO Auto-generated method stub			
		}
		@Override
		public void onGetPoiResult(MKPoiResult result, int arg1, int arg2)
		{
			// TODO Auto-generated method stub
			PoiOverlay poioverlay = new PoiOverlay(NearSearch.this, bmapsView);  
			if (result==null)
			{
				Toast.makeText(getApplicationContext(),
						"请检查网络连接是否正确?", Toast.LENGTH_SHORT).show();
				handler.sendEmptyMessage(0);
			}else{
				poioverlay.setData(result.getAllPoi());  
				ol.add(poioverlay); 
				bmapsView.invalidate();  //刷新地图
			    handler.sendEmptyMessage(0);	
			}
			
		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult arg0, int arg1)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult arg0, int arg1)
		{
			// TODO Auto-generated method stub
			
		}		
	}
	@Override
	protected boolean isRouteDisplayed()
	{
		// TODO Auto-generated method stub
		return false;
	}
	protected void onDestroy() {
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mBMapMan != null) {
			mBMapMan.getLocationManager().removeUpdates(mLocationListener);
			mLocationOverlay.disableMyLocation();
	        mLocationOverlay.disableCompass(); // 关闭指南针
			mBMapMan.stop();
		}
		super.onPause();
	}
	@Override
	protected void onResume() {
		if (mBMapMan != null) {
			//申请查找位置
			mBMapMan.getLocationManager().requestLocationUpdates(mLocationListener);
	        mLocationOverlay.enableMyLocation();
	        mLocationOverlay.enableCompass(); // 打开指南针
			mBMapMan.start();
		}
		super.onResume();
	}	
}
