package com.example.eysencktest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Toast;
//import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
public class ResultActivity extends Activity {

	AlertDialog.Builder ad;
	Context context;
	float myX=0;
	float myY=0; 
	public void onCreate(Bundle savedInstanceState)
	{	
		Intent intent = getIntent(); 
		myX = intent.getIntExtra("extrointro", 0); 
		myY = intent.getIntExtra("nero", 0);
		super.onCreate(savedInstanceState);
		GraphicsView myview=new GraphicsView(this); // создаем объект myview класса GraphicsView

		setContentView(myview); // отображаем его в Activity
	}
	@SuppressLint("DrawAllocation")
	public class GraphicsView extends View
	{
		public float bigXR;// = width-tShift+30;
		float bigYR;// = height-tShift-20; 
		float bigYL;// =  tShift-5;
		float bigXL;// = tShift+25;
		float medX;// = bigXL+(bigXR-bigXL)/2;
		float medY;// = bigYL+(bigYR-bigYL)/2;
		float tAxeShift;// =15; 
		public GraphicsView(Context context) { super(context); }

		float touchX = 0;
		float touchY = 0;

		
		static final byte introvert = 0; 
		static final byte extrovert = 1;
		static final byte stable = 2;
		static final byte unstable = 3; 
		static final byte Sanguine = 4; 
		static final byte Choleric = 5;
		static final byte Phlegmatic = 6;
		static final byte Melancholic = 7;

		@Override
		protected void onDraw(Canvas canvas)
		{
			Display display = getWindowManager().getDefaultDisplay();
			DisplayMetrics metricsB = new DisplayMetrics();
			display.getMetrics(metricsB);
			float width = metricsB.widthPixels;
			float height = metricsB.heightPixels;			

			float tShift=100;
			Paint paintLineVert = new Paint(); 
			Paint paintLineHor = new Paint(); 

			Paint paintRect = new Paint(); 
			//Paint paintRect1 = new Paint();
			Paint paint = new Paint(); 
			Paint paintText = new Paint(); 			
			bigXR = width-tShift+30;
			bigYR = height-tShift-20; 
			bigYL =  tShift-5;
			bigXL = tShift+25;
			medX = bigXL+(bigXR-bigXL)/2;
			medY = bigYL+(bigYR-bigYL)/2;
			tAxeShift =15; 

			paintRect.setColor(Color.GRAY);
			canvas.drawRect(bigXL, bigYL, bigXR, bigYR, paintRect);
			canvas.drawLine(medX, bigYL, medX, bigYR, paintLineVert);				
			canvas.drawLine(bigXL, medY, bigXR, medY, paintLineHor);

			//paintText.
			paintText.setColor(Color.BLACK);
			paintText.setTextSize(35.0f);
			

			Paint paintTextAxe = new Paint(); 
			//paintTextAxe.setColor(color.);
			paintTextAxe.setTextSize(17f);	
			canvas.drawText("0", bigXL-tAxeShift, bigYR+tAxeShift, paintTextAxe );
			canvas.drawText("12", medX, bigYR+tAxeShift, paintTextAxe );
			canvas.drawText("24", bigXR, bigYR+tAxeShift, paintTextAxe );
			canvas.drawText("12", bigXL-tAxeShift, medY, paintTextAxe );
			canvas.drawText("24", bigXL-tAxeShift, bigYL,  paintTextAxe );

			canvas.drawText(getResources().getStringArray(R.array.nameType)[2], 10, bigYR,  paintTextAxe );
			canvas.drawText(getResources().getStringArray(R.array.nameType)[3], 10, bigYL+20,  paintTextAxe );
			canvas.drawText(getResources().getStringArray(R.array.nameType)[0], bigXL+10, bigYR+tAxeShift,  paintTextAxe );
			canvas.drawText(getResources().getStringArray(R.array.nameType)[1], bigXR-100, bigYR+tAxeShift,  paintTextAxe );

			Paint paintTextMain = new Paint(); 

			float medMainVX = bigXL+20;
			float medMainVY = bigYL+(medY-bigYL)/2;
			float medMainNY = medY +(medY-bigYL)/2;
			float medMainNX = 20+medX; 

			paintTextMain.setTextSize(22f);

			canvas.drawText(getResources().getStringArray(R.array.nameType)[7], medMainVX-10, medMainVY,  paintTextMain);
			canvas.drawText(getResources().getStringArray(R.array.nameType)[6], medMainVX, medMainNY,  paintTextMain);
			canvas.drawText(getResources().getStringArray(R.array.nameType)[5], medMainNX, medMainVY,  paintTextMain);
			canvas.drawText(getResources().getStringArray(R.array.nameType)[4], medMainNX, medMainNY,  paintTextMain);

			//canvas.drawText("нестабильный", 10, bigYL,  paintTextAxe );
			float x =  (bigXL+myX*(bigXR-bigXL)/24); 
			float y =  (bigYR-myY*(bigYR-bigYL)/24);
			
			paint.setColor(Color.RED);
			canvas.drawText("Ваш результат", width/2-120, tShift-30, paintText);
			canvas.drawCircle(x, y, 10, paint);//(oval, startAngle, sweepAngle, useCenter, paint)
			//canvas.
		}
		public boolean onTouchEvent(MotionEvent event)
		{
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{	
				touchX = event.getX();
				touchY = event.getY();

				if( touchX>bigXL && touchX<medX && touchY > bigYR ) {
					dialogAlert(introvert); 			
				}				
				if( touchX>medX && touchX<bigXR && touchY > bigYR ) {
					dialogAlert(extrovert);					
				}
				if( touchX<bigXL && touchY < bigYR && touchY > medY ) {
					dialogAlert(stable);					
				}
				if( touchX<bigXL && touchY > bigYL && touchY < medY ) {
					dialogAlert(unstable);					
				}
				if( touchX<medX && touchX > bigXL &&  touchY > bigYL && touchY < medY ) {
					dialogAlert(Melancholic);
				}
				if( touchX<medX && touchX > bigXL &&  touchY < bigYR && touchY > medY ) {
					dialogAlert(Phlegmatic);
				}
				if( touchX >medX && touchX < bigXR &&  touchY < bigYR && touchY > medY ) {
					dialogAlert(Choleric);
				}
				if( touchX >medX && touchX < bigXR &&  touchY > bigYL && touchY < medY ) {
					dialogAlert(Sanguine);
				}
				//invalidate();
			}
			return true;
		}
	}
	public void dialogAlert(int i){
		context = ResultActivity.this;
		ad = new AlertDialog.Builder(context);

		String[] ansMan = getResources().getStringArray(R.array.manualType); 
		String[] ansName = getResources().getStringArray(R.array.nameType);
		ad.setMessage(ansMan[i]);
		ad.setTitle(ansName[i]);
		ad.setPositiveButton("Ok", new OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) {
				Toast.makeText(context, "Вы можете выбрать любое слово",
						Toast.LENGTH_LONG).show();
			}
		});
		ad.show();
		//finish();
	}
}