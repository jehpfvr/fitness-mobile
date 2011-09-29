package br.com.fitnessmobile.view;

import java.util.ArrayList;
import java.util.List;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EtapaAdapter;
import br.com.fitnessmobile.model.EtapaExercicio;
import br.com.fitnessmobile.model.Exercicio;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class EtapaView extends Activity implements OnItemClickListener, OnItemLongClickListener{
	private ListView listView;
	private List<EtapaExercicio> listaEtapa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.etapa);
		
		this.instanciarView();
	}
	
	@SuppressWarnings("unchecked")
	private void instanciarView() {
		this.listView = (ListView) findViewById(R.id.etapa_listview);
		
		if(getIntent().getSerializableExtra("etapa") != null){
			this.listaEtapa = (List<EtapaExercicio>) getIntent().getSerializableExtra("etapa");
		}else this.listaEtapa = new ArrayList<EtapaExercicio>();
		
		this.listView.setAdapter(new EtapaAdapter(this, this.listaEtapa));
		this.listView.setOnItemClickListener(this);
		this.listView.setOnItemLongClickListener(this);
		
	}

	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
		
		
		return true;
	}

	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		EtapaExercicio etapa = (EtapaExercicio) adapter.getAdapter().getItem(pos);
		ArrayList<Exercicio> listaExercicio = (ArrayList<Exercicio>) etapa.getExercicios();
		Intent intent = new Intent(this, ExercicioView.class);
		intent.putExtra("exercicio", listaExercicio);
		startActivity(intent);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_default, menu);
		return true;
	}
}
