package com.mistdale.test2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class DiagramResultsView extends View{
	
    private Paint paint;
    private Rect rect;
    private float canvasSize;
	
	public DiagramResultsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		paint = new Paint();
		
	}
	
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvasSize = (int)convertDpToPixel(300, getContext());
		
		paint.setStyle(Paint.Style.FILL);

		// закрашиваем холст белым цветом
		paint.setColor(Color.LTGRAY);
		canvas.drawPaint(paint);
		
		// Рисуем желтый круг
		paint.setAntiAlias(true);
		paint.setColor(Color.YELLOW);
		canvas.drawCircle(450, 30, 25, paint);
		
		// Рисуем зеленый прямоугольник
		paint.setColor(Color.GREEN);
		canvas.drawRect(20, 200, 460, 230, paint);
		
		// Рисуем текст
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		paint.setTextSize(30);
		canvas.drawText("Лужайка для котов", 30, 200, paint);
		
		// Текст под углом
		int x = 310;
		int y = 190;
		 
		paint.setColor(Color.GRAY);
		paint.setTextSize(25);
		String str2rotate = "Лучик солнца!";

		// Создаем ограничивающий прямоугольник для наклонного текста
		rect = new Rect();

		// поворачиваем холст по центру текста
		canvas.rotate(-45, x + rect.exactCenterX(), y + rect.exactCenterY());

		// Рисуем текст
		paint.setStyle(Paint.Style.FILL);
		canvas.drawText(str2rotate, x, y, paint);

		// восстанавливаем холст
		canvas.restore();
		

		// Выводим значок из ресурсов
		Resources res = this.getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.ic_launcher2);
		canvas.drawBitmap(bitmap, 415, 655, paint);
	}
	
	public float convertDpToPixel(float dp, Context context) {
		
	        Resources resources = context.getResources();
	        DisplayMetrics metrics = resources.getDisplayMetrics();
	        return dp * (metrics.densityDpi/160f);
	 }

}