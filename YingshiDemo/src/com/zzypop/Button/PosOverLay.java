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

	// 定义该PosOverLay所绘制的位图
		Bitmap posBitmap;
		// 定义该PosOverLay绘制位图的位置
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
				// 获取MapView的Projection对象
				Projection proj = mapView.getProjection();
				Point p = new Point();
				// 将真实的地理坐标转化为屏幕上的坐标
				proj.toPixels(gp, p);
				// 在指定位置绘制图片
				canvas.drawBitmap(posBitmap, p.x - posBitmap.getWidth() / 2
					, p.y - posBitmap.getHeight(), null);

			}
		}
	
	
	
	
	
	
}
