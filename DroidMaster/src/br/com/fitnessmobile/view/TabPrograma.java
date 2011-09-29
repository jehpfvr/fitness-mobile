package br.com.fitnessmobile.view;

import java.util.ArrayList;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.ProgramaAdapter;
import br.com.fitnessmobile.dao.ProgramaDao;
import br.com.fitnessmobile.model.Etapa;
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
	private ProgramaDao programaDao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programa);
		this.instanciarViews();

	}

	private void instanciarViews() {
		
		this.programaDao = new ProgramaDao(this);
		
		this.listView = (ListView) findViewById(R.id.series_listview);
		
		this.listView.setAdapter(new ProgramaAdapter(this, this.programaDao.listarProgramas()));
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
	    case R.id.Novo:
	        //chame aqui a activity de adicionar um programa
	    	startActivity(new Intent(this, AddPrograma.class));
	        return true;
	    case R.id.Opcoes:
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
	
	@Override
	protected void onResume() {
		this.listView.setAdapter(new ProgramaAdapter(this, programaDao.listarProgramas()));
		super.onResume();
	}

}
