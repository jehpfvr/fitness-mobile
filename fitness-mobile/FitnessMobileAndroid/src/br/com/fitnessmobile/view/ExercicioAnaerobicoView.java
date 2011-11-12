package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;
import br.com.fitnessmobile.dao.AnaerobicoDao;
import br.com.fitnessmobile.dao.EtapaExercicioDao;
import br.com.fitnessmobile.model.Anaerobico;

public class ExercicioAnaerobicoView extends Activity implements OnClickListener{
	
	private EditText tv_peso;
	private EditText tv_serie;
	private EditText tv_repeticao;
	private Button btn_salvar;
	private Button btn_cancelar;
	private AnaerobicoDao anaerobicoDao;
	
	private int ultimoID;
	private int etapaID;
	private int diaID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.exercicio_anaerobico);
		
		ultimoID = getIntent().getIntExtra("etapaExercicioID", -1);
		etapaID = getIntent().getIntExtra("etapaID", -1);
		diaID = getIntent().getIntExtra("diaID", -1);
		
		this.instanciarViews();
	}
	
	
	private void instanciarViews() {
		// TODO Auto-generated method stub
		this.tv_peso = (EditText) findViewById(R.id.EAnaerobico_Peso);
		this.tv_repeticao = (EditText) findViewById(R.id.EAnaerobico_Repeticao);
		this.tv_serie = (EditText) findViewById(R.id.EAnaerobico_Serie);
		
		this.btn_salvar = (Button) findViewById(R.id.btn_exercicio_salvar);
		btn_salvar.setOnClickListener(this);
		this.btn_cancelar = (Button) findViewById(R.id.btn_exercicio_cancelar);
		btn_cancelar.setOnClickListener(this);
		
		anaerobicoDao = new AnaerobicoDao(this);
		
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 == btn_salvar){
			
			if(!validarCampos()){
				vibrar();
				return;
			}
			Log.i("Campos","validados");
			String peso = tv_peso.getText().toString();
			String serie = tv_serie.getText().toString();
			String repeticao = tv_repeticao.getText().toString();
			
			Anaerobico anaerobico = new Anaerobico();
			anaerobico.setIdEta(ultimoID);
			anaerobico.setPeso(Float.parseFloat(peso));
			anaerobico.setSerie(Integer.parseInt(serie));
			anaerobico.setRepeticoes(Integer.parseInt(repeticao));
			anaerobicoDao.inserir(anaerobico);
			
			anaerobicoDao.Fechar();
			//startActivity(new Intent(this, ExercicioViewTab.class).putExtra("etapaID", etapaID).putExtra("diaID", diaID));
			this.finish();
		
		}else if(arg0 == btn_cancelar){
			EtapaExercicioDao etapaExercicioDao = new EtapaExercicioDao(this);
			etapaExercicioDao.excluir(ultimoID);
			etapaExercicioDao.Fechar();
			//startActivity(new Intent(this, ExercicioViewTab.class).putExtra("etapaID", etapaID).putExtra("diaID", diaID));
			this.finish();
		}
	}
	
	private void vibrar(){
		Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		// Vibrate for 300 milliseconds
		vb.vibrate(300);
	}
	
	private boolean validarCampos() {
		// TODO Auto-generated method stub
		boolean retorno = true;
		
		
		Log.i("Validando","Campos");
		if(tv_peso.getText().length() < 1 ){
			Toast.makeText(this, "Informe o peso!", 500).show();
			retorno = false;
		}
		if(tv_repeticao.getText().length() < 1){
			Toast.makeText(this, "Informe a quantidade de repetições!", 500).show();
			retorno = false;
		}
		if(tv_serie.getText().length() < 1){
			Toast.makeText(this, "Informe a quantidade de séries!!", 500).show();
			retorno = false;
		}
		return retorno;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		EtapaExercicioDao etapaExercicioDao = new EtapaExercicioDao(this);
		etapaExercicioDao.excluir(ultimoID);
		etapaExercicioDao.Fechar();
		this.finish();
	}
}