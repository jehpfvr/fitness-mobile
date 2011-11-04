package br.com.fitnessmobile.view;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.GraficoEvolutivoController;
import br.com.fitnessmobile.controller.Util;


public class TabGraficoEvolutivo extends Activity implements OnClickListener, OnDateSetListener {

	private Button btn;
	private DatePicker dpDataDeInicio;
	private DatePicker dpDataDeFim;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.grafico_evolutivo);
		this.instanciaViews();				
	}

	private void instanciaViews() {
		this.btn = (Button) findViewById(R.id.btnAnalisar);
		
		btn.setOnClickListener(this);
		dpDataDeInicio = (DatePicker) findViewById(R.id.dpDataDeInicio);
		dpDataDeFim = (DatePicker) findViewById(R.id.dpDataDeFim);
	}

	public void onClick(View v) {
		
		if (v == btn) {
			GraficoEvolutivoController gec = new GraficoEvolutivoController(this);
			
			String dataDeInicioStr = Integer.toString(dpDataDeInicio.getDayOfMonth());
			dataDeInicioStr += "-" + Integer.toString(dpDataDeInicio.getMonth());
			dataDeInicioStr += "-" + Integer.toString(dpDataDeInicio.getYear());
			
			Log.v("log", dataDeInicioStr);
			
			String dataDeFimStr = Integer.toString(dpDataDeFim.getDayOfMonth());
			dataDeFimStr += "-" + Integer.toString(dpDataDeFim.getMonth());
			dataDeFimStr += "-" + Integer.toString(dpDataDeFim.getYear());
			
			Log.v("log", dataDeFimStr);
			
			gec.geraRelatorio(dataDeInicioStr, dataDeFimStr);
		}
	}

	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
	}

}
