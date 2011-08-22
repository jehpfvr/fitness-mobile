package br.com.fitnessmobile.view;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.service.ControladorGPS;
import br.com.fitnessmobile.service.ServiceGPS.LocalBinder;
import br.com.fitnessmobile.service.TimeUtils;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.TextView;


public class ExercicioAerobicoView extends Activity implements OnClickListener,ServiceConnection, OnChronometerTickListener{

	protected static final int MSG_ATUALIZAR = 1;
	private Chronometer cro_duracao;
	private TextView tv_distancia;
	private TextView tv_velocidade;
	private Button btn_iniciar;
	private Button btn_parar;
	private ControladorGPS controlador;
	private DecimalFormat df;
	private double ultimaDistancia;
	private Date ultimaDuracao;
	private SimpleDateFormat sdf;
	private TimeUtils timeUtils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicio_aerobico);
		this.instanciarViews();
		this.df = new DecimalFormat("0.00");
		this.sdf = new SimpleDateFormat("mm:ss");
		timeUtils = new TimeUtils();
		bindService(new Intent("SERVICO_GPS"), this, Context.BIND_AUTO_CREATE);
	}

	public void AtualizarTela(){
		try {
			if(ultimaDistancia == 0){
				this.ultimaDuracao = sdf.parse((String) this.cro_duracao.getText());
				this.ultimaDistancia = controlador.getDistancia();	
			}else{
				Date temp = sdf.parse((String) this.cro_duracao.getText());
				this.tv_distancia.setText(df.format(controlador.getDistancia()));
				this.tv_velocidade.setText(df.format(this.calcularVelocidade(controlador.getDistancia(), timeUtils.getSegundos(temp.getTime()))));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void instanciarViews() {
		this.cro_duracao = (Chronometer) findViewById(R.id.exer_aero_display_duracao);
		this.tv_distancia = (TextView) findViewById(R.id.exer_aero_display_distancia);
		this.tv_velocidade = (TextView) findViewById(R.id.exer_aero_display_velocidade);
		
		this.btn_iniciar = (Button) findViewById(R.id.exer_aero_btn_iniciar);
		this.btn_parar = (Button) findViewById(R.id.exer_aero_btn_parar);
		
		this.btn_iniciar.setOnClickListener(this);
		this.btn_parar.setOnClickListener(this);
		this.cro_duracao.setOnChronometerTickListener(this);
	}

	private double calcularVelocidade(double distancia, long duracao) {
		return (distancia - ultimaDistancia) / (duracao - timeUtils.getSegundos(ultimaDuracao.getTime()));
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(this);
	}
	

	public void onClick(View v) {
		Button button = (Button) v;
		
		if(button.getText().equals(getString(R.string.Pausar))){
			this.btn_iniciar.setText(R.string.Iniciar);
			controlador.stopGPS();
			this.cro_duracao.stop();
		}else if(v == btn_parar){
			controlador.stopGPS();
			this.cro_duracao.stop();
		}else if(v == btn_iniciar){
			this.btn_iniciar.setText(R.string.Pausar);
			controlador.startGPS();
			this.cro_duracao.start();
		}
	}


	public void onServiceConnected(ComponentName name, IBinder service) {
		LocalBinder binder = (LocalBinder) service;
		this.controlador = binder.getControlador();
	}

	public void onServiceDisconnected(ComponentName name) {
		this.controlador = null;
	}
	
	public void onChronometerTick(Chronometer c) {
		try {
			if(ultimaDistancia == 0){
				this.ultimaDuracao = sdf.parse((String) this.cro_duracao.getText());
				this.ultimaDistancia = controlador.getDistancia();	
			}else{
				Date segundos = sdf.parse((String) c.getText());
				this.tv_distancia.setText(df.format(controlador.getDistancia()));
				this.tv_velocidade.setText(df.format(this.calcularVelocidade(controlador.getDistancia(), timeUtils.getSegundos(segundos.getTime()))));
				this.ultimaDuracao = sdf.parse((String) this.cro_duracao.getText());
				this.ultimaDistancia = controlador.getDistancia();	
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

};


