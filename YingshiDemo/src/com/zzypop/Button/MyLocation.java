package com.zzypop.Button;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MapActivity;
import com.nyist.activity.R;


import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

public class MyLocation extends Activity {
	
	LocationListener mLocationListener = null;//create时注册此listener，Destroy时需要Remove
	BMapManager mBMapMan = null;
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mylocation);
        
		//初始化BMapManager
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("3BFB2B30CD6198B25A1FF81887DE3677928FE677", null);
		mBMapMan.start();
		

        // 注册定位事件
        mLocationListener = new LocationListener(){

			@Override
			public void onLocationChanged(Location location) {
				if(location != null){
					String strLog = String.format("您当前的位置:\r\n" +
							"纬度:%f\r\n" +
							"经度:%f",
							location.getLongitude(), location.getLatitude());
                  
					TextView mainText = (TextView)findViewById(R.id.textview);
			        mainText.setText(strLog);
				}
			}
        };
	}

	@Override
	protected void onPause() {

	   mBMapMan.getLocationManager().removeUpdates(mLocationListener);
       mBMapMan.stop();
		super.onPause();
	}
	@Override
	protected void onResume() {
        mBMapMan.getLocationManager().requestLocationUpdates(mLocationListener);
		mBMapMan.start();
		super.onResume();
	}


}
