package br.com.fitnessmobile.service;

import java.text.DecimalFormat;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.Log;


public class ServiceGPS extends Service implements LocationListener,ControladorGPS,OnCronometroListener{
	
	private final IBinder conexao = new LocalBinder();
	private Location ultimoLocation = null;
	private GeoUtils geoUtils;
	private double distancia;
	private Cronometro cronometro;
	private double velocidade;
	private double velocidadeMedia;
	private long ultimoTempo;
	private double ultimaDistancia;
	private double ultimaVelocidade;
	private double aceleracao;
	private OnControladorGPSListener listener;
	private DecimalFormat df;
	private EstatisticaGPS estatisticaGPS;

	@Override
	public void onCreate() {
		super.onCreate();
		this.geoUtils = new GeoUtils();
		this.cronometro = new Cronometro();
		this.cronometro.setOnCronometroListener(this);
		this.distancia = 0;
		this.velocidade = 0;
		this.df = new DecimalFormat("0.00");
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
			//Log.v("gps", "latitude atual"+location.getLatitude());
			//Log.v("gps", "longitude atual"+location.getLongitude());
			
			//Log.v("gps", "latitude anterior"+ultimoLocation.getLatitude());
			//Log.v("gps", "longitude anterior"+ultimoLocation.getLongitude());
		
			
			Log.v("gps","tempo"+DateFormat.format("mm:ss", location.getTime()));
			
			//this.distancia += this.ultimoLocation.distanceTo(location)*3.6;
			this.estatisticaGPS.setTempoEmMovimento(location.getTime()-ultimoLocation.getTime());
			this.estatisticaGPS.setDistancia(this.ultimoLocation.distanceTo(location));
			
			Log.v("gps", "distancia"+estatisticaGPS.getDistancia());
			Log.v("gps", "velocidade"+estatisticaGPS.getVelocidadeMovimento());
			Log.v("gps","velocidade location"+location.getSpeed());
			
			//this.distancia += this.geoUtils.geoDistanceInKm(ultimoLocation, location);
			//this.velocidade = this.calcularVelocidade(distancia, this.cronometro.getHoras());
			this.velocidade = location.getSpeed();
			
			//this.aceleracao = this.calcularAceleracao(velocidade,cronometro.getHoras());
			
			//this.velocidadeMedia = this.velocidadeMedia(aceleracao,cronometro.getHoras());
			
			this.ultimoLocation = location;
			this.ultimoTempo = cronometro.getHoras();
			this.ultimaDistancia = this.distancia;
			this.ultimaVelocidade = this.velocidade;
			
		}

	}

	private double velocidadeMedia(double aceleracao, long tempo) {
		//V = V0 + a.(t – t0)
		return ultimaVelocidade+aceleracao*(tempo-ultimoTempo);
	}


	private double calcularAceleracao(double velocidade, long tempo) {
		// a=(V – V0) / (t – t0) ;
		return (velocidade-ultimaVelocidade)/(tempo-ultimoTempo);
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
		this.getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		this.cronometro.startCronometro();
		this.estatisticaGPS.setTempoInicial(System.currentTimeMillis());
	}

	public void stopGPS() {
		getLocationManager().removeUpdates(this);
		this.cronometro.stopCronometro();
		this.estatisticaGPS.setTempoFinal(System.currentTimeMillis());
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
	
	private double calcularVelocidade(double distancia, long duracao) {
		return (distancia - ultimaDistancia) / (duracao - ultimoTempo);
	}


	public void onCronometro(Cronometro cronometo) {
		estatisticaGPS.setTempoEmAndamento(cronometo.getMilissegundos());
		if(listener != null) listener.onControladorGPS(estatisticaGPS);
		
	}
}
