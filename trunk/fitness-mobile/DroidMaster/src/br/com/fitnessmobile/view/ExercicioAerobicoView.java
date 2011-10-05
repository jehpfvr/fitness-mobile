package br.com.fitnessmobile.view;

import java.text.DecimalFormat;
import com.google.android.maps.MapActivity;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.service.ControladorGPS;
import br.com.fitnessmobile.service.EstatisticaGPS;
import br.com.fitnessmobile.service.OnControladorGPSListener;
import br.com.fitnessmobile.service.ServiceGPS.LocalBinder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class ExercicioAerobicoView extends MapActivity implements OnClickListener,ServiceConnection, OnControladorGPSListener{

	protected static final int MSG_ATUALIZAR = 1;
	private TextView tv_distancia;
	private TextView tv_velocidade;
	private TextView tv_duracao;
	private Button btn_iniciar;
	private Button btn_mapa;
	private Button btn_parar;
	private EstatisticaGPS dados;
	private ControladorGPS controlador;
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicio_aerobico);

		bindService(new Intent("SERVICO_GPS"), this, Context.BIND_AUTO_CREATE);
		
		this.instanciarViews();
	}
 

	private void instanciarViews() {
		this.dados = new EstatisticaGPS();
		
		this.df = new DecimalFormat("0.00");
		this.tv_distancia = (TextView) findViewById(R.id.exer_aero_display_distancia);
		this.tv_velocidade = (TextView) findViewById(R.id.exer_aero_display_velocidade);
		this.tv_duracao = (TextView) findViewById(R.id.exer_aero_display_duracao);
	
		this.btn_iniciar = (Button) findViewById(R.id.exer_aero_btn_iniciar);
		this.btn_parar = (Button) findViewById(R.id.exer_aero_btn_parar);
		this.btn_parar.setEnabled(false);
		this.btn_mapa = (Button) findViewById(R.id.exer_aero_btn_mapa);
		
		this.btn_iniciar.setOnClickListener(this);
		this.btn_parar.setOnClickListener(this);
		this.btn_mapa.setOnClickListener(this);
		
	}
	
	@Override
	protected void onResume() {
		if(controlador != null )this.controlador.setOnControladorGPS(this);
		super.onResume();
	}

	
	
	
	@Override
	protected void onDestroy() {
		if(dados != null){
			Intent intent = new Intent();
			intent.putExtra("distancia", dados.getDistancia());
			intent.putExtra("tempo", dados.getTempoEmAndamento());
			intent.putExtra("velocidade", dados.getVelocidadeMaxima());
			setResult(1, intent);
		}
		
		if(controlador != null )this.controlador.removerOnControladorGPS(this);
		unbindService(this);
		super.onDestroy();
	}
	

	public void onClick(View v) {

		Button button = (Button) v;
		
		if(button.getText().equals(getString(R.string.Pausar))){
			this.btn_iniciar.setText(R.string.Iniciar);
			controlador.stopGPS(false);
		}else if (button.getText().equals(getString(R.string.Zerar))) {
			this.btn_parar.setText(R.string.Parar);
			this.btn_iniciar.setEnabled(true);
			this.btn_parar.setEnabled(false);
			this.controlador.Zerar();
		}
		else if(v == btn_parar){
			this.btn_parar.setText(R.string.Zerar);
			this.btn_iniciar.setEnabled(false);
			controlador.stopGPS(true);
		}else if(v == btn_iniciar){
			this.btn_iniciar.setText(R.string.Pausar);
			this.btn_parar.setEnabled(true);
			controlador.startGPS();
		}else if(v == btn_mapa){
			startActivity(new Intent(this, MapViewTrajeto.class));
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
	
	
	public void onControladorGPS(EstatisticaGPS dados) {	
		this.dados = dados;
		handler.sendEmptyMessage(0);
	}
	
	private Handler handler = new Handler() {
        public void  handleMessage(Message msg) {
        	tv_distancia.setText(df.format(dados.getDistancia()));
    		tv_velocidade.setText(df.format(dados.getVelocidade()));
    		tv_duracao.setText(DateFormat.format("mm:ss", dados.getTempoEmAndamento()));
   
        }
   };

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}	

};


