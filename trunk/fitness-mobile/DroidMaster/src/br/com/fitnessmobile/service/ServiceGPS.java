package br.com.fitnessmobile.service;

import java.util.ArrayList;
import java.util.List;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

public class ServiceGPS extends Service implements LocationListener,ControladorGPS,OnCronometroListener{
	
	private final IBinder conexao = new LocalBinder();
	private double distancia;
	private long tempo;
	private double velocidade;
	private Location ultimoLocation;
	private Cronometro cronometro;
	private List<OnControladorGPSListener> listener;
	private EstatisticaGPS estatisticaGPS;
	private List<Coordenada> trajeto;


	@Override
	public void onCreate() {
		super.onCreate();
		this.cronometro = new Cronometro();
		this.cronometro.setOnCronometroListener(this);
		this.distancia = 0;
		this.estatisticaGPS = new EstatisticaGPS();
		this.trajeto = new ArrayList<Coordenada>();
		this.listener = new ArrayList<OnControladorGPSListener>();
		
	}
	

	@Override
	public IBinder onBind(Intent arg0) {
		return conexao;
	}

	public void onLocationChanged(Location location) {
		if(ultimoLocation == null){
			ultimoLocation = location;
		}else{
			
			this.distancia = this.ultimoLocation.distanceTo(location);
			
			this.tempo = location.getTime() - ultimoLocation.getTime();
			
			this.velocidade = (distancia)/((double) Math.abs(tempo/1000));
			
			this.estatisticaGPS.setDistancia(distancia);
			this.estatisticaGPS.setVelocidade(velocidade*3.6);
			
			if(!location.equals(ultimoLocation))
			this.estatisticaGPS.setUltimoLocation(location);

			this.trajeto.add(new Coordenada(location));
			this.ultimoLocation = location;
			
		}

	}

	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}

	public void startGPS() {
		getLocationManager().requestLocationUpdates( LocationManager.GPS_PROVIDER,1000, 0, this);
		
		this.cronometro.startCronometro();
	}


	public void stopGPS() {
		getLocationManager().removeUpdates(this);
		this.cronometro.stopCronometro();
	}
	
	private LocationManager getLocationManager(){
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		return locationManager;
	}

	public double getDistancia() {
		return distancia;
	}

	
	public class LocalBinder extends Binder {
		public ControladorGPS getControlador(){
			return ServiceGPS.this;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		getLocationManager().removeUpdates(this);
	}


	public void setOnControladorGPS(OnControladorGPSListener listener) {
		this.listener.add(listener);
	}

	public void onCronometro(Cronometro cronometo) {
		estatisticaGPS.setTempoEmAndamento(cronometo.getMilissegundos());
		
		if(!listener.isEmpty()){
			for (OnControladorGPSListener c : listener) {
				c.onControladorGPS(estatisticaGPS);
			}
		}
		
	}


	public List<Coordenada> getTrajeto() {
		return trajeto;
	}


	public void removerOnControladorGPS(OnControladorGPSListener listener) {
		this.listener.remove(listener);
		
	}
}