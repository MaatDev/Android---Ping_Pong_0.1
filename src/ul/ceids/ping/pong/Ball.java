package ul.ceids.ping.pong;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

public class Ball {
	
	//Posicion de la pelota
		public int ball_pointX=50;
		public int ball_pointY=50;
		
		private int maxHeight;
		private int maxWidth;
		
		public Paint paint;
		
		//Ball radius
		public int radius=5;
		
		//Esta dentro del view?
		private boolean ball_leftRight=true;
		private boolean ball_upDown=true;
		private boolean ball_invert_upDown=true;
		private boolean ball_invert_leftRight=true;
		
		//Velocidad del juego
		private int base_velocity=1;
		public int velocity2=1;
		
		public boolean normal_direction=true;
		
		//Para cambiar a velocidad de la pelota según las colisiones
		private int factor=1;
		
		//El factor para el choque
		private int factor_palette_shock=2;
		
		//Generador de números aleatorios
		private Random random;
	
	
	public Ball(){
		
		this.random = new Random();
		
	}
	
	public void set(int color, int maxHeight, int maxWidth, int radius){
		
		this.paint = new Paint();
		this.paint.setColor(color);
		
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
		
		this.radius = radius;

		
	}
	

	
	public boolean drawBall(Canvas canvas, Palette player, Palette pc, Ball ball, Context context){
		
				
		boolean state=false;
		
		if(validateColision(pc, ball, false, context) ||
			validateColision(player, ball, true, context)){
	
				ball_leftRight = !ball_leftRight;

	
		}


		if(normal_direction){
			state = normalDirection();
		}else{
//			state = invertedDirection();
		}
		
//		if(!state){
////			if(validateColision(pc, ball, false, context) ||
////					validateColision(player, ball, true, context)){
////				
////				ball_leftRight = !ball_leftRight;
////
////				
////			}
//			
//			if(validateColision(pc, ball, false, context)){
//				if(){
//					
//				}
//				ball_leftRight = !ball_leftRight;
//			}
//			
//		}
		
//			Log.v("TAG","Resultado: "+state+"/"+validateColision(pc, ball, false, context));
			
			canvas.drawCircle(ball_pointX,ball_pointY, radius, paint);
		
//			Log.v("TAG", "Valor del factor: "+factor);
			
		return state;
					
	}
	
	private boolean normalDirection(){
		
		if (ball_leftRight) {
			// a la derecha
			ball_pointX += velocity2;
			
			if (ball_pointX>= maxWidth- radius) {
				ball_leftRight = false;		
				velocity2=factor+base_velocity;
				return true;
			}
		} else {
			// a la izquierda
			ball_pointX -= velocity2;
			if (ball_pointX<= radius) {
				ball_leftRight = true;
				velocity2=factor+base_velocity;
				return true;
			}
		}
		if(factor<=0){
			factor=0;
		}

		// The ball moves from up to down
		if (ball_upDown) {
			// hacia arriba
			ball_pointY -= velocity2;
			if ( ball_pointY<= radius) {
				ball_upDown = false;
				velocity2=factor+base_velocity;
				factor--;
			}

		} else {
			// hacia abajo
			ball_pointY += velocity2;
			if (ball_pointY>= maxHeight- radius) {
				ball_upDown = true;
				velocity2=factor+base_velocity;
				factor--;
			}
		}
		
		if(factor<=0){
			factor=0;
		}
		return false;
	}
	
	private void invertedDirection(){
		
		if (ball_invert_leftRight) {
			// a la izquierda
			ball_pointX -= base_velocity;
			
			if (ball_pointX<= radius) {
				ball_invert_leftRight = false;
			}
		} else {
			// a la derecha
			ball_pointX += base_velocity;
			if (ball_pointX>= maxWidth- radius) {
				ball_invert_leftRight = true;
			}
		}

		// The ball moves from up to down
		if (ball_invert_upDown) {
			// hacia abajo
			ball_pointY -= base_velocity;
			if (ball_pointY<=radius) {
				ball_invert_upDown = false;
			}

		} else {
			// hacia arriba
			ball_pointY += base_velocity;
			if (ball_pointY>= maxHeight - radius) {
				ball_invert_upDown = true;
			}
		}
		
	}
	
	public boolean validateColision(Palette palette, Ball ball, boolean left, Context context){
		
		if(palette == null || ball == null){
			return false;
		}
		
		//Player
		if(left){			
			
			if(  (palette.player_pointX+palette.p_width+2 >= ball.ball_pointX-ball.radius &&
					palette.player_pointX <= ball.ball_pointX+ball.radius )    &&
					
						(palette.player_pointY < ball.ball_pointY  && 
							palette.player_pointY+palette.p_height > ball.ball_pointY)
					
					){				
				this.factor = factor_palette_shock;
//				Log.v("TAG","Valor: "+palette.player_pointX+palette.p_width +"/"+(ball.ball_pointX-ball.radius));
				
				return true;
			}
			
			return false;
			
			
		//PC
		}else{
			if( (palette.player_pointX -2 <= ball.ball_pointX+ball.radius  &&
					palette.player_pointX + palette.p_width >= ball.ball_pointX+ball.radius) &&
						( (palette.player_pointY < ball.ball_pointY  && 
								palette.player_pointY+palette.p_height > ball.ball_pointY))
					){
				
				this.factor = factor_palette_shock;
//				Log.v("TAG","Valor: "+palette.player_pointX+palette.p_width +"/"+(ball.ball_pointX-ball.radius));
				
				return true;
				
			}
			
			
			return false;
		}	
		
	}
	
	
	public void setVelocity(int velocity){
		this.base_velocity=velocity;
		this.velocity2=velocity;
	}


	
}
