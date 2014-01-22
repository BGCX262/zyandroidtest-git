package com.zzypop.Button;

import java.util.List;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPlanNode;
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
import com.baidu.mapapi.RouteOverlay;
import com.baidu.mapapi.TransitOverlay;
import com.nyist.activity.R;



import com.zzypop.Button.PoiSearch.MySearchListener;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RouteSearch extends MapActivity
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
    TextView startText,endText;
    Button transit,drive,walk;
    protected void onCreate(Bundle arg0)
    {
    	// TODO Auto-generated method stub
    	super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.routeplan);
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
					RouteSearch.this.finish();
			
				}	
				else{
					controller.animateTo(pt);
					controller.setCenter(pt);
			        controller.setZoom(15);
				    ol.add(mLocationOverlay);
						        
				}			
				}	 
		 }
	};
		
		
    }
    private void init()
	{
		// 获取界面上的MapView对象
		bmapsView = (MapView) findViewById(R.id.bmapView);
		progDialog=new ProgressDialog(this);
		startText=(TextView)findViewById(R.id.start);
		endText=(TextView)findViewById(R.id.end);
		
		transit=(Button)findViewById(R.id.transit);
		drive=(Button)findViewById(R.id.drive);
		walk=(Button)findViewById(R.id.walk);
		
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
	    startText.setText("我现在的位置");
	    
	    transit.setOnClickListener(new routeOpera());
	    drive.setOnClickListener(new routeOpera());
	    walk.setOnClickListener(new routeOpera());
	}
	private void createTh(final int flag)
	{
		Thread t = new Thread(new Runnable() 
		{
	    public void run()
		{	
			MKPlanNode stNode = new MKPlanNode();
			stNode.pt=pt;			
			MKPlanNode enNode = new MKPlanNode();
			enNode.name = endText.getText().toString();
			if (flag==1)
			{
				mMKSearch.transitSearch("南阳", stNode, enNode);
			}
			else if (flag==2)
			{
				mMKSearch.drivingSearch("南阳", stNode, "南阳", enNode);	
			} 
			else if (flag==3)
			{
				mMKSearch.walkingSearch("南阳", stNode, "南阳", enNode);
			} 

			
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
    private void getSearch()
    {
    	
    	
    }
    
    private class routeOpera implements OnClickListener 
    {

		@Override
		public void onClick(View v)
		{
			// 对起点终点的name进行赋值，也可以直接对坐标赋值，赋值坐标则将根据坐标进行搜索		

			switch (v.getId())
			{

			
			case R.id.transit:
				
				createTh(1);
				break;
				
			case R.id.drive:
				
                createTh(2);
				break;	
			case R.id.walk:
				
				createTh(3);
				break;		

			default:
				break;
			}
			
		}
    }
    public class MySearchListener implements MKSearchListener{

		@Override
		public void onGetAddrResult(MKAddrInfo arg0, int arg1)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error)
		{
			// TODO Auto-generated method stub
			if (error != 0 || res == null) {
				Toast.makeText(RouteSearch.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
				handler.sendEmptyMessage(0);
				return;
			}
			RouteOverlay routeOverlay = new RouteOverlay(RouteSearch.this, bmapsView);
		    // 此处仅展示一个方案作为示例
		    routeOverlay.setData(res.getPlan(0).getRoute(0));
		    ol.clear();
		    ol.add(routeOverlay);
		    bmapsView.invalidate();
		    
		    controller.animateTo(res.getStart().pt);
		    handler.sendEmptyMessage(0);
		}

		@Override
		public void onGetPoiResult(MKPoiResult arg0, int arg1, int arg2)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult res,
				int error)
		   {
			// TODO Auto-generated method stub
			if (error != 0 || res == null) {
				Toast.makeText(RouteSearch.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
				handler.sendEmptyMessage(0);
				return;
			}
		    TransitOverlay  routeOverlay = new TransitOverlay(RouteSearch.this,bmapsView);
		 
		    routeOverlay.setData(res.getPlan(0));
		    ol.clear();
		    ol.add(routeOverlay);
		    bmapsView.invalidate();
		    
		    controller.animateTo(res.getStart().pt);
		    handler.sendEmptyMessage(0);
			
		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult res, int error)
		{
			// TODO Auto-generated method stub
			if (error != 0 || res == null) {
				Toast.makeText(RouteSearch.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
				handler.sendEmptyMessage(0);
				return;
			}
			RouteOverlay routeOverlay = new RouteOverlay(RouteSearch.this, bmapsView);
		    // 此处仅展示一个方案作为示例
		    routeOverlay.setData(res.getPlan(0).getRoute(0));
		    ol.clear();
		    ol.add(routeOverlay);
		    bmapsView.invalidate();
		    
		    controller.animateTo(res.getStart().pt);
		    handler.sendEmptyMessage(0);
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
