package com.zzypop.Button;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
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
import com.baidu.mapapi.PoiOverlay;
import com.nyist.activity.R;

import com.zzypop.Button.NearSearch.MySearchListener;




public class PoiSearch extends MapActivity {
	MapView mMapView = null;	// ��ͼView
	MKSearch mSearch = null;	// ����ģ�飬Ҳ��ȥ����ͼģ�����ʹ��
	BMapManager mBMapMan = null;
	MapController controller;
	Intent intent;
	String strPoiSearch;
	ProgressDialog progDialog = null;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.poi_map);	
		//��ʼ��BMapManager
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("3BFB2B30CD6198B25A1FF81887DE3677928FE677", null);
		super.initMapActivity(mBMapMan);
        init();
        createTh();

	}
    private void init()
	{
		    intent=getIntent();
		    strPoiSearch=intent.getStringExtra("strPoiSearch");		    
	        mMapView = (MapView)findViewById(R.id.mapsView);
	        progDialog=new ProgressDialog(this);
			// ��ȡcontroller����
			controller = mMapView.getController();
			// ����ϵͳ���õ����Ű�ť
			mMapView.setBuiltInZoomControls(true);
			// ���MapView��ԭ�е�Overlay����
	        mSearch = new MKSearch();
	        mSearch.init(mBMapMan, new MySearchListener());	       
	}
	private void createTh()
	{
		Thread t = new Thread(new Runnable() 
		{
	   public void run()
		{	
			mSearch.poiSearchInCity("����", 
					strPoiSearch);
	    }
		});	
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setIndeterminate(false);
		progDialog.setCancelable(true);
		progDialog.setMessage("���ڻ�ȡ�����Ϣ ");
		progDialog.show();
		t.start();	
	}
	/*������� */
 private Handler handler=new Handler(){
	 
	 public void handleMessage(android.os.Message msg) {
		 super.handleMessage(msg);		 
		 progDialog.dismiss();
	 }; 
	 
 };
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
		public void onGetPoiResult(MKPoiResult res, int type, int error)
		{
			// TODO Auto-generated method stub
			// ����ſɲο�MKEvent�еĶ���
			if (error != 0 || res == null) {
				handler.sendEmptyMessage(0);
				Toast.makeText(PoiSearch.this, "��Ǹ��δ�ҵ����", Toast.LENGTH_LONG).show();
				return;
			}
		    // ����ͼ�ƶ�����һ��POI���ĵ�
		    if (res.getCurrentNumPois() > 0) {
			    // ��poi�����ʾ����ͼ��
				PoiOverlay poiOverlay = new PoiOverlay(PoiSearch.this, mMapView);
				poiOverlay.setData(res.getAllPoi());
			    mMapView.getOverlays().clear();
			    mMapView.getOverlays().add(poiOverlay);
			    mMapView.invalidate();
			    controller.setZoom(15);
		    	mMapView.getController().animateTo(res.getPoi(0).pt);
		    	handler.sendEmptyMessage(0);
		    } else if (res.getCityListNum() > 0) {
		    	String strInfo = "��";
		    	for (int i = 0; i < res.getCityListNum(); i++) {
		    		strInfo += res.getCityListInfo(i).city;
		    		strInfo += ",";
		    	}
		    	strInfo += "�ҵ����";
		    	handler.sendEmptyMessage(0);
				Toast.makeText(PoiSearch.this, strInfo, Toast.LENGTH_LONG).show();
				
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
	protected void onPause() {
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}
	@Override
	protected void onResume() {
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
