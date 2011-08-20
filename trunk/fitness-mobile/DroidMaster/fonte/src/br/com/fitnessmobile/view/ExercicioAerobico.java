package br.com.fitnessmobile.view;
import br.com.fitnessmobile.R;
import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;


public class ExercicioAerobico extends Activity implements LocationListener{
	
	private TextView duracao;
	private TextView distancia;
	private TextView velocidade;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicio_aerobico);
		this.instanciarViews();
	}

	private void instanciarViews() {
		this.duracao = (TextView) findViewById(R.id.exer_aero_display_duracao);
		this.distancia = (TextView) findViewById(R.id.exer_aero_display_distancia);
		this.velocidade = (TextView) findViewById(R.id.exer_aero_display_velocidade);
		
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
