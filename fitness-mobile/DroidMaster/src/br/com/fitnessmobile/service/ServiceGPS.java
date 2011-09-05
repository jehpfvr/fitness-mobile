package br.com.fitnessmobile.service;

import com.google.android.maps.GeoPoint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
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
	private OnControladorGPSListener listener;
	private EstatisticaGPS estatisticaGPS;
	private GeoPoint localizacao;


	@Override
	public void onCreate() {
		super.onCreate();
		this.cronometro = new Cronometro();
		this.cronometro.setOnCronometroListener(this);
		this.distancia = 0;
		this.estatisticaGPS = new EstatisticaGPS();
		
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
			//Log.v("gps","metros "+distancia);
			//Log.v("gps","Kilometros "+distancia/1000);
			this.tempo = location.getTime()-ultimoLocation.getTime();
			//Log.v("gps","milisengundos "+tempo);
			//Log.v("gps","Segundos "+tempo/1000);
			//Log.v("gps","minutos "+tempo/60000);
			//Log.v("gps","horas "+tempo/3600000);
			this.velocidade = (distancia)/((double) tempo/1000);
			
			//Log.v("gps","m/s"+this.velocidade);
			//Log.v("gps","km/h "+this.velocidade*3.6);
			
			
			this.estatisticaGPS.setTesteVelocidade(location.getSpeed());
			this.estatisticaGPS.setDistancia(distancia);
			this.estatisticaGPS.setVelocidade(velocidade*3.6);

			
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
		this.listener = listener;
	}

	public void onCronometro(Cronometro cronometo) {
		estatisticaGPS.setTempoEmAndamento(cronometo.getMilissegundos());
		if(listener != null) listener.onControladorGPS(estatisticaGPS);
		
	}


	public GeoPoint ultimaLocalizacao() {
		return new Coordenada(getLocationManager().getLastKnownLocation(LocationManager.GPS_PROVIDER));
	}
}
