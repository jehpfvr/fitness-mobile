package br.com.fitnessmobile.service;

import android.location.Location;

public class GeoUtils {

	public final int EARTH_RADIUS_KM = 6371;
	private final int EARTH_RADIUS_M = 12756200;
	
	public GeoUtils() {
	}

	public double geoDistanceInKm(Location primeiroLocation, Location segundoLocation) {
		return geoDistanceInRadios(primeiroLocation.getLatitude(), primeiroLocation.getLongitude(), 
                segundoLocation.getLatitude(), segundoLocation.getLongitude()) * EARTH_RADIUS_KM;
	}

	public  double geoDistanceInMetros(Location primeiroLocation, Location segundoLocation) {
				return geoDistanceInRadios(primeiroLocation.getLatitude(), primeiroLocation.getLongitude(), 
						                   segundoLocation.getLatitude(), segundoLocation.getLongitude()) * EARTH_RADIUS_M;
	}

	private double geoDistanceInRadios(double firstLatitude,
			                                  double firstLongitude, 
			                                  double secondLatitude, 
			                                  double secondLongitude) {

		// Conversão de graus pra radianos das latitudes
		double firstLatToRad = Math.toRadians(firstLatitude);
		double secondLatToRad = Math.toRadians(secondLatitude);

		// Diferença das longitudes
		double deltaLongitudeInRad = Math.toRadians(secondLongitude
				- firstLongitude);

		// Calculo da distância entre os pontos
		return Math.acos(Math.cos(firstLatToRad) * Math.cos(secondLatToRad)
				* Math.cos(deltaLongitudeInRad) + Math.sin(firstLatToRad)
				* Math.sin(secondLatToRad));
	}

}
