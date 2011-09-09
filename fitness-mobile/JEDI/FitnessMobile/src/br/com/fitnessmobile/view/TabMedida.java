package br.com.fitnessmobile.view;

import java.util.Calendar;
import br.com.fitnessmobile.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;



public class TabMedida extends Activity implements OnClickListener,OnDateSetListener{
	private TextView tv_data_medida;
	private Button btn_data;
	private Button btn_hoje;
    static final int DATE_DIALOG_ID = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medidas);
		
		btn_data = (Button) findViewById(R.id.btn_data_medida);
		btn_hoje  = (Button) findViewById(R.id.btn_hoje_medida);
		btn_data.setOnClickListener(this);
		btn_hoje.setOnClickListener(this);
		
		tv_data_medida = (TextView) findViewById(R.id.data_medida);
		
		
	}

	public void onClick(View v) {
		if(v == btn_data)
		showDialog(DATE_DIALOG_ID);
		else if(v == btn_hoje){
			String dia = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			String mes = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
			String ano = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
			tv_data_medida.setText(dia+"-"+mes+"-"+ano);
		}
		
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
		int diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		
		return new DatePickerDialog(this, this, anoAtual,mesAtual, diaAtual);
	}

	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		String dataSelecionada = String.valueOf(dayOfMonth)+"-"+String.valueOf(view.getMonth()+1)+"-"+String.valueOf(year);
		tv_data_medida.setText(dataSelecionada);
	}


}
