package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class TabModoVisualizacao extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.selecionar_modo_de_visualizacao);
		
		findViewById(R.id.btnModoVisualizacaoDiurno).setOnClickListener(this);
		findViewById(R.id.btnModoVisualizacaoNoturno).setOnClickListener(this);

	}
		
	public void onClick(View v) {
		
		switch (v.getId())
		{
		case R.id.btnModoVisualizacaoDiurno:
			Util.mudarParaTema(this, Util.TEMA_DIA);
			break;
		case R.id.btnModoVisualizacaoNoturno:
			Util.mudarParaTema(this, Util.TEMA_NOITE);
			break;
		}
	}
	
}