package br.com.fitnessmobile.view;


import br.com.fitnessmobile.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FitnessMobileMain extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button bt = (Button) findViewById(R.id.btn_main_medidas);
		bt.setOnClickListener(this);
	
	}

	public void onClick(View v) {
		
		startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 1));
		
	}

}
