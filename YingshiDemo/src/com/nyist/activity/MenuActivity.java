package com.nyist.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.nyist.connectUtil.ProcessDialogshow;
import com.nyist.liuyan.LiuyanActivity;
import com.nyist.privilege.PrivilegeInfoActivity;
import com.nyist.sale.Sale;
import com.nyist.start.StartInfoActivity;
import com.nyist.test.FilmInfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MenuActivity extends Activity { 
	
	Button refresh;
	int[] images = { R.drawable.a1,  
        R.drawable.a2, R.drawable.a3,  
        R.drawable.a4, R.drawable.a5,  R.drawable.a6,
        R.drawable.a7, R.drawable.a8,  
        R.drawable.a9, R.drawable.a10,  
        R.drawable.a11, R.drawable.a12
        };  
String[] array = { "ӰѶ", "�Ż�ר��", "��Ʒ", "���Ż�",
		"��ҵ��Ϣ", "�ǹ����", "��Ա", "��Ƹ��Ϣ",
		"һ������", "���ŷ���", "�ҵ��ղ�", "�û�����"
		              	 };  

protected void onCreate(Bundle savedInstanceState) {  
    // TODO Auto-generated method stub   
    super.onCreate(savedInstanceState);  
    
    setContentView(R.layout.menu_activity);  
    
    GridView gridView = (GridView) findViewById(R.id.gridview01);  
   refresh=(Button) findViewById(R.id.refresh);
   refresh.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ProcessDialogshow.processshow(MenuActivity.this);
		
	}
});
    ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
    for (int i = 0; i < array.length; i++) {  
        HashMap<String, Object> map = new HashMap<String, Object>();  
        map.put("Imageitem", images[i]);  
        map.put("Textitem", array[i]);  
        list.add(map);  
    }  

    SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,// ����Դ   
            R.layout.gridview_item,// ��ʾ����   
            new String[] { "Imageitem", "Textitem" }, new int[] {  
                    R.id.grid_item_image, R.id.grid_item_text });  
    gridView.setAdapter(simpleAdapter);  
    gridView.setOnItemClickListener(new ItemClickListener());  
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
//        itemtext.setText((String)item.get("Textitem"));
        Toast.makeText(MenuActivity.this, Textitem,  
                Toast.LENGTH_LONG).show();  

        // ����ͼƬ������Ӧ����ת   
        switch (images[position]) {  
        case R.drawable.a1:  
            startActivity(new Intent(MenuActivity.this,  
            		FilmInfoActivity.class));// ������һ��Activity   
            // finish();// ������Activity���ɻ���   
            break;  
        case R.drawable.a2:  
            startActivity(new Intent(MenuActivity.this,  
            		PrivilegeInfoActivity.class));  
            // finish();   
            break;  
        case R.drawable.a3:  
            startActivity(new Intent(MenuActivity.this, Sale.class));  
            // finish();   
            break;  
//        case R.drawable.a4:  
//            startActivity(new Intent(SearchActivity.this,  
//                    MemorialDay.class));// ������һ��Activity   
//            // finish();// ������Activity���ɻ���   
//            break;  
        case R.drawable.a5:  
            startActivity(new Intent(MenuActivity.this,CompanyInfo.class));  
            // finish();   
            break;  
        case R.drawable.a6:  
            startActivity(new Intent(MenuActivity.this, StartInfoActivity.class));  
            // finish();   
            break;  
        case R.drawable.a7:  
            startActivity(new Intent(MenuActivity.this,  
            		ViewPaperTestActivity.class));// ������һ��Activity   
            // finish();// ������Activity���ɻ���   
            break;  
        case R.drawable.a8:  
        	  startActivity(new Intent(MenuActivity.this,Zhaopin.class));  
               break;  
          
         
        case R.drawable.a9:  
        	
        	Intent intent = new Intent();
			intent.setAction("android.intent.action.DIAL");
		    intent.setData(Uri.parse("tel:0377-63253518"));
             startActivity(intent);
            // finish();   
            break;  
        case R.drawable.a10:  
            Intent smsIntent=new Intent(Intent.ACTION_VIEW);
            smsIntent.setData(Uri.parse("sms:"));
     		smsIntent.putExtra("sms_body","��������ĳĳ�ͻ��ˣ��ǳ�������ӭ���أ�www.nyist.net");
     		startActivity(smsIntent);
         break;  
//        case R.drawable.a11:  
//            startActivity(new Intent(SearchActivity.this, Shift.class));  
//            // finish();   
//            break;  
        case R.drawable.a12:  
      	  startActivity(new Intent(MenuActivity.this,LiuyanActivity.class));  
//      
            break;  
//
        }  

    }  
}  }
