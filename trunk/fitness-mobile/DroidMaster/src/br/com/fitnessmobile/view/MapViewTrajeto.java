package br.com.fitnessmobile.view;

import java.text.DecimalFormat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.format.DateFormat;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.service.CacheMapView;
import br.com.fitnessmobile.service.ControladorGPS;
import br.com.fitnessmobile.service.Coordenada;
import br.com.fitnessmobile.service.EstatisticaGPS;
import br.com.fitnessmobile.service.MapOverlay;
import br.com.fitnessmobile.service.OnControladorGPSListener;
import br.com.fitnessmobile.service.ServiceGPS.LocalBinder;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class MapViewTrajeto extends MapActivity implements ServiceConnection,OnControladorGPSListener{
	
	private MapView mapView;
	private MapController mapController;
	private MapOverlay mapOverlay;
	private TextView tvDistancia;
	private TextView tvDuracao;
	private TextView tvVelocidade;
	private ControladorGPS controlador;
	private EstatisticaGPS dados;
	private DecimalFormat df;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.map_view_trajeto);
		
		bindService(new Intent("SERVICO_GPS"), this, Context.BIND_AUTO_CREATE);
		this.instanciarViews();
		
	}

	private void instanciarViews() {
		this.df = new DecimalFormat("0.00");
		this.dados = new EstatisticaGPS();
		
		
		this.mapView = (MapView) findViewById(R.id.mapview_trajeto);
		this.mapView.setBuiltInZoomControls(true);
		this.mapController = this.mapView.getController();
		
		this.mapOverlay = new MapOverlay(this);
		this.mapView.getOverlays().add(mapOverlay);
		
		this.tvDistancia = (TextView) findViewById(R.id.trajeto_distancia);
		this.tvDuracao = (TextView) findViewById(R.id.trajeto_duracao);
		this.tvVelocidade = (TextView) findViewById(R.id.trajeto_velocidade);
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onServiceConnected(ComponentName name, IBinder service) {
		LocalBinder binder = (LocalBinder) service;
		this.controlador = binder.getControlador();
		this.controlador.setOnControladorGPS(this);
		this.mapOverlay.setListaCoordenada(this.controlador.getTrajeto());
		
		if(!this.controlador.getTrajeto().isEmpty())
		this.mapController.setCenter(controlador.getTrajeto().get(0));
		
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
        	tvDistancia.setText(df.format(dados.getDistancia()));
    		tvVelocidade.setText(df.format(dados.getVelocidade()));
    		tvDuracao.setText(DateFormat.format("mm:ss", dados.getTempoEmAndamento()));
    		
    		mapOverlay.setListaCoordenada(controlador.getTrajeto());
    		mapView.invalidate();
    	
        }
   };
   
   @Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(this);
	}

}