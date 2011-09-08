package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.service.ControladorGPS;
import br.com.fitnessmobile.service.DadosGPS;
import br.com.fitnessmobile.service.OnControladorGPSListener;
import br.com.fitnessmobile.service.ServiceGPS.LocalBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class ExercicioAerobicoView extends Activity implements OnClickListener,ServiceConnection, OnControladorGPSListener{

	protected static final int MSG_ATUALIZAR = 1;
	private TextView tv_distancia;
	private TextView tv_velocidade;
	private TextView tv_duracao;
	private TextView tv_aceleracao;
	private Button btn_iniciar;
	private Button btn_parar;
	private DadosGPS dados;
	private ControladorGPS controlador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicio_aerobico);
		this.instanciarViews();

		bindService(new Intent("SERVICO_GPS"), this, Context.BIND_AUTO_CREATE);
	}
 

	private void instanciarViews() {
		this.tv_distancia = (TextView) findViewById(R.id.exer_aero_display_distancia);
		this.tv_velocidade = (TextView) findViewById(R.id.exer_aero_display_velocidade);
		this.tv_duracao = (TextView) findViewById(R.id.exer_aero_display_duracao);
		this.tv_aceleracao = (TextView) findViewById(R.id.exer_aero_display_aceleracao);
		
		this.btn_iniciar = (Button) findViewById(R.id.exer_aero_btn_iniciar);
		this.btn_parar = (Button) findViewById(R.id.exer_aero_btn_parar);
		
		this.btn_iniciar.setOnClickListener(this);
		this.btn_parar.setOnClickListener(this);
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
		}else if(v == btn_parar){
			controlador.stopGPS();
			//this.cro_duracao.stop();
		}else if(v == btn_iniciar){
			this.btn_iniciar.setText(R.string.Pausar);
			controlador.startGPS();
		}
	}


	public void onServiceConnected(ComponentName name, IBinder service) {
		LocalBinder binder = (LocalBinder) service;
		this.controlador = binder.getControlador();
		this.controlador.setOnControladorGPS(this);
	}

	public void onServiceDisconnected(ComponentName name) {
		this.controlador = null;
	}
	
	
	public void onControladorGPS(DadosGPS dados) {	
		this.dados = dados;
		handler.sendEmptyMessage(0);
	}
	
	private Handler handler = new Handler() {
        public void  handleMessage(Message msg) {
        	tv_distancia.setText(dados.getDistancia());
    		tv_velocidade.setText(dados.getVelocidade());
    		tv_duracao.setText(dados.getDuracao());
    		tv_aceleracao.setText(dados.getAceleracao());
        }
   };

};


