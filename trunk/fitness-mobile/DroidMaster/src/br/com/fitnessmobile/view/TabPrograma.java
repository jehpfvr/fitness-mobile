package br.com.fitnessmobile.view;

import java.util.ArrayList;
import java.util.List;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.ProgramaAdapter;
import br.com.fitnessmobile.model.Etapa;
import br.com.fitnessmobile.model.EtapaExercicio;
import br.com.fitnessmobile.model.Exercicio;
import br.com.fitnessmobile.model.Musculo;
import br.com.fitnessmobile.model.Programa;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class TabPrograma extends Activity implements OnItemLongClickListener, OnItemClickListener,DialogInterface.OnClickListener{
	private final int DIALOG_SELECIONAR = 1;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programa);
		this.instanciarViews();

	}

	private void instanciarViews() {
		
		this.listView = (ListView) findViewById(R.id.series_listview);
		
		//daqui para baixo tem que ser preenchido com dados do banco de dados...
		Musculo musculo = new Musculo();
		musculo.setImagem(R.drawable.icon);
		musculo.setNome("Peitoral");
		List<Musculo> listMusculo = new ArrayList<Musculo>();
		listMusculo.add(musculo);
		
		Exercicio exercicio = new Exercicio();
		exercicio.setDescricao("Supino Reto");
		exercicio.setGrupoMuscular(listMusculo);
		exercicio.setMusculoPrincipal(musculo);
		exercicio.setNome("Supino Reto");
		exercicio.setTipo('N');
		
		Exercicio corrida = new Exercicio();
		corrida.setDescricao("Corrida");
		corrida.setGrupoMuscular(listMusculo);
		corrida.setMusculoPrincipal(musculo);
		corrida.setNome("Corrida");
		corrida.setTipo('E');
		
		List<Exercicio> listExercicio= new ArrayList<Exercicio>();
		listExercicio.add(exercicio);
		listExercicio.add(corrida);
		
		EtapaExercicio etapaExercicio = new EtapaExercicio();
		etapaExercicio.setExercicios(listExercicio);
		etapaExercicio.setNome("Peito e Corrida");
		
		/*
		List<EtapaExercicio> listaEtapa = new ArrayList<EtapaExercicio>();
		listaEtapa.add(etapa);
		*/
		
		Etapa etapa = new Etapa();
		etapa.setNome("Peito e Corrida");
		etapa.setId(1);
		
		List<Etapa> listaEtapa = new ArrayList<Etapa>();
		listaEtapa.add(etapa);
		
		Programa programa = new Programa();
		programa.setNome("Hipertrofia");
		programa.setEtapas(listaEtapa);
		
		List<Programa> listSerie = new ArrayList<Programa>();
		listSerie.add(programa);
		
		this.listView.setAdapter(new ProgramaAdapter(this, listSerie));
		this.listView.setOnItemClickListener(this);
		this.listView.setOnItemLongClickListener(this);
	}

	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
		final CharSequence[] items = getResources().getTextArray(R.array.array_opcoes);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.Opcoes);
		builder.setItems(items, this);
		AlertDialog alert = builder.create();
		alert.show();
		return true;
	}

	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		Programa programa = (Programa) adapter.getAdapter().getItem(pos);
		ArrayList<Etapa> listaEtapa = (ArrayList<Etapa>) programa.getEtapas();
		Intent intent = new Intent(this, EtapaView.class);
		intent.putExtra("etapa", listaEtapa);
		startActivity(intent);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		DialogExercicios dialogExercicio = null;
		if(id == DIALOG_SELECIONAR){
			dialogExercicio = new DialogExercicios(this);
			dialogExercicio.show();
		}
		return dialogExercicio;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_default, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    case R.id.novo:
	        //chame aqui a activity de adicionar um programa
	    	Log.v("log", "adicionar Programa");
	        return true;
	    case R.id.opcoes:
	    	//chame aqui a activity de configurações
	    	Log.v("log", "activity configuração");
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	public void onClick(DialogInterface dialog, int pos) {
		//trate aqui a seleção do click longo do botão
		if(pos == 0){
			
		}
		
	}

}
