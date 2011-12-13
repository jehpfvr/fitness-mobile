package br.com.fitnessmobile.view;

import java.util.Calendar;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;
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
	private Calendar validarDtFim;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.add_etapa);
		programaID = getIntent().getIntExtra("programaID", -1);
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
		
		this.dataInicio = Calendar.getInstance();
		this.dataFim = Calendar.getInstance();
		
		validarDtFim = Calendar.getInstance();
		validarDtFim.setTimeInMillis(programaDtFim);
		validarDtFim.set(Calendar.DAY_OF_MONTH, validarDtFim.get(Calendar.DAY_OF_MONTH)+1);
	}
		

	public void onClick(View v) {
		if (v == btn_salvar) {
			if(editview.getText().length() < 1){
				Toast.makeText(getApplicationContext(), "Preencha o nome da etapa", Toast.LENGTH_LONG).show();
				vibrar();
				return;
			}
			if (editview.length() >1) {
				
				if (dataInicio.getTimeInMillis() < programaDtInicio) {
					Toast.makeText(getApplicationContext(), "Data inicial da Etapa nao pode ser menor do que a Data Inicial do Programa.", Toast.LENGTH_LONG).show();
					vibrar();
					return;
				}
				
				if (dataInicio.getTimeInMillis() > validarDtFim.getTimeInMillis()) { 
					Toast.makeText(getApplicationContext(), "Data inicial da Etapa nao pode ser maior do que a Data Final do Programa.", Toast.LENGTH_LONG).show();
					vibrar();
					return;
				}
				
				
				if (dataFim.getTimeInMillis() > validarDtFim.getTimeInMillis()) { 
					Toast.makeText(getApplicationContext(), "Data Final da Etapa nao pode ser maior do que a Data Final do Programa.", Toast.LENGTH_LONG).show();
					vibrar();
					return;
				}
				
				if (dataFim.getTimeInMillis() < dataInicio.getTimeInMillis()) { 
					Toast.makeText(getApplicationContext(), "Data Final da Etapa nao pode ser menor do que a Data Inicial da Etapa.", Toast.LENGTH_LONG).show();
					vibrar();
					return;
				}
				
				
				Etapa novaEtapa = new Etapa();
				novaEtapa.setNome(editview.getText().toString());
				novaEtapa.setProgramaID(programaID);
				novaEtapa.setDataInicio(dataInicio.getTimeInMillis());
				novaEtapa.setDataFim(dataFim.getTimeInMillis());
				
				etapaDao.salvar(novaEtapa);
			
				dlgAlert.setMessage("Sua Etapa foi adicionada com sucesso");
				dlgAlert.setTitle("Etapa adicionada");
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
	
	
	public void vibrar() {
		Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		// Vibrate for 300 milliseconds
		vb.vibrate(300);
	}
	
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Fecha o banco
        etapaDao.Fechar();
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
			this.tvDataInicial.setText(String.valueOf(day)+"/"+String.valueOf(dialog.getMonth()+1)+"/"+String.valueOf(year));
			this.dataInicio.set(Calendar.YEAR, year);
			this.dataInicio.set(Calendar.MONTH, month);
			this.dataInicio.set(Calendar.DAY_OF_MONTH, day);
		}else if(this.dialogSelecionado == DIALOG_DATA_FINAL){
			this.tvDataFinal.setText(String.valueOf(day)+"/"+String.valueOf(dialog.getMonth()+1)+"/"+String.valueOf(year));
			this.dataFim.set(Calendar.YEAR, year);
			this.dataFim.set(Calendar.MONTH, month);
			this.dataFim.set(Calendar.DAY_OF_MONTH, day);
		}
		
	}
	
	public void onClick(DialogInterface dialog, int which) {
		Log.i("OnClickDialog","Laço OnClick");
		
		//Pega o evento do click do Alert e chama o menu com a Tab
		if (which == DialogInterface.BUTTON_POSITIVE){
			this.finish();
		}
	}
}
