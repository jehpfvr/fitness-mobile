package br.com.fitnessmobile.view;


import java.util.Calendar;
import java.util.Date;

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
import br.com.fitnessmobile.dao.EtapaDao;
import br.com.fitnessmobile.model.Etapa;

public class AddEtapa extends Activity implements OnClickListener,OnDateSetListener, android.content.DialogInterface.OnClickListener{
	
	
	static final int DIALOG_DATA_INICIO = 0;
	static final int DIALOG_DATA_FINAL = 1;

	private EditText editview;
	private Button btn_salvar;

	private EtapaDao etapaDao;
	AlertDialog.Builder dlgAlert;
	
	private Integer programaID;
	private String programaNome;
	private long programaDtInicio;
	private long programaDtFim;
	private int dialogSelecionado;
	private TextView tvDataInicial;
	private TextView tvDataFinal;
	private Button btDatainicial;
	private Button btDataFinal;
	private Date dataInicio;
	private Date dataFim;
	private DatePickerDialog dialogDataInicio;
	private DatePickerDialog dialogDataFim;
	Intent intent;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_etapa);
		programaID = getIntent().getIntExtra("programaID", -1);
		programaNome = getIntent().getStringExtra("programaNome");
		programaDtInicio = getIntent().getLongExtra("programaDtInicio",-1);
		programaDtFim = getIntent().getLongExtra("programaDtFim",-1);
		this.instanciarViews();
	}
	
	private void instanciarViews() {
		etapaDao = new EtapaDao(this);
		editview = (EditText) findViewById(R.id.add_etapa_nome);
		btn_salvar = (Button) findViewById(R.id.add_etapa_salvar);
		btDatainicial = (Button) findViewById(R.id.add_etapa_datainicial);
		btDataFinal = (Button) findViewById(R.id.add_etapa_datafinal);
		tvDataInicial = (TextView) findViewById(R.id.add_etapa_mostrardtinicial);
		tvDataFinal = (TextView) findViewById(R.id.add_etapa_mostrardtfinal);
		
		
		this.btDatainicial.setOnClickListener(this);
		this.btDataFinal.setOnClickListener(this);
		btn_salvar.setOnClickListener(this);
		dlgAlert  = new AlertDialog.Builder(this);
		
		
	}

	public void onClick(View v) {
		if (v == btn_salvar) {
			if (editview.length() >1) {
				
				if (dataInicio.getTime() < programaDtInicio) { // TODO: mostrar mensagem de erro e adicionar ao caso de uso
					Toast.makeText(getApplicationContext(), "Data inicial da Etapa nao pode ser menor do que a Data Inicial do Programa.", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (dataInicio.getTime() > programaDtFim) { // TODO: mostrar mensagem de erro e adicionar ao caso de uso
					Toast.makeText(getApplicationContext(), "Data inicial da Etapa nao pode ser maior do que a Data Final do Programa.", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (dataFim.getTime() > programaDtFim) { // TODO: mostrar mensagem de erro e adicionar ao caso de uso
					Toast.makeText(getApplicationContext(), "Data Final da Etapa nao pode ser maior do que a Data Final do Programa.", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (dataFim.getTime() < dataInicio.getTime()) { // TODO: mostrar mensagem de erro e adicionar ao caso de uso
					Toast.makeText(getApplicationContext(), "Data Final da Etapa nao pode ser menor do que a Data Inicial da Etapa.", Toast.LENGTH_LONG).show();
					return;
				}
				
				
				Etapa novaEtapa = new Etapa();
				novaEtapa.setNome(editview.getText().toString());
				novaEtapa.setProgramaID(programaID);
				novaEtapa.setDataInicio(dataInicio.getTime());
				novaEtapa.setDataFim(dataFim.getTime());
				
				etapaDao.salvar(novaEtapa);
				
				Log.i("Etapa","Salva");
				
				dlgAlert.setMessage("Sua Etapa foi adicionada com sucesso");
				dlgAlert.setTitle("Etapa adicionado");
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
			this.dataInicio = new Date(year, month, day);
		}else if(this.dialogSelecionado == DIALOG_DATA_FINAL){
			this.tvDataFinal.setText(String.valueOf(day)+"-"+String.valueOf(dialog.getMonth()+1)+"-"+String.valueOf(year));
			this.dataFim = new Date(year, month, day);
		}
		
	}
	
	
	public void onClick(DialogInterface dialog, int which) {
		Log.i("OnClickDialog","Laço OnClick");
		
		//Pega o evento do click do Alert e chama o menu com a Tab
		if (which == AlertDialog.BUTTON_POSITIVE){
			Log.i("OnClickDialog","EtapaView");
			intent = new Intent(this,EtapaView.class).putExtra("programaID", programaID).putExtra("programaNome",programaNome);
			startActivity(intent);
		}
	}
}
