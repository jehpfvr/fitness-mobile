package br.com.fitnessmobile.view;

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
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.ProgramaAdapter;
import br.com.fitnessmobile.controller.Util;
import br.com.fitnessmobile.dao.ProgramaDao;
import br.com.fitnessmobile.model.Programa;

public class TabPrograma extends Activity implements OnItemLongClickListener, OnItemClickListener,DialogInterface.OnClickListener{
	private final int DIALOG_SELECIONAR = 1;
	private ListView listView;
	private ProgramaDao programaDao;
	private Intent intent;
	AlertDialog.Builder dlgAlert;
	
	
	private Programa programa_selecionado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.programa);
		this.instanciarViews();

	}

	private void instanciarViews() {
		
		this.programaDao = new ProgramaDao(this);
		
		this.listView = (ListView) findViewById(R.id.series_listview);
		
		this.listView.setAdapter(new ProgramaAdapter(this, this.programaDao.listarProgramas()));
		this.listView.setOnItemClickListener(this);
		this.listView.setOnItemLongClickListener(this);
		this.dlgAlert = new AlertDialog.Builder(this);
	}

	
	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
		final CharSequence[] items = getResources().getTextArray(R.array.array_opcoes);

		programa_selecionado = (Programa)adapter.getAdapter().getItem(pos);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.Opcoes);
		builder.setItems(items, this);
		AlertDialog alert = builder.create();
		alert.show();
		
		return true;
	}
	
	
	public void remover(){
	
		programaDao.excluir(programa_selecionado.getId());
		
	}
	

	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		Programa programa = (Programa) adapter.getAdapter().getItem(pos);
		Intent intent = new Intent(this, EtapaView.class);
		intent.putExtra("programaID", programa.getId());
		intent.putExtra("programaNome", programa.getNome());
		intent.putExtra("programaDtInicio", programa.getDataInicio());
		intent.putExtra("programaDtFim", programa.getDataFim());
		Log.i("passando parametros", "programa dt inicio" + programa.getDataInicio());
		Log.i("passando parametros", "programa dt fim" + programa.getDataFim());
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
	    	Log.i("log", "activity AddPrograma");
	        //chame aqui a activity de adicionar um programa
	    	startActivity(new Intent(this, AddPrograma.class));
	        return true;
	    case R.id.Opcoes:
	    	//chame aqui a activity de configuracoes
	    	Log.v("log", "activity configuracao");
	    	startActivity(new Intent(this, Configuracao.class));
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	public void onClick(DialogInterface dialog, int pos) {
		//trate aqui a selecao do click longo do botao
		if(dialog == dlgAlert ){
			if(pos == AlertDialog.BUTTON_POSITIVE){
				Log.i("Entrou ","no if");
				intent = new Intent(this,FitnessMobileTab.class).putExtra("aba", 1);
				startActivity(intent);	
			}
		}
		
		if(pos == 0){
			
		}else if (pos == 2){
			Log.i("Programa","Excluir");
			remover();
			
			dlgAlert.setMessage("Seu programa foi excluido");
			dlgAlert.setTitle("Programa Excluido");
			dlgAlert.setPositiveButton("OK", this);
			dlgAlert.create().show();
			
			intent = new Intent(this,FitnessMobileTab.class).putExtra("aba", 1);
			startActivity(intent);	
		}
	}
	
	@Override
	protected void onResume() {
		this.listView.setAdapter(new ProgramaAdapter(this, programaDao.listarProgramas()));
		super.onResume();
	}
}