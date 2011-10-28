package br.com.fitnessmobile.view;


import java.util.Calendar;
import java.util.Date;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.dao.ProgramaDao;
import br.com.fitnessmobile.model.Programa;
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

public class AddPrograma extends Activity implements OnClickListener, OnDateSetListener, android.content.DialogInterface.OnClickListener {
	
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
	private Date dataInicio;
	private Date dataFim;
	private DatePickerDialog dialogDataInicio;
	private DatePickerDialog dialogDataFim;
	private int dialogSelecionado;
	AlertDialog.Builder dlgAlert;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_programa);
		this.instanciarViews();
	}

	private void instanciarViews() {
		this.programa = new Programa();
		this.programaDao = new ProgramaDao(this);
		
		this.etNome = (EditText) findViewById(R.id.add_programa_nome);
		this.tvDataInicial = (TextView) findViewById(R.id.add_programa_mostrardtinicial);
		this.tvDataFinal = (TextView) findViewById(R.id.add_programa_mostrardtfinal);
		this.btSalvar = (Button) findViewById(R.id.add_programa_salvar);
		this.btDatainicial = (Button) findViewById(R.id.add_programa_datainicial);
		this.btDataFinal = (Button) findViewById(R.id.add_programa_datafinal);
		
		dlgAlert  = new AlertDialog.Builder(this);
		
		this.btSalvar.setOnClickListener(this);
		this.btDatainicial.setOnClickListener(this);
		this.btDataFinal.setOnClickListener(this);
	}

	public void onClick(View v) {
		if(v == this.btSalvar){
			
			// programa nao pode ter data inicial menor do que a data atual
			Date dataAtual = new Date(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			if (dataInicio.getTime() < dataAtual.getTime()) { // TODO adicionar ao casos de uso
				Toast.makeText(getApplicationContext(), "Data Inicial nao pode ser menor do que a Data de Atual.", Toast.LENGTH_SHORT).show();
				return;
			}
			if (dataInicio.getTime() > dataFim.getTime()) { // TODO adicionar ao casos de uso
				Toast.makeText(getApplicationContext(), "Data Inicial nao pode ser maior do que a Data Final.", Toast.LENGTH_SHORT).show();
				return;
			}
			
			this.programa.setNome(this.etNome.getText().toString());
			this.programa.setDataInicio(this.dataInicio.getTime());
			this.programa.setDataFim(this.dataFim.getTime());
			
			this.programaDao.salvar(programa);
			
			dlgAlert.setMessage("Seu programa foi adicionado com sucesso");
			dlgAlert.setTitle("Programa adicionado");
			dlgAlert.setPositiveButton("OK", this);
			dlgAlert.create().show();
			this.onDestroy();
				
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
			Log.i("OnClickDialog","AddPRograma" );
			intent = new Intent(this,FitnessMobileTab.class).putExtra("aba", 1);
			startActivity(intent);
		}
	}
}