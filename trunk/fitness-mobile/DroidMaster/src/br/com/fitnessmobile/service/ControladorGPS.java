package br.com.fitnessmobile.service;

import com.google.android.maps.GeoPoint;

public interface ControladorGPS {
	
	public void startGPS();
	public void stopGPS();
	public double getDistancia();
	public void setOnControladorGPS(OnControladorGPSListener listener);
	public GeoPoint ultimaLocalizacao();
}
