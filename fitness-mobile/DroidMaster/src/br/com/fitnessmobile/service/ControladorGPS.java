package br.com.fitnessmobile.service;

import java.util.List;

public interface ControladorGPS {
	
	public void startGPS();
	public void stopGPS();
	public void setOnControladorGPS(OnControladorGPSListener listener);
	public void removerOnControladorGPS(OnControladorGPSListener listener);
	public List<Coordenada> getTrajeto();
}
