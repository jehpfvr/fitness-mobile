package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import br.com.fitnessmobile.R;

public class SplashScreen extends Activity implements Runnable {
	/** Called when the activity is first created. */

	private ProgressBar progressbar;
	private Thread t;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		progressbar = (ProgressBar)findViewById(R.id.progressBar);
		t = new Thread(SplashScreen.this);
		t.start();
		
	}

	private void chamaMain(){
		startActivity(new Intent(this, FitnessMobileMain.class));
		finish();
	}

	public void run(){
		int i = 1;
		try {
			while (i<=100){
				Thread.sleep(30);
				progressbar.setProgress(i++);
				if(i >= 100){
					chamaMain();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}