package br.com.fitnessmobile.service;

import android.content.Context;

import com.google.android.maps.MapView;

public class CacheMapView {
	
	static private MapView mapView;

	public CacheMapView() {
	}

	public static MapView getMapView(Context context) {
		if(mapView == null){
			mapView = new MapView(context, "0X1tFn3N-pGDS6m75qX1rY1usj7E4IqEhkV247A");
		}
		return mapView;
	}	

}
