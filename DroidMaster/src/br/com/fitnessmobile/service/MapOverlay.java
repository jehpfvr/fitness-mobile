package br.com.fitnessmobile.service;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.location.Location;
import br.com.fitnessmobile.R;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapOverlay extends Overlay{
	
	private List<Coordenada> listaCoordenada;
	private final Paint caminhoPaint;
	private boolean fim;
	
	public MapOverlay() {
		
		this.listaCoordenada = new ArrayList<Coordenada>();
		
		caminhoPaint = new Paint();
	    caminhoPaint.setColor(Color.RED);
	    caminhoPaint.setStrokeWidth(3);
	    caminhoPaint.setStyle(Paint.Style.STROKE);
	    caminhoPaint.setAntiAlias(true);
	    
	    this.fim = false;
	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		
				final Projection projection = mapView.getProjection();
				Point ponto = new Point();
				Point ultimoPonto = new Point();
				
				if (!listaCoordenada.isEmpty()) {
					projection.toPixels(listaCoordenada.get(0), ultimoPonto);
					desenharMarcacao(canvas,ultimoPonto,mapView,true);
					for (Coordenada c : listaCoordenada) {						
						projection.toPixels(c, ponto);
						canvas.drawLine(ultimoPonto.x, ultimoPonto.y, ponto.x, ponto.y,caminhoPaint);
						ultimoPonto.x = ponto.x;
						ultimoPonto.y = ponto.y;

					}
					if(fim)
					desenharMarcacao(canvas,ultimoPonto,mapView,false);
				}

	}
	

	public void setFim(boolean fim) {
		this.fim = fim;
	}

	private void desenharMarcacao(Canvas canvas, Point p, MapView mapView, boolean inicio) {	
		Bitmap bitmap = null;
		if(inicio)
			bitmap = BitmapFactory.decodeResource(mapView.getResources(),R.drawable.green_dot);
		else
			bitmap = BitmapFactory.decodeResource(mapView.getResources(),R.drawable.red_dot);
			
		//calculo centraliza a imagem no inicio do traçado do trajeto.
		RectF r = new RectF(p.x-(bitmap.getWidth()/2), p.y - bitmap.getHeight(), p.x + (bitmap.getWidth()/2), p.y);
		canvas.drawBitmap(bitmap, null, r, new Paint()); 
	}

	public void addLocation(Location location){
		if(location != null){
			//testa se coordenada ja existe
			if(!listaCoordenada.contains(new Coordenada(location)))
			this.listaCoordenada.add(new Coordenada(location));
		}
	}

	public List<Coordenada> getListaCoordenada() {
		return listaCoordenada;
	}

	public void setListaCoordenada(List<Coordenada> listaCoordenada) {
		this.listaCoordenada = listaCoordenada;
	}	
	
	
}
