package br.com.fitnessmobile.view;


import java.util.ArrayList;
import java.util.List;

import br.com.fitnessmobile.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import br.com.fitnessmobile.adapters.ExercicioAdapter;
import br.com.fitnessmobile.model.*;

public class TabExercicios extends Activity {
	
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicios);
		
		Musculo musculo = new Musculo();
		musculo.setImagem(R.drawable.icon);
		
		Exercicio exercicio = new Exercicio();
		exercicio.setNome("Supino Reto");
		exercicio.setMusculoPrincipal(musculo);
		
		List<Exercicio> listExercicio = new ArrayList<Exercicio>();
		listExercicio.add(exercicio);
		
		
		this.listView = (ListView) findViewById(R.id.list_view_exercicio);
		this.listView.setAdapter(new ExercicioAdapter(this, listExercicio));
		
	}

}
