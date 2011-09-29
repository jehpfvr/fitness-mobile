package br.com.fitnessmobile.service;

public class TimeUtils {

	public TimeUtils() {
	}
	
	public long getSegundos(long ms){
		return ms/1000;
	}
	
	public long getHoras(long ms){
		return ms/60000;
	}
	
	public long getMilis(long s){
		return s * 1000;
	}

}
