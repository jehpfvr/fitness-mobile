package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;

public class FitnessMobileMain extends Activity implements OnClickListener {
	
	private Button btn_medidas;
	private Button btn_programa;
	private Button btn_exercicio;
	private Button btn_configuracao;
	private Button btn_estatistica;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.main);
		
		this.btn_programa = (Button) findViewById(R.id.btn_main_programa);
		this.btn_programa.setOnClickListener(this);
		
		this.btn_medidas= (Button) findViewById(R.id.btn_main_medidas);
		this.btn_medidas.setOnClickListener(this);
		
		this.btn_exercicio = (Button) findViewById(R.id.btn_main_exercicio);
		this.btn_exercicio.setOnClickListener(this);
		
		this.btn_configuracao = (Button) findViewById(R.id.btn_main_conf);
		this.btn_configuracao.setOnClickListener(this);
		
		this.btn_estatistica = (Button)findViewById(R.id.btn_main_estatistica);
		this.btn_estatistica.setOnClickListener(this);
	}

	public void onClick(View v) {
		if(v == btn_programa){
			startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 1));
		}else if(v == btn_exercicio){
			startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 2));
		}else if(v == btn_medidas){
			startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 3));
		}else if(v == btn_configuracao){
			startActivity(new Intent(this, ModoVisualizacao.class));
		}else if(v == btn_estatistica){
			startActivity(new Intent(this, TabEstatistica.class));
		}
	}
	
}