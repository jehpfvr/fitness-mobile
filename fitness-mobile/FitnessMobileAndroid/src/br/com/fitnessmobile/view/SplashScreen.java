package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity implements Runnable {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Handler h = new Handler();
		h.postDelayed(this, 5000);//aqui é definido o delay do handler em milisegundos
		
	}
	
	public void run(){
		startActivity(new Intent(this, FitnessMobileMain.class));
		finish();
	}
    
}