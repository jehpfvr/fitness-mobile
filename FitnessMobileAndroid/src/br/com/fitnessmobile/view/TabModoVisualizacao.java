package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class TabModoVisualizacao extends Activity implements OnClickListener {

	AlertDialog.Builder dlgAlert;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Util.inicioActivitySetTema(this);
		
		dlgAlert = new AlertDialog.Builder(this);
		setContentView(R.layout.selecionar_modo_de_visualizacao);
		
		findViewById(R.id.btnModoVisualizacaoDiurno).setOnClickListener(this);
		findViewById(R.id.btnModoVisualizacaoNoturno).setOnClickListener(this);

	}
		
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.btnModoVisualizacaoDiurno:
			dlgAlert.setMessage("Tema foi mudado com sucesso");
			dlgAlert.setTitle("Tema Alterado");
			dlgAlert.create().show();
			Util.mudarParaTema(this, Util.TEMA_DIA);
			break;
		case R.id.btnModoVisualizacaoNoturno:
			Util.mudarParaTema(this, Util.TEMA_NOITE);
			dlgAlert.setMessage("Tema foi mudado com sucesso");
			dlgAlert.setTitle("Tema Alterado");
			dlgAlert.create().show();
			break;
		}
	}
	
}