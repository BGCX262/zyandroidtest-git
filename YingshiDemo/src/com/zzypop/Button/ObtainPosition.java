package com.zzypop.Button;

import java.util.ArrayList;
import java.util.List;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.ItemizedOverlay;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiInfo;
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
import com.baidu.mapapi.OverlayItem;
import com.baidu.mapapi.Projection;
import com.nyist.activity.R;

public class ObtainPosition extends MapActivity implements Runnable {
	/** Called when the activity is first created. */
	MapView bmapsView;
	Bitmap posBitmap;
	TextView position_name,position;
	BMapManager mBMapMan = null;
	ProgressDialog progressDialog;
	MapController controller;
    Intent intent;
    String name;
    StringBuffer pos;
    double lon,lat;
    View popView;
    GeoPoint pt;
    List<Overlay> ol;
	LocationListener mLocationListener = null;//onResume时注册此listener，onPause时需要Remove
	MyLocationOverlay mLocationOverlay = null;	//定位图层
	private MKSearch mMKSearch;
  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.map);
		
		pos=new StringBuffer();
		position_name=(TextView)findViewById(R.id.posiname);
		//初始化BMapManager
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("3BFB2B30CD6198B25A1FF81887DE3677928FE677", null);
		super.initMapActivity(mBMapMan);
		

		// 获取界面上的MapView对象
		bmapsView = (MapView) findViewById(R.id.bmapsView);
		//获取数据
		intent=getIntent();
		
		lon=Double.parseDouble(intent.getStringExtra("long"));
		lat=Double.parseDouble(intent.getStringExtra("lattude"));
		name=intent.getStringExtra("poname");
		
		popView=super.getLayoutInflater().inflate(R.layout.pop_map_view, null);
        bmapsView.addView( popView,
                new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT,
              null, MapView.LayoutParams.BOTTOM_CENTER));
        popView.setVisibility(View.GONE);
        
        
		// 初始化MKSearch
		mMKSearch = new MKSearch();
		mMKSearch.init(mBMapMan, new MySearchListener());
		
		//设置名字
		position_name.setText(name);
		
		posBitmap = BitmapFactory
				.decodeResource(getResources(), R.drawable.pos);

		
		mLocationOverlay = new MyLocationOverlay(this, bmapsView);
		
		// 获取controller对象
		controller = bmapsView.getController();

		// 设置系统内置的缩放按钮
		bmapsView.setBuiltInZoomControls(true);

		 mLocationListener = new LocationListener(){

				@Override
				public void onLocationChanged(Location location) {
					if (location != null){
						
						pt = new GeoPoint((int)(location.getLatitude()*1e6),
								(int)(location.getLongitude()*1e6));
					if (pt==null)
					{
                        getInfo();
					}	
					else{
						controller.animateTo(pt);
						controller.setCenter(pt);
						// 查询该经纬度值所对应的地址位置信息
						mMKSearch.reverseGeocode(pt);	
						
						ol.add(new OverItemT(null,ObtainPosition.this,pt,pos.toString()));       
					}	        
					}
				}
		    };			
        this.progressDialog=ProgressDialog.show(this,"请稍后...","正在地图上定位...." 
     		   ,true,false);
        
        Thread thread=new Thread(this);
        thread.start();
        
	}
	
	/*处理结束 */
 private Handler handler=new Handler(){
	 
	 public void handleMessage(android.os.Message msg) {
		 super.handleMessage(msg);
		 
		 progressDialog.dismiss();
	 }; 
	 
 };

  @Override
   public void run()
   {
	// TODO Auto-generated method stub
	  //开始查找位置
	  updateMapView(lon, lat);
	  
	 
	  handler.sendEmptyMessage(0);
   }
  
	private void getInfo()
	{
		 pos.append("暂时无法获取你的位置!");
		 Toast.makeText(getApplicationContext(),
					"请检查网络连接是否正确?", Toast.LENGTH_SHORT).show();
	}

	
	/**
	 * 内部类实现MKSearchListener接口,用于实现异步搜索服务
	 * 
	 */
	
  
  public class MySearchListener implements MKSearchListener
  {

		@Override
		public void onGetAddrResult(MKAddrInfo result, int arg1)
		{
			// TODO Auto-generated method stub
	         if (pos.toString().length()!=0)
			{
				pos=new StringBuffer();
			} 	 
			if( arg1 != 0 || result == null)
			{  			 
				 getInfo();					
			}
			else 
			{	
			       pos.append("我现在的位置:"+result.strAddr+"附近");		        	 
		    }			
		}

        /*
         * 驾车路线查询
         */
		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult arg0, int arg1)
		{
			// TODO Auto-generated method stub			
		}

		@Override
		public void onGetPoiResult(MKPoiResult res, int arg1, int arg2)
		{
			// TODO Auto-generated method stub		
			
			
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
	public void setJump(String position)
	{
	    Intent intent1=new Intent(ObtainPosition.this,OperaLocation.class);
		float x=(float) (pt.getLatitudeE6()/1E6);
		float y=(float) (pt.getLongitudeE6()/1E6);
		intent1.putExtra("position", position);
		intent1.putExtra("latpoint", x);
		intent1.putExtra("lngpoint", y);
		startActivity(intent1);
	}
	public void setSearch()
	{
		Intent intent=new Intent(getApplicationContext(),LocalSearch.class);
		startActivity(intent);
		
	}
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	// 根据经度、纬度将MapView定位到指定地点的方法
	private void updateMapView(double lng, double lat) {
		// 将经纬度信息包装成GeoPoint对象
		GeoPoint gp = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		// 设置显示放大缩小按钮
		bmapsView.displayZoomControls(true);
		controller.setZoom(20);
		// 获得MapView上原有的Overlay对象
		 ol= bmapsView.getOverlays();
		// 清除原有的Overlay对象
		ol.clear();
		
		ol.add(mLocationOverlay);
		// 添加一个新的OverLay对象
		ol.add(new PosOverLay(gp, posBitmap,name));
		
		
	}
	

//	 Override以下方法,管理API:
	@Override
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
