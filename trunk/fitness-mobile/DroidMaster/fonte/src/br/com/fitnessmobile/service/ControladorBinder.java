package br.com.fitnessmobile.service;

import android.os.Binder;

public class ControladorBinder extends Binder {
	
	private ControladorGPS controlador;

	public ControladorBinder(ControladorGPS controlador) {
		this.controlador = controlador;
	}

	public ControladorGPS getControlador() {
		return controlador;
	}

}
