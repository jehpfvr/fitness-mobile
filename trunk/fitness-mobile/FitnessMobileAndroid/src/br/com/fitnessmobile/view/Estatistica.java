package br.com.fitnessmobile.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EstaExerAdapter;
import br.com.fitnessmobile.dao.ExercicioDao;

public class Estatistica extends Activity{
	
	private ExercicioDao exercicioDao;
	private ListView listView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programa);
		
		instanciarViews();
		configurarViews();
	}

	private void instanciarViews() {
		this.exercicioDao = new ExercicioDao(this);
		this.listView = (ListView) findViewById(R.id.series_listview);
	}

	private void configurarViews() {
		this.listView.setAdapter(new EstaExerAdapter(this, this.exercicioDao.getEstatisticasByExer()));
	}
}