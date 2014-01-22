package com.nyist.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class LogoActivity extends Activity {
	AlphaAnimation startAnimation;  
	  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        // TODO Auto-generated method stub   
        super.onCreate(savedInstanceState);  
//      全屏设置   
//        requestWindowFeature(Window.FEATURE_NO_TITLE);  
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
  
        setContentView(R.layout.logo);  
         ImageView logo = (ImageView) findViewById(R.id.logo);  
//      设置启动的时候的透明度、持续的时间等   
        startAnimation = new AlphaAnimation(0.5f, 1.0f);  
        startAnimation.setDuration(3000);  
        logo.startAnimation(startAnimation);  
        startAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				 Intent intent = new Intent(LogoActivity.this,  
	                        TabRadioDemoActivity.class);  
	                startActivity(intent);  
	                LogoActivity.this.finish();  

			}
		});
  

    }
}
