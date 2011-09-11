package br.com.fitnessmobile.view;

import java.util.ArrayList;
import java.util.List;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EtapaAdapter;
import br.com.fitnessmobile.model.Etapa;
import br.com.fitnessmobile.model.Exercicio;
import br.com.fitnessmobile.model.Programa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class EtapaView extends Activity implements OnItemClickListener, OnItemLongClickListener{
	private ListView listView;
	private List<Etapa> listaEtapa;
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
			this.listaEtapa = (List<Etapa>) getIntent().getSerializableExtra("etapa");
		}else this.listaEtapa = new ArrayList<Etapa>();
		
		this.listView.setAdapter(new EtapaAdapter(this, this.listaEtapa));
		this.listView.setOnItemClickListener(this);
		this.listView.setOnItemLongClickListener(this);
		
	}

	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
		
		
		return true;
	}

	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		Etapa etapa = (Etapa) adapter.getAdapter().getItem(pos);
		ArrayList<Exercicio> listaExercicio = (ArrayList<Exercicio>) etapa.getExercicios();
		Intent intent = new Intent(this, ExercicioView.class);
		intent.putExtra("exercicio", listaExercicio);
		startActivity(intent);
		
	}
}
