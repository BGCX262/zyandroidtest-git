package com.nyist.connectUtil;

import com.nyist.activity.R;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class isConnect_Internet {

	public static boolean isConnect(Context context) {
		boolean Flag = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		if (networkInfo != null) {
			Flag = connectivityManager.getActiveNetworkInfo().isAvailable();
		}
		return Flag;
	}

	/***
	 * ����ϵͳ��������Ľ���
	 * 
	 * if(!isConnect_Internet.isConnect(getApplicationContext())){
	 * isConnect_Internet.setNetworkMethod(TabRadioDemoActivity.this); }
	 * */
	public static void setNetworkMethod(final Context context) {
		// ��ʾ�Ի���
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("����������ʾ!")
				.setIcon(R.drawable.not_connect)
				.setMessage("�ס������������Ӳ����ã����������磡")
				.setPositiveButton("����", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent intent = null;
						// �ж��ֻ�ϵͳ�İ汾 ��API����10 ����3.0�����ϰ汾
						if (android.os.Build.VERSION.SDK_INT > 10) {
							intent = new Intent(
									android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						} else {
							intent = new Intent();
							ComponentName component = new ComponentName(
									"com.android.settings",
									"com.android.settings.WirelessSettings");
							intent.setComponent(component);
							intent.setAction("android.intent.action.VIEW");
						}
						context.startActivity(intent);
					}
				})
				.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}
}
