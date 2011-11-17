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
import br.com.fitnessmobile.dao.ProgramaDao;
import br.com.fitnessmobile.model.Programa;

public class EditarPrograma extends Activity implements OnClickListener, OnDateSetListener, android.content.DialogInterface.OnClickListener {
	
	static final int DIALOG_DATA_INICIO = 0;
	static final int DIALOG_DATA_FINAL = 1;
	
	private EditText etNome;
	private TextView tvDataInicial;
	private TextView tvDataFinal;
	private Button btSalvar;
	private Button btDatainicial;
	private Button btDataFinal;
	private Programa programa;
	private ProgramaDao programaDao;
	private Calendar dataInicio;
	private Calendar dataFim;
	private Calendar dataAtual;
	private DatePickerDialog dialogDataInicio;
	private DatePickerDialog dialogDataFim;
	private int dialogSelecionado;
	AlertDialog.Builder dlgAlert;
	Intent intent;
	private int programaID;
	
	private Calendar dataProgramaInicio;
	private Calendar dataProgramaFim;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.add_programa);
		programaID = getIntent().getIntExtra("Programa",-1);
		Log.i("Recebido programa",""+programaID);
		this.instanciarViews();
		
	}

	private void instanciarViews() {
		
		this.programaDao = new ProgramaDao(this);
		
		programa = programaDao.buscarPrograma(programaID); 
		
		this.etNome = (EditText) findViewById(R.id.add_programa_nome);
		this.tvDataInicial = (TextView) findViewById(R.id.add_programa_mostrardtinicial);
		this.tvDataFinal = (TextView) findViewById(R.id.add_programa_mostrardtfinal);
		this.btSalvar = (Button) findViewById(R.id.add_programa_salvar);
		this.btDatainicial = (Button) findViewById(R.id.add_programa_datainicial);
		this.btDataFinal = (Button) findViewById(R.id.add_programa_datafinal);
		
		this.dataProgramaInicio = Calendar.getInstance();
		this.dataProgramaInicio.setTimeInMillis(programa.getDataInicio());
		
		this.dataProgramaFim = Calendar.getInstance();
		this.dataProgramaFim.setTimeInMillis(programa.getDataFim());
		
		dlgAlert  = new AlertDialog.Builder(this);
		
		this.btSalvar.setOnClickListener(this);
		this.btDatainicial.setOnClickListener(this);
		this.btDataFinal.setOnClickListener(this);
		
		this.etNome.setText(programa.getNome());
		
		int monthInicio = dataProgramaInicio.get(Calendar.MONTH) + 1;
		int monthFim = dataProgramaFim.get(Calendar.MONTH) + 1;
		
		this.tvDataInicial.setText(dataProgramaInicio.get(Calendar.DAY_OF_MONTH)+"-"+monthInicio+"-"+dataProgramaInicio.get(Calendar.YEAR));
		this.tvDataFinal.setText(dataProgramaFim.get(Calendar.DAY_OF_MONTH)+"-"+monthFim+"-"+dataProgramaFim.get(Calendar.YEAR));
		
		
		this.dataInicio = Calendar.getInstance();
		this.dataInicio.setTimeInMillis(programa.getDataInicio());
		
		this.dataFim = Calendar.getInstance();
		this.dataFim.setTimeInMillis(programa.getDataFim());
		
		dataAtual = Calendar.getInstance();
		dataAtual.set(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)-1);
	}

	public void onClick(View v) {
		if(v == this.btSalvar){
			
			// programa nao pode ter data inicial menor do que a data atual
			if (dataInicio.getTimeInMillis() < dataAtual.getTimeInMillis()) { // TODO adicionar ao casos de uso
				Toast.makeText(getApplicationContext(), "Data Inicial nao pode ser menor do que a Data de Atual.", Toast.LENGTH_SHORT).show();
				return;
			}
			if (dataInicio.getTimeInMillis() > dataFim.getTimeInMillis()) { // TODO adicionar ao casos de uso
				Toast.makeText(getApplicationContext(), "Data Inicial nao pode ser maior do que a Data Final.", Toast.LENGTH_SHORT).show();
				return;
			}
			
			this.programa.setNome(this.etNome.getText().toString());
			this.programa.setDataInicio(this.dataInicio.getTimeInMillis());
			this.programa.setDataFim(this.dataFim.getTimeInMillis());
			
			this.programaDao.salvar(programa);
			
			dlgAlert.setMessage("Seu programa foi atualizado com sucesso");
			dlgAlert.setTitle("Programa Atualizado");
			dlgAlert.setPositiveButton("OK", this);
			dlgAlert.create().show();
			
		}else if (v == this.btDatainicial){
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
			this.dialogDataInicio = new DatePickerDialog(this, this, dataProgramaInicio.get(Calendar.YEAR),dataProgramaInicio.get(Calendar.MONTH),dataProgramaInicio.get(Calendar.DAY_OF_MONTH));
			return this.dialogDataInicio;
		}else if(id == DIALOG_DATA_FINAL){
			this.dialogDataFim = new DatePickerDialog(this, this, dataProgramaFim.get(Calendar.YEAR),dataProgramaFim.get(Calendar.MONTH), dataProgramaFim.get(Calendar.DAY_OF_MONTH));
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
		
		//Pega o evento do click do Alert e chama o menu com a Tab
		if (which == DialogInterface.BUTTON_POSITIVE){
			Log.i("OnClickDialog","AddPRograma" );
			//intent = new Intent(this,FitnessMobileTab.class).putExtra("aba", 1);
			//startActivity(intent);
			this.finish();
		}
	}
}
