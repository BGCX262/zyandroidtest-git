package com.nyist.start;







import com.nyist.activity.R;
import com.nyist.tool.CustomTabHost;



import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class StartInfoActivity extends TabActivity implements OnCheckedChangeListener,OnGestureListener {

	private RadioGroup mainTab;
	private TabHost mTabHost;
	private TabWidget tabWidget;
	/** ��¼��ǰ��ҳID */
	private int currentTabID = 0;
	private GestureDetector gestureDetector;
	private FrameLayout frameLayout;
	private static final int FLEEP_DISTANCE = 120;
	private final static String TAB_TAG_HOME="tab_Home";
	private final static String TAB_TAG_MSG="tab_Msg";
	private final static String TAB_TAG_Te="tab_te";
	private RadioButton radioButton0,radioButton1,radioButton2;
    private Intent mFirstIntent,mSecondIntent,mThirdIntent;
    private Button ret_inf;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.start_info);
        radioButton0=(RadioButton)findViewById(R.id.radio_button0);
        radioButton1=(RadioButton)findViewById(R.id.radio_button1);
        radioButton2=(RadioButton)findViewById(R.id.radio_button2);
    	radioButton0.setBackgroundResource(R.drawable.abs__cab_background_top_holo_light);
        mainTab=(RadioGroup)findViewById(R.id.main_tab);
        ret_inf=(Button)findViewById(R.id.return_inf);
        mainTab.setOnCheckedChangeListener(this);
        prepareIntent();
        setupIntent();	
        //ʵ����
        gestureDetector = new GestureDetector(this);
		new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		};
		ret_inf.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				StartInfoActivity.this.finish();
			}
		});
}  
    /* ׼����ת��ҳ���Intent  */
	private void prepareIntent() {		
		mFirstIntent=new Intent(this, StartFaceActivity .class);
		mSecondIntent=new Intent(this, FilmCirActivity.class);
        mThirdIntent=new Intent(this,FilmBriActivity.class);
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		// TODO Auto-generated method stub
		switch( checkedId){
		case R.id.radio_button0:
			radioButton0.setBackgroundResource(R.drawable.abs__cab_background_top_holo_light);
	        radioButton1.setBackgroundResource(R.drawable.film_btn_bg);
	        radioButton2.setBackgroundResource(R.drawable.film_btn_bg);
			this.mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
			break;
		case R.id.radio_button1:
			radioButton1.setBackgroundResource(R.drawable.abs__cab_background_top_holo_light);
	        radioButton0.setBackgroundResource(R.drawable.film_btn_bg);
	        radioButton2.setBackgroundResource(R.drawable.film_btn_bg);
			this.mTabHost.setCurrentTabByTag(TAB_TAG_MSG);
			break;
		case R.id.radio_button2:
			radioButton2.setBackgroundResource(R.drawable.abs__cab_background_top_holo_light);
	        radioButton1.setBackgroundResource(R.drawable.film_btn_bg);
	        radioButton0.setBackgroundResource(R.drawable.film_btn_bg);
			this.mTabHost.setCurrentTabByTag(TAB_TAG_Te);
		}
	}
	/* ΪTabHost��ӱ�ǩ  */
	
	private void setupIntent() {
		this.mTabHost=(CustomTabHost) findViewById(android.R.id.tabhost);
		TabHost localTabHost=this.mTabHost;
		localTabHost.addTab(buildTabSpec(TAB_TAG_HOME,R.string.privi_gro, mFirstIntent));
		localTabHost.addTab(buildTabSpec(TAB_TAG_MSG,R.string.privi_card, mSecondIntent));
		localTabHost.addTab(buildTabSpec(TAB_TAG_Te,R.string.privi_stu, mThirdIntent));
	}
	/*  �Ѿ���װ��һ������ ���ڽ���Tab */
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, final Intent content) {
		return this.mTabHost.newTabSpec(tag).setIndicator(getString(resLabel))
				.setContent(content);
	}
	@Override
	public boolean onDown(MotionEvent arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}
	public boolean dispatchTouchEvent(MotionEvent event)
	{
		// TODO Auto-generated method stub
		if (gestureDetector.onTouchEvent(event)) {
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);	
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY)
	{
		// TODO Auto-generated method stub
				if (e1.getX() - e2.getX() <= (-FLEEP_DISTANCE)) {//�������һ���
					currentTabID = mTabHost.getCurrentTab() - 1;
					
					if (currentTabID < 0) {
						currentTabID = ((CustomTabHost) mTabHost).getTabCount()-1;
					}
				} else if (e1.getX() - e2.getX() >= FLEEP_DISTANCE) {//�������󻬶�
					currentTabID = mTabHost.getCurrentTab() + 1;
					if (currentTabID >= ((CustomTabHost) mTabHost).getTabCount()) {
						currentTabID = 0;
					}
				}
				setButton(currentTabID);
				return false;
	}
	//Ϊ��ʵ�ֻ����Ķ�̬Ч�� ֻ��������
	//�����෽���Բ������ַ������ȡ
	private void setButton(int id)
	  { 
		    boolean result=true;
			switch(id){
			case 0:
	            //Ϊʵ���ƶ���̬Ч�������õ�Ч�� ���¾�����ô���õ�
			    radioButton0.setBackgroundResource(R.drawable.abs__cab_background_top_holo_light);
		        radioButton1.setBackgroundResource(R.color.Gainsboro);
		        radioButton2.setBackgroundResource(R.color.Gainsboro);
				this.mTabHost.setCurrentTabByTag(TAB_TAG_HOME);
				break;
			case 1:
			    radioButton1.setBackgroundResource(R.drawable.abs__cab_background_top_holo_light);
		        radioButton0.setBackgroundResource(R.color.Gainsboro);
		        radioButton2.setBackgroundResource(R.color.Gainsboro);
				this.mTabHost.setCurrentTabByTag(TAB_TAG_MSG);
				break;
			case 2:
			    radioButton2.setBackgroundResource(R.drawable.abs__cab_background_top_holo_light);
		        radioButton1.setBackgroundResource(R.color.Gainsboro);
		        radioButton0.setBackgroundResource(R.color.Gainsboro);
				this.mTabHost.setCurrentTabByTag(TAB_TAG_Te);
				break;		
			}
	  }
	@Override
	public void onLongPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY)
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onShowPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
