package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.dao.AerobicoDao;
import br.com.fitnessmobile.model.Aerobico;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExercicioAerobicoManual extends Activity implements OnClickListener{
	
	private EditText et_distancia;
	private EditText et_horas;
	private EditText et_minutos;
	private EditText et_segundos;
	private TextView tv_calorias;
	private TextView tv_velocidade;
	private Button bt_salvar;
	private Button bt_cancelar;
	private Button bt_calcular;
	private AerobicoDao dao;
	private int etapaExercicioID;
	private float indiceCalorico;
	private Double peso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicio_aerobico_manual);
		
		etapaExercicioID = getIntent().getIntExtra("etapaExercicioID", -1);
		this.indiceCalorico = getIntent().getFloatExtra("indiceCalorico", 0);
		
		this.peso = 88.9;
		
		this.instanciarViews();

	}

	private void instanciarViews() {
		this.dao = new AerobicoDao(this);
		
		this.et_distancia = (EditText) findViewById(R.id.eam_et_distancia);
		this.et_horas = (EditText) findViewById(R.id.eam_et_horas);
		this.et_minutos = (EditText) findViewById(R.id.eam_et_minutos);
		this.et_segundos = (EditText) findViewById(R.id.eam_et_segundos);
		this.tv_calorias = (TextView) findViewById(R.id.eam_tv_calorias);
		this.tv_velocidade = (TextView) findViewById(R.id.eam_tv_vm);
		
		this.bt_salvar = (Button) findViewById(R.id.eam_bt_salvar);
		this.bt_salvar.setOnClickListener(this);
		this.bt_cancelar = (Button) findViewById(R.id.eam_bt_cancelar);
		this.bt_cancelar.setOnClickListener(this);
		this.bt_calcular = (Button) findViewById(R.id.eam_bt_calcular);
		this.bt_calcular.setOnClickListener(this);
		
		
	}

	public void onClick(View v) {
		if(v == this.bt_salvar){
			this.salvarExercicio();
		}
		else if(v == this.bt_cancelar){
			finish();
		}else if(v == this.bt_calcular){
			this.calcularCaloriaVelocidade();
		}
		
	}

	private void calcularCaloriaVelocidade() {
		if(this.validarCampos()){
			Double velocidade = this.calcularVelocidade();
			Double caloria = this.calcularCaloria();
			this.tv_velocidade.setText(velocidade.toString());
			this.tv_calorias.setText(caloria.toString());
		}
	}

	private Double calcularCaloria() {
		Double tempo = obterMinutos();
		Log.v("tempo", "minutos "+tempo);
		Double calorias = ((this.indiceCalorico * this.peso)/50) * tempo;
		return calorias;

	}

	private void salvarExercicio() {
		if(validarCampos() && validarVelocidadeCaloria()){
			Aerobico a = new Aerobico();
			a.setDistancia(Double.parseDouble(this.et_distancia.getText().toString()));
			a.setDuracao(this.obterMilisegundos());
			a.setVelocidade(this.calcularVelocidade());
			a.setId_exc(etapaExercicioID);
			dao.inserir(a);
			dao.Fechar();
			Toast.makeText(this, R.string.Exercicio_Salvo, Toast.LENGTH_LONG).show();
			finish();
		}
		
	}

	private boolean validarVelocidadeCaloria() {
		boolean validado = false;
		if(!tv_velocidade.getText().toString().equals("00") && !tv_calorias.getText().toString().equals("0.00"))
			return true;
		return validado;
	}

	private Double calcularVelocidade() {
		if(this.et_distancia.getText().toString().length() > 0){
			Double distancia = Double.valueOf(this.et_distancia.getText().toString());
			Double duracao = obterHoras();
			return distancia/duracao;
		}else
			return (double) 0;
	}


	private long obterMilisegundos() {
		Integer horas = Integer.valueOf(this.et_horas.getText().toString()); 
		Integer minutos = Integer.parseInt(this.et_minutos.getText().toString());
		Integer segundos =  Integer.parseInt(this.et_segundos.getText().toString());
		
		return (horas*3600000)+(minutos*60000)+(segundos*1000);
	}

	private Double obterHoras(){
		Double horas = Double.valueOf(this.et_horas.getText().toString()); 
		Double minutos = Double.valueOf(this.et_minutos.getText().toString());
		Double segundos =  Double.valueOf(this.et_segundos.getText().toString());
		
		return (horas+(minutos/60)+(segundos/3600));
	}
	
	private Double obterMinutos(){
		Double horas = Double.valueOf(this.et_horas.getText().toString()); 
		Double minutos = Double.valueOf(this.et_minutos.getText().toString());
		Double segundos =  Double.valueOf(this.et_segundos.getText().toString());
		
		return ((horas*60)+minutos+(segundos/60));
	}

	private boolean validarCampos() {

		if(!et_distancia.getText().equals("") && this.validarCampoDuracao())
			return true;
		else{
			Toast.makeText(this, R.string.Preencha_campos, Toast.LENGTH_LONG).show();
			return false;
		}
		
	}

	private boolean validarCampoDuracao() {
		
		boolean validado = false;
		
		if(Integer.valueOf(this.et_horas.getText().toString()) > 0 || 
				Integer.parseInt(this.et_minutos.getText().toString()) > 0 || 
				Integer.parseInt(this.et_segundos.getText().toString()) > 0)
			validado = true;
		
		return validado;
	}

}
