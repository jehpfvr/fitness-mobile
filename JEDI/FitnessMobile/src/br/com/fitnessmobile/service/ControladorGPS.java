package br.com.fitnessmobile.service;

public interface ControladorGPS {
	
	public void startGPS();
	public void stopGPS();
	public double getDistancia();
	public void setOnControladorGPS(OnControladorGPSListener listener);
}
