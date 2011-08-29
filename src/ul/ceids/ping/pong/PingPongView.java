package ul.ceids.ping.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class PingPongView extends View{
	
	//Radio del círculo
	private int radius=10;

	private int maxHeight;
	private int maxWidth;
	
	private Paint paint;
	
	private Ball ball;
	private Palette player;
	private Palette pc;

	Point point;
	
	private SensorManager sManager;	
	
	private Context context;
	
	private boolean gameState=false;
	
	public PingPongView(Context context) {
		super(context);
		
		this.context = context;

		this.paint = new Paint();
		this.paint.setColor(Color.WHITE);
					
		this.sManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);		
		this.registerSensor();
		
		this.ball = new Ball();
		this.player = new Palette();
		this.pc = new Palette();
		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
//		drawPersonPalette(canvas);				
		this.player.drawPersonPalette(canvas);
		this.pc.drawPersonPalette(canvas);
//		this.gameState = !this.ball.drawBall(canvas,player,pc,ball,context);
		if(!this.ball.drawBall(canvas,player,pc,ball,context)){
			invalidate();
		}
		
	}
	
	public boolean drawGame(){
		
		invalidate();
		
		return this.gameState;
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		this.maxHeight = h;
		this.maxWidth = w;

		this.radius = (int)(w*0.03);
				
		this.player.set(Color.WHITE, maxHeight, maxWidth);
		this.pc.set(Color.WHITE, maxHeight, maxWidth);
		this.pc.player_pointX=maxWidth-this.pc.p_width-5;
		this.ball.set(Color.WHITE, maxHeight, maxWidth, this.radius);

				
	}
	
	private SensorEventListener listener = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			
			if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
				
				float y = event.values[SensorManager.DATA_X];
				
//					validateColision(player, ball, true);
				
				player.updatePlayerPoint(y);
				pc.updatePlayerPoint(y);
				
			}
			
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	public void drawPCPalette(){
		
		
	}
	
	public void registerSensor(){
		this.sManager.registerListener(listener, 
				this.sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
					SensorManager.SENSOR_DELAY_FASTEST);
	}
	
	public void unregisterSensor(){
		
		this.sManager.unregisterListener(listener);
		
	}
	
//	public void validateColision(Palette palette, Ball ball, boolean left){
//		//Player
//		if(left){			
//			Log.v("TAG","Estoy en invalidate");
////			if(palette.player_pointX+palette.p_width+2 >= ball.ball_pointX-ball.radius  &&
////						(palette.player_pointY > ball.ball_pointY  &&  
////								palette.player_pointY+palette.p_height < ball.ball_pointY  )){
//				
//			if(palette.player_pointX+palette.p_width+2 >= ball.ball_pointX-ball.radius  &&
//					(palette.player_pointY > ball.ball_pointY  &&  
//							palette.player_pointY+palette.p_height < ball.ball_pointY  )){
//			
//				Toast.makeText(context, "choque", 1000).show();
//				
//			}
//			
//		}
//	
//		
//	}

}
