package br.com.fitnessmobile.view;


import java.util.Calendar;
import java.util.Date;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.dao.ProgramaDao;
import br.com.fitnessmobile.model.Programa;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class AddPrograma extends Activity implements OnClickListener, OnDateSetListener {
	
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
		
		this.btSalvar.setOnClickListener(this);
		this.btDatainicial.setOnClickListener(this);
		this.btDataFinal.setOnClickListener(this);
	}

	public void onClick(View v) {
		if(v == this.btSalvar){
			this.programa.setNome(this.etNome.getText().toString());
			this.programa.setDataInicio(this.dataInicio.getTime());
			this.programa.setDataFim(this.dataFim.getTime());
			
			this.programaDao.salvar(programa);
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

}
