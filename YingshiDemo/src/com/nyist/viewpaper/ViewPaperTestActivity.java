package com.nyist.viewpaper;

import java.util.ArrayList;
import java.util.HashMap;

import com.nyist.activity.R;


import android.app.ActivityGroup;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;

public class ViewPaperTestActivity extends ActivityGroup implements
OnClickListener {

	private RadioGroup radioGroup;
     private Button go_back;
	private String array[] = { "��ļ��Ա", "��Ա������", "��Ա������" };
	private LinearLayout linearLayout;
	private ArrayList<TextView> textViews;
	private ViewPager viewPager;
	private ArrayList<View> pageViews;
	private HorizontalScrollView horizontalScrollView;

	/***
	 * ��ʼ����ͼ
	 * 
	 * 
	 */
	void InItView() {
		// ����һ���µ�ArrayListʵ������
		pageViews = new ArrayList<View>();
		/**
		 * ������ʼһ���µĻ�����е���֯�� ÿһ����㿪ʼ������һ����һ�޶����ַ�����ʶ���������
		 * **/
		View view1 = getLocalActivityManager().startActivity("RecruitMembers",
				new Intent(this, RecruitMembers.class)).getDecorView();
		View view2 = getLocalActivityManager().startActivity("IntegralGift",
				new Intent(this, IntegralGift.class)).getDecorView();
		View view3 = getLocalActivityManager().startActivity("birthday present",
				new Intent(this, birthdayPresent.class)).getDecorView();
		// ���ָ���Ķ���������ĩβ��ArrayList��
		pageViews.add(view1);
		pageViews.add(view2);
		pageViews.add(view3);
	}

	/***
	 * ��ʼ����������
	 */
	void InItTitle() {
		// ��ȡ���ڹ�������ʾ�Զ��崰��,ȥ��Ĭ����ʾ���󡣵õ�1/3��Ļ���
		int width = getWindowManager().getDefaultDisplay().getWidth() / 3;

		for (int i = 0; i < array.length; i++) {
			// ����һ��radioButton����
			RadioButton radioButton = new RadioButton(this, null);
			radioButton.setText(array[i]);
			radioButton.setWidth(width);
			radioButton.setHeight(70);
			// ��ˮƽ�����ı��ʹ�ֱ������ʱ�򽫻�ʹ���ж���Ŀռ���TextView����Ҫ����ı�����
			radioButton.setGravity(Gravity.CENTER);
			// �������ͼ�����û�в��ֲ����Ժ����Ѿ�����,Ĭ�ϲ����������ViewGroup�����õĺ��ӡ�
			radioGroup.addView(radioButton);
		}
	}

	/***
	 * ��ʼ���ı�
	 */
	void initTextView() {
		// ����һ��ArrayList����
		textViews = new ArrayList<TextView>();
		// ��ȡ���ڹ�������ʾ�Զ��崰��,ȥ��Ĭ����ʾ���󡣵õ�1/3��Ļ���
		int width = getWindowManager().getDefaultDisplay().getWidth() / 3;
		int height = 60;
		for (int i = 0; i < array.length; i++) {
			TextView textView = new TextView(this);
			textView.setText(array[i]);
			textView.setTextSize(17);
			textView.setWidth(width);
			textView.setHeight(45);
			textView.setGravity(Gravity.CENTER);
			textView.setId(i);
		
			// �����ı��ļ����¼�
			textView.setOnClickListener(this);
			textViews.add(textView);
			Bitmap  bitmap = BitmapFactory.decodeResource(getResources(),
					 R.drawable.sc_uncheck);
			 textViews.get(i).setBackgroundDrawable(
					 new BitmapDrawable(bitmap));
			LinearLayout.LayoutParams layoutParams = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layoutParams.width = 1;
			layoutParams.height = height - 40;
			layoutParams.gravity = Gravity.CENTER;
			// �������ͼ�����û�в��ֲ����Ժ����Ѿ�����,Ĭ�ϲ����������ViewGroup�����õĺ��ӡ�
			linearLayout.addView(textView);

		}
	}

	/***
	 * ѡ�к�Ĵ���
	 */
	public void select(int id) {
		for (int i = 0; i < array.length; i++) {
			
			if (id == i) {
//				 �õ�ͼƬ����Դ
				Bitmap   bitmap = BitmapFactory.decodeResource(getResources(),
				 R.drawable.sc);
				 // ���ñ���ͼ
				 textViews.get(id).setBackgroundDrawable(
				 new BitmapDrawable(bitmap));
				viewPager.setCurrentItem(i);
				textViews.get(i).setTextColor(Color.DKGRAY);
			}

			else {
				Bitmap   bitmap1 = BitmapFactory.decodeResource(getResources(),
						 R.drawable.sc_uncheck);
				textViews.get(i).setBackgroundDrawable(new BitmapDrawable(bitmap1));
				textViews.get(i).setTextColor(Color.GRAY);
			}
		}
	}

	public void initview() {
		linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
		viewPager = (ViewPager) findViewById(R.id.viewpaper);
		horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalscrollview);
		go_back=(Button) findViewById(R.id.back);
		go_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		// ���÷���ʵ��
		initTextView();
		select(0);
		InItView();

		viewPager.setAdapter(new PagerAdapter() {
			// �õ���Ŀ
			public int getCount() {
				return pageViews.size();
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

			@Override
			public int getItemPosition(Object object) {
				// TODO Auto-generated method stub
				return super.getItemPosition(object);
			}

			@Override
			public void destroyItem(View view, int id, Object arg2) {
				// TODO Auto-generated method stub
				((ViewPager) view).removeView(pageViews.get(id));
			}

			// ��ȡÿһ��item��id
			@Override
			public Object instantiateItem(View view, int id) {
				((ViewPager) view).addView(pageViews.get(id));
				return pageViews.get(id);
			}

		});
		// ҳ��ı�ʱ��ļ����¼�
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			public void onPageSelected(int arg0) {
				select(arg0);
				// textViews.get(arg0).setTextColor(Color.RED);
			}

			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			public void onPageScrollStateChanged(int arg0) {

			}
		});
		
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȥ��������
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// // ȡ��״̬��������ȫ��
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

setContentView(R.layout.viewpaper);
		// ʵ�������
		initview();
	}

	public void onClick(View v) {
		select(v.getId());
	}

	class ItemClickListener implements OnItemClickListener {
		/**
		 * @param parent
		 *            �������������AdapterView
		 * @param view
		 *            ��AdapterView�б��������ͼ(������adapter�ṩ��һ����ͼ)��
		 * @param position
		 *            ��ͼ��adapter�е�λ�á�
		 * @param rowid
		 *            �����Ԫ�ص���id��
		 */
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long rowid) {
			HashMap<String, Object> item = (HashMap<String, Object>) parent
					.getItemAtPosition(position);
			// ��ȡ����Դ������ֵ
			String Textitem = (String) item.get("Textitem");
			Object object = item.get("Imageitem");
			Toast.makeText(ViewPaperTestActivity.this, Textitem,
					Toast.LENGTH_LONG).show();


		}
	}
}