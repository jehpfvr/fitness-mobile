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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.service.ControladorGPS;
import br.com.fitnessmobile.service.EstatisticaGPS;
import br.com.fitnessmobile.service.MapOverlay;
import br.com.fitnessmobile.service.OnControladorGPSListener;
import br.com.fitnessmobile.service.ServiceGPS.LocalBinder;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

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
	private boolean foco_automatico;
	private MyLocationOverlay myLocationOverlay;
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.map_view_trajeto);
		
		bindService(new Intent("SERVICO_GPS"), this, Context.BIND_AUTO_CREATE);
		this.instanciarViews();
		
		this.foco_automatico = false;
		
	}

	private void instanciarViews() {
		this.df = new DecimalFormat("0.00");
		this.dados = new EstatisticaGPS();
		
		
		this.mapView = (MapView) findViewById(R.id.mapview_trajeto);
		this.mapView.setBuiltInZoomControls(true);
		this.mapController = this.mapView.getController();
		
		this.mapOverlay = new MapOverlay();
		this.myLocationOverlay = new MyLocationOverlay(this, this.mapView);
		this.mapView.getOverlays().add(mapOverlay);
		this.mapView.getOverlays().add(myLocationOverlay);
		this.myLocationOverlay.enableMyLocation();
		
		this.tvDistancia = (TextView) findViewById(R.id.trajeto_distancia);
		this.tvDuracao = (TextView) findViewById(R.id.trajeto_duracao);
		this.tvVelocidade = (TextView) findViewById(R.id.trajeto_velocidade);
		
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_mapa, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    case R.id.mapa_tipo:
		     if(mapView.isSatellite()){
		    	 this.mapView.setSatellite(false);
		    	 item.setTitle(R.string.Satelite);
		     }
		     else{
		    	 this.mapView.setSatellite(true);
		    	 item.setTitle(R.string.Mapa);
		     }
		        return true;
		        
	    case R.id.mapa_focus:
	    	
		     if(foco_automatico)
		    	 this.foco_automatico = false;
		     else
		    	 this.foco_automatico = true;
		     return true;
		        
	    case R.id.Opcoes:
	    	//chame aqui a activity de configurações
	    	Log.v("log", "activity configuração");
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
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
    		
    		if(dados.isMarcaFim()){
    			mapOverlay.setFim(dados.isMarcaFim());
    			myLocationOverlay.disableMyLocation();
    			mapView.getOverlays().remove(myLocationOverlay);
    		}
    		
    		if(foco_automatico){
    			if(!controlador.getTrajeto().isEmpty()){
    				int size = controlador.getTrajeto().size();
	    			GeoPoint ponto = controlador.getTrajeto().get(size-1);
	    			mapController.setCenter(ponto);
	    			mapController.setZoom(21);
    			}
    		}
    		
    		mapView.invalidate();
    	
        }
   };
   
   @Override
	protected void onDestroy() {
	    unbindService(this);
		super.onDestroy();
		
	}

}
