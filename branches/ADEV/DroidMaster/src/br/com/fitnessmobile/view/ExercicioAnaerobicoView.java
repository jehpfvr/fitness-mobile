package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.fitnessmobile.R;
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicio_anaerobico);
		
		ultimoID = getIntent().getIntExtra("ultimoID", -1);
		etapaID = getIntent().getIntExtra("etapaID", -1);
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
			
			String peso = tv_peso.getText().toString();
			String serie = tv_serie.getText().toString();
			String repeticao = tv_repeticao.getText().toString();
			
			Anaerobico anaerobico = new Anaerobico();
			anaerobico.setId(ultimoID);
			anaerobico.setPeso(Float.parseFloat(peso));
			anaerobico.setSerie(Integer.parseInt(serie));
			anaerobico.setRepeticoes(Integer.parseInt(repeticao));
			anaerobicoDao.inserir(anaerobico);
			
			anaerobicoDao.Fechar();
			
			startActivity(new Intent(this, ExercicioView.class).putExtra("etapaID", etapaID));
			
		
		}else if(arg0 == btn_cancelar){
			EtapaExercicioDao etapaExercicioDao = new EtapaExercicioDao(this);
			etapaExercicioDao.excluir(ultimoID);
			etapaExercicioDao.Fechar();
			startActivity(new Intent(this, ExercicioView.class).putExtra("etapaID", etapaID));
		}
	}
	

}
