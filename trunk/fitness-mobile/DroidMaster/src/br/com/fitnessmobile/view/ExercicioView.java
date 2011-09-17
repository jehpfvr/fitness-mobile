package br.com.fitnessmobile.view;

import java.util.ArrayList;
import java.util.List;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.ExercicioAdapter;
import br.com.fitnessmobile.model.Exercicio;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ExercicioView extends Activity implements OnItemLongClickListener, OnItemClickListener {
	
	private ListView listView;
	private List<Exercicio> listaExercicio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.exercicio);
		this.instanciarViews();
	}

	@SuppressWarnings("unchecked")
	private void instanciarViews() {
		this.listView = (ListView) findViewById(R.id.exercicio_listview);
		
		if(getIntent().getSerializableExtra("exercicio") != null){
			this.listaExercicio = (ArrayList<Exercicio>) getIntent().getSerializableExtra("exercicio");
		}else this.listaExercicio = new ArrayList<Exercicio>();
		
		this.listView.setAdapter(new ExercicioAdapter(this, this.listaExercicio));
		this.listView.setOnItemClickListener(this);
		this.listView.setOnItemLongClickListener(this);
		
		
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_default, menu);
		return true;
	}
}
