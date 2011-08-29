package ul.ceids.ping.pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Palette {

		
		//Posicion de la paleta del jugador
		public int player_pointX=5;
		public int player_pointY=50; //Trabajar con eje Y
		
		
		private int maxHeight;
		private int maxWidth;
		
		private Paint paint;
		
		//Palette size
		public int p_height;
		public int p_width;

		public Palette(){
			
			this.paint = new Paint();

			
		}
		
		public void set(int color, int maxHeight, int maxWidth){
			
			this.paint = new Paint();
			this.paint.setColor(color);
			
			this.maxHeight = maxHeight;
			this.maxWidth = maxWidth;
			
			this.p_height = (int)(maxHeight*0.2);
			this.p_width = (int)(maxWidth*0.01);
			
			
		}
		
		
		public void drawPersonPalette(Canvas canvas){
			
			Rect palette = new Rect();
			
			palette.set(player_pointX, player_pointY,player_pointX+p_width, player_pointY+p_height);
			
			canvas.drawRect(palette, paint);
			
		}
		
		public void updatePlayerPoint(float y){
			
			if(y>1){
				
				if(player_pointY >= maxHeight - p_height){

					return;
				}	
				//Arriba
				player_pointY+=y;
			}
			else if(y<-1){
				
				if(player_pointY <= 1){

					return;
				}		
				//Abajo
				player_pointY+=y;
				
			}
			
		}
		

}
