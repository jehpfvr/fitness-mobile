package br.com.fitnessmobile.view;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;

public class TabGraficoEvolutivo extends Activity implements OnClickListener, OnDateSetListener {

	static final int DIALOG_DATA_INICIO = 0;
	static final int DIALOG_DATA_FINAL = 1;
	
	private Calendar dataInicio;
	private Calendar dataFim;
	private Calendar dataAtual;
	private DatePickerDialog dialogDataInicio;
	private DatePickerDialog dialogDataFim;
	private int dialogSelecionado;
	private Button btn;
	private Button btn2;
	private Button btn3;
	private TextView tvDataInicial;
	private TextView tvDataFinal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.grafico_evolutivo);
		this.instanciarViews();
	}

	private void instanciarViews() {	
        this.btn = (Button) findViewById(R.id.btnAnalisar);		
        this.btn2 = (Button) findViewById(R.id.analise_datainicial);
        this.btn3 = (Button) findViewById(R.id.analise_datafinal);
        this.tvDataInicial = (TextView) findViewById(R.id.tvDataInicial);
		this.tvDataFinal = (TextView) findViewById(R.id.tvDataFinal);    
        
        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

		this.dataInicio = Calendar.getInstance();
		this.dataFim = Calendar.getInstance();
				
		this.dataInicio = Calendar.getInstance();
		this.dataFim = Calendar.getInstance();
		dataAtual = Calendar.getInstance();
		dataAtual.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
	}

    
	public void vibrar(){
		Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		vb.vibrate(300);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
		int diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		if(id == DIALOG_DATA_INICIO){
			this.dialogDataInicio = new DatePickerDialog(this, this, anoAtual,mesAtual, diaAtual);
			return this.dialogDataInicio;
		}else if(id == DIALOG_DATA_FINAL){
			this.dialogDataFim = new DatePickerDialog(this, this, anoAtual,mesAtual, diaAtual);
			return this.dialogDataFim;
		}
		
		return super.onCreateDialog(id);
	}

	public void onDateSet(DatePicker dialog, int year, int month, int day) {
		if(this.dialogSelecionado == DIALOG_DATA_INICIO){
			this.tvDataInicial.setText(String.valueOf(day)+"-"+String.valueOf(dialog.getMonth()+1)+"-"+String.valueOf(year));
			this.dataInicio.set(Calendar.YEAR, year);
			this.dataInicio.set(Calendar.MONTH, month);
			this.dataInicio.set(Calendar.DAY_OF_MONTH, day);
			
		}else if(this.dialogSelecionado == DIALOG_DATA_FINAL){
			this.tvDataFinal.setText(String.valueOf(day)+"-"+String.valueOf(dialog.getMonth()+1)+"-"+String.valueOf(year));
			this.dataFim.set(Calendar.YEAR, year);
			this.dataFim.set(Calendar.MONTH, month);
			this.dataFim.set(Calendar.DAY_OF_MONTH, day);
		}
		
	}

	public void onClick(DialogInterface dialog, int which) {
		Log.i("OnClickDialog","Laço OnClick");
		
		if (which == DialogInterface.BUTTON_POSITIVE){
			Log.i("OnClickDialog","teste" );
			this.finish();
		}
	}
	
	public void onClick(View v) {
		if(v == btn){
			
			Intent it = new Intent(this, CanvasActivity.class).putExtra("DataInicio", tvDataFinal.getText().toString()).putExtra("DataFim", tvDataInicial.getText().toString());
			startActivity(it);
			//gec.geraRelatorio(tvDataInicial.getText().toString(), tvDataFinal.getText().toString());
			
		}else if (v == this.btn2){
			this.dialogSelecionado = DIALOG_DATA_INICIO;
			showDialog(DIALOG_DATA_INICIO);
		}else if (v == this.btn3){
			this.dialogSelecionado = DIALOG_DATA_FINAL;
			showDialog(DIALOG_DATA_FINAL);
		}
		
	}
}

