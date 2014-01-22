package com.zzypop.Button;

import android.R.string;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Overlay;
import com.baidu.mapapi.Projection;

/**
 * 
 * @author 
 *
 */
public class PosOverLay extends Overlay{

	// �����PosOverLay�����Ƶ�λͼ
		Bitmap posBitmap;
		// �����PosOverLay����λͼ��λ��
		GeoPoint gp;
		String name;
		public PosOverLay(GeoPoint gp, Bitmap posBitmap,String name)
		{
			super();
			this.gp = gp;
			this.posBitmap = posBitmap;
			this.name=name;
		}

		@Override
		public void draw(Canvas canvas, MapView mapView
			, boolean shadow)
		{
			if (!shadow)
			{
				// ��ȡMapView��Projection����
				Projection proj = mapView.getProjection();
				Point p = new Point();
				// ����ʵ�ĵ�������ת��Ϊ��Ļ�ϵ�����
				proj.toPixels(gp, p);
				// ��ָ��λ�û���ͼƬ
				canvas.drawBitmap(posBitmap, p.x - posBitmap.getWidth() / 2
					, p.y - posBitmap.getHeight(), null);

			}
		}
	
	
	
	
	
	
}
