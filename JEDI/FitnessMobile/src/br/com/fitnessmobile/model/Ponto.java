package br.com.fitnessmobile.model;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public class Ponto extends GeoPoint {

	public Ponto(int latitudeE6, int longitudeE6) {
		super(latitudeE6, longitudeE6);
	}
	
	public Ponto(double latitude, double longitude) {
		super((int)(latitude*1E6), (int) (longitude*1E6));
	}
	
	public Ponto(Location location) {
		super((int)location.getLatitude(),(int)location.getLongitude());
	}
	
}
