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
	LocationListener mLocationListener = null;//onResumeʱע���listener��onPauseʱ��ҪRemove
	MyLocationOverlay mLocationOverlay = null;	//��λͼ��
	private MKSearch mMKSearch;
  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.map);
		
		pos=new StringBuffer();
		position_name=(TextView)findViewById(R.id.posiname);
		//��ʼ��BMapManager
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("3BFB2B30CD6198B25A1FF81887DE3677928FE677", null);
		super.initMapActivity(mBMapMan);
		

		// ��ȡ�����ϵ�MapView����
		bmapsView = (MapView) findViewById(R.id.bmapsView);
		//��ȡ����
		intent=getIntent();
		
		lon=Double.parseDouble(intent.getStringExtra("long"));
		lat=Double.parseDouble(intent.getStringExtra("lattude"));
		name=intent.getStringExtra("poname");
		
		popView=super.getLayoutInflater().inflate(R.layout.pop_map_view, null);
        bmapsView.addView( popView,
                new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT,
              null, MapView.LayoutParams.BOTTOM_CENTER));
        popView.setVisibility(View.GONE);
        
        
		// ��ʼ��MKSearch
		mMKSearch = new MKSearch();
		mMKSearch.init(mBMapMan, new MySearchListener());
		
		//��������
		position_name.setText(name);
		
		posBitmap = BitmapFactory
				.decodeResource(getResources(), R.drawable.pos);

		
		mLocationOverlay = new MyLocationOverlay(this, bmapsView);
		
		// ��ȡcontroller����
		controller = bmapsView.getController();

		// ����ϵͳ���õ����Ű�ť
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
						// ��ѯ�þ�γ��ֵ����Ӧ�ĵ�ַλ����Ϣ
						mMKSearch.reverseGeocode(pt);	
						
						ol.add(new OverItemT(null,ObtainPosition.this,pt,pos.toString()));       
					}	        
					}
				}
		    };			
        this.progressDialog=ProgressDialog.show(this,"���Ժ�...","���ڵ�ͼ�϶�λ...." 
     		   ,true,false);
        
        Thread thread=new Thread(this);
        thread.start();
        
	}
	
	/*������� */
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
	  //��ʼ����λ��
	  updateMapView(lon, lat);
	  
	 
	  handler.sendEmptyMessage(0);
   }
  
	private void getInfo()
	{
		 pos.append("��ʱ�޷���ȡ���λ��!");
		 Toast.makeText(getApplicationContext(),
					"�������������Ƿ���ȷ?", Toast.LENGTH_SHORT).show();
	}

	
	/**
	 * �ڲ���ʵ��MKSearchListener�ӿ�,����ʵ���첽��������
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
			       pos.append("�����ڵ�λ��:"+result.strAddr+"����");		        	 
		    }			
		}

        /*
         * �ݳ�·�߲�ѯ
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

	// ���ݾ��ȡ�γ�Ƚ�MapView��λ��ָ���ص�ķ���
	private void updateMapView(double lng, double lat) {
		// ����γ����Ϣ��װ��GeoPoint����
		GeoPoint gp = new GeoPoint((int) (lat * 1E6), (int) (lng * 1E6));
		// ������ʾ�Ŵ���С��ť
		bmapsView.displayZoomControls(true);
		controller.setZoom(20);
		// ���MapView��ԭ�е�Overlay����
		 ol= bmapsView.getOverlays();
		// ���ԭ�е�Overlay����
		ol.clear();
		
		ol.add(mLocationOverlay);
		// ���һ���µ�OverLay����
		ol.add(new PosOverLay(gp, posBitmap,name));
		
		
	}
	

//	 Override���·���,����API:
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
	        mLocationOverlay.disableCompass(); // �ر�ָ����
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (mBMapMan != null) {
			//�������λ��
			mBMapMan.getLocationManager().requestLocationUpdates(mLocationListener);
	        mLocationOverlay.enableMyLocation();
	        mLocationOverlay.enableCompass(); // ��ָ����
			mBMapMan.start();
		}
		super.onResume();
	}
	
}
