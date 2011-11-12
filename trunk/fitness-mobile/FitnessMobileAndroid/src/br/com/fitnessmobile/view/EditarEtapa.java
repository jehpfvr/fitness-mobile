package br.com.fitnessmobile.view;


import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;
import br.com.fitnessmobile.dao.EtapaDao;
import br.com.fitnessmobile.model.Etapa;

public class EditarEtapa extends Activity implements OnClickListener,OnDateSetListener, android.content.DialogInterface.OnClickListener{
	
	
	static final int DIALOG_DATA_INICIO = 0;
	static final int DIALOG_DATA_FINAL = 1;

	private EditText editview;
	private Button btn_salvar;
	private EtapaDao etapaDao;
	AlertDialog.Builder dlgAlert;
	private Etapa etapa;
	private Integer programaID;
	private String programaNome;
	private long programaDtInicio;
	private long programaDtFim;
	private int dialogSelecionado;
	private TextView tvDataInicial;
	private TextView tvDataFinal;
	private Button btDatainicial;
	private Button btDataFinal;
	private Calendar dataInicio;
	private Calendar dataFim;
	private DatePickerDialog dialogDataInicio;
	private DatePickerDialog dialogDataFim;
	Intent intent;
	private Calendar dataEtapaInicio;
	private Calendar dataEtapaFim;
	private Calendar validarDtFim;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.add_etapa);
		programaID = getIntent().getIntExtra("programaID", -1);
		programaNome = getIntent().getStringExtra("programaNome");
		programaDtInicio = getIntent().getLongExtra("programaDtInicio",-1);
		programaDtFim = getIntent().getLongExtra("programaDtFim",-1);
		this.instanciarViews();
	}
	
	private void instanciarViews() {
		etapaDao = new EtapaDao(this);
		
		etapa = etapaDao.buscarEtapa(programaID); 
		
		editview = (EditText) findViewById(R.id.add_etapa_nome);
		btn_salvar = (Button) findViewById(R.id.add_etapa_salvar);
		btDatainicial = (Button) findViewById(R.id.add_etapa_datainicial);
		btDataFinal = (Button) findViewById(R.id.add_etapa_datafinal);
		tvDataInicial = (TextView) findViewById(R.id.add_etapa_mostrardtinicial);
		tvDataFinal = (TextView) findViewById(R.id.add_etapa_mostrardtfinal);
		
		this.dataEtapaInicio = Calendar.getInstance();
		this.dataEtapaInicio.setTimeInMillis(etapa.getDataInicio());
		
		this.dataEtapaFim = Calendar.getInstance();
		this.dataEtapaFim.setTimeInMillis(etapa.getDataFim());
		
		this.btDatainicial.setOnClickListener(this);
		this.btDataFinal.setOnClickListener(this);
		btn_salvar.setOnClickListener(this);
		dlgAlert  = new AlertDialog.Builder(this);
		
		this.dataInicio = Calendar.getInstance();
		this.dataInicio.setTimeInMillis(etapa.getDataInicio());
		
		this.dataFim = Calendar.getInstance();
		this.dataFim.setTimeInMillis(etapa.getDataFim());
		
		validarDtFim = Calendar.getInstance();
		validarDtFim.setTimeInMillis(programaDtFim);
		validarDtFim.set(Calendar.DAY_OF_MONTH, validarDtFim.get(Calendar.DAY_OF_MONTH)+1);
		
		this.editview.setText(etapa.getNome());
		int monthInicio = dataEtapaInicio.get(Calendar.MONTH) + 1;
		int monthFim = dataEtapaFim.get(Calendar.MONTH) + 1;
		
		this.tvDataInicial.setText(dataEtapaInicio.get(Calendar.DAY_OF_MONTH)+"-"+monthInicio+"-"+dataEtapaInicio.get(Calendar.YEAR));
		this.tvDataFinal.setText(dataEtapaFim.get(Calendar.DAY_OF_MONTH)+"-"+monthFim+"-"+dataEtapaFim.get(Calendar.YEAR));
		
	}

	public void onClick(View v) {
		if (v == btn_salvar) {
			if (editview.length() >1) {
				
				if (dataInicio.getTimeInMillis() < programaDtInicio) { // TODO: mostrar mensagem de erro e adicionar ao caso de uso
					Toast.makeText(getApplicationContext(), "Data inicial da Etapa nao pode ser menor do que a Data Inicial do Programa.", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (dataInicio.getTimeInMillis() > validarDtFim.getTimeInMillis()) { // TODO: mostrar mensagem de erro e adicionar ao caso de uso
					Toast.makeText(getApplicationContext(), "Data inicial da Etapa nao pode ser maior do que a Data Final do Programa.", Toast.LENGTH_LONG).show();
					return;
				}
				
				
				if (dataFim.getTimeInMillis() > validarDtFim.getTimeInMillis()) { // TODO: mostrar mensagem de erro e adicionar ao caso de uso
					Toast.makeText(getApplicationContext(), "Data Final da Etapa nao pode ser maior do que a Data Final do Programa.", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (dataFim.getTimeInMillis() < dataInicio.getTimeInMillis()) { // TODO: mostrar mensagem de erro e adicionar ao caso de uso
					Toast.makeText(getApplicationContext(), "Data Final da Etapa nao pode ser menor do que a Data Inicial da Etapa.", Toast.LENGTH_LONG).show();
					return;
				}
				
				etapa.setNome(editview.getText().toString());
				etapa.setProgramaID(programaID);
				etapa.setDataInicio(dataInicio.getTimeInMillis());
				etapa.setDataFim(dataFim.getTimeInMillis());
				
				etapaDao.salvar(etapa);
				
				Log.i("Etapa","Atualizada");
				dlgAlert.setMessage("Sua Etapa foi atualizada com sucesso");
				dlgAlert.setTitle("Etapa Atualizada");
				dlgAlert.setPositiveButton("OK", this);
				dlgAlert.create().show();
			}
		}
		else if (v == this.btDatainicial){
			this.dialogSelecionado = DIALOG_DATA_INICIO;
			showDialog(DIALOG_DATA_INICIO);
		}else if (v == this.btDataFinal){
			this.dialogSelecionado = DIALOG_DATA_FINAL;
			showDialog(DIALOG_DATA_FINAL);
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		
		if(id == DIALOG_DATA_INICIO){
			this.dialogDataInicio = new DatePickerDialog(this, this, dataEtapaInicio.get(Calendar.YEAR),dataEtapaInicio.get(Calendar.MONTH),dataEtapaInicio.get(Calendar.DAY_OF_MONTH));
			return this.dialogDataInicio;
		}else if(id == DIALOG_DATA_FINAL){
			this.dialogDataFim = new DatePickerDialog(this, this, dataEtapaFim.get(Calendar.YEAR),dataEtapaFim.get(Calendar.MONTH),dataEtapaFim.get(Calendar.DAY_OF_MONTH));
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
			this.dataInicio.set(Calendar.YEAR, year);
			this.dataInicio.set(Calendar.MONTH, month);
			this.dataInicio.set(Calendar.DAY_OF_MONTH, day);
		}
		
	}
	
	public void onClick(DialogInterface dialog, int which) {
		Log.i("OnClickDialog","Laço OnClick");
		
		//Pega o evento do click do Alert e chama o menu com a Tab
		if (which == AlertDialog.BUTTON_POSITIVE){
			Log.i("OnClickDialog","EtapaView");
			//intent = new Intent(this,EtapaView.class).putExtra("programaID", programaID).putExtra("programaNome",programaNome);
			//startActivity(intent);
			this.finish();
		}
	}
}
