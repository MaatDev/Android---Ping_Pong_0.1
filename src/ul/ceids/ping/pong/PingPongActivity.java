package ul.ceids.ping.pong;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class PingPongActivity extends Activity {
    /** Called when the activity is first created. */
	
	private PingPongView ppv;
	private LinearLayout ll;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               
        setContentView(R.layout.main);
        
        this.ppv = new PingPongView(this);
        this.ll = (LinearLayout) findViewById(R.id.ll);
        this.ll.addView(this.ppv);
//        setContentView(this.ppv);
        
        Log.v("TAGGG","Termino");
        
    }
    
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	Log.v("--","Comenzar activity");
    	

//    	while(this.ppv.drawGame()){
//    		
//    	}

//    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
//    	builder.setMessage("Are you sure you want to exit?")
//    	       .setCancelable(false)
//    	       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//    	           public void onClick(DialogInterface dialog, int id) {
//    	                MyActivity.this.finish();
//    	           }
//    	       })
//    	       .setNegativeButton("No", new DialogInterface.OnClickListener() {
//    	           public void onClick(DialogInterface dialog, int id) {
//    	                dialog.cancel();
//    	           }
//    	       });
//    	AlertDialog alert = builder.create();
    	
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	ppv.unregisterSensor();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	ppv.registerSensor();
    }
    
}