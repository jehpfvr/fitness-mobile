package br.com.fitnessmobile.service;

import java.io.Serializable;
import com.google.android.maps.GeoPoint;
import android.location.Location;

public class Coordenada extends GeoPoint implements Serializable {

		private static final long serialVersionUID = 1L;

		// valores em graus * 1E6 (microdegrees)
		public Coordenada(int latitudeE6, int longitudeE6) {
			super(latitudeE6, longitudeE6);
		}

		// Converte para "graus * 1E6"
		public Coordenada(double latitude, double longitude) {
			this((int) (latitude * 1E6), (int) (longitude * 1E6));
		}

		// Cria baseado no objeto 'Location' diretamente recebido do GPS
		public Coordenada(Location location) {
			this(location.getLatitude(),location.getLongitude());
		}
		
		@Override
		public int getLatitudeE6() {
			return super.getLatitudeE6();
		}

		@Override
		public int getLongitudeE6() {
			return super.getLongitudeE6();
		}

		public double getLatitude() {
			return super.getLatitudeE6() / 1E6;
		}

		public double getLongitude() {
			return super.getLongitudeE6() / 1E6;
		}


}
