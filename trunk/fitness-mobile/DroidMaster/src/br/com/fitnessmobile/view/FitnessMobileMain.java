package br.com.fitnessmobile.view;


import br.com.fitnessmobile.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FitnessMobileMain extends Activity implements OnClickListener{
	
	private Button btn_medidas;
	private Button btn_programa;
	private Button btn_exercicio;
	private Button btn_teste;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		this.btn_programa = (Button) findViewById(R.id.btn_main_programa);
		btn_programa.setOnClickListener(this);
		
		this.btn_medidas= (Button) findViewById(R.id.btn_main_medidas);
		this.btn_medidas.setOnClickListener(this);
		
		this.btn_exercicio = (Button) findViewById(R.id.btn_main_exercicio);
		this.btn_exercicio.setOnClickListener(this);
		
		this.btn_teste = (Button) findViewById(R.id.btn_main_teste);
		this.btn_teste.setOnClickListener(this);
	
	}

	public void onClick(View v) {
		
		if(v == btn_teste){
			startActivity(new Intent(this, ExercicioAerobicoView.class));
		}else if(v == btn_programa){
			startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 1));
		}else if(v == btn_medidas){
			startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 2));
		}else if(v == btn_exercicio){
			startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 3));
		}
		
	}

}
