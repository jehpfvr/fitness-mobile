package br.com.fitnessmobile.view;

import android.app.Activity;
import android.os.Bundle;

public class EstatisticaBarraLinha extends Activity {
	String type;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		type = getIntent().getStringExtra("type");
		boolean _type;
		float[] values = new float[] { 1.0f, 2.0f, 3.0f, 4.0f };
		String[] verlabels = new String[] { "Ruim", "Normal", "Bom" };
		String[] horlabels = new String[] { "Feitos", "Pendentes" };
		
		if (type.equals("barra")) {  _type = GraphView.BAR; }
		else if (type.equals("linha")) { _type = GraphView.LINE; }
		else { _type = GraphView.BAR; }
		GraphView graphView = new GraphView(this, values, horlabels, verlabels, _type);
		setContentView(graphView);
	}
}
