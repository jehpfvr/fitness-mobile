package br.com.fitnessmobile.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EtapaAdapter;
import br.com.fitnessmobile.dao.EtapaDao;
import br.com.fitnessmobile.model.Etapa;

public class EtapaView extends Activity implements OnItemClickListener, OnItemLongClickListener, OnLongClickListener,DialogInterface.OnClickListener{
	private TextView textView;
	private ListView listView;
	private Integer programaID;
	private String programaNome;
	private EtapaDao etapaDao;
	private Etapa etapa_selecionada;
	private Intent intent;
	AlertDialog.Builder dlgAlert;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.etapa);
		
		programaID = getIntent().getIntExtra("programaID", -1);
		programaNome = getIntent().getStringExtra("programaNome");
		
		this.instanciarView();
	}
	
	private void instanciarView() {
		this.etapaDao = new EtapaDao(this);
		this.textView = (TextView) findViewById(R.id.etapa_textview);
		this.textView.setText("Etapas do Programa: " + programaNome);
		this.listView = (ListView) findViewById(R.id.etapa_listview);
		this.dlgAlert = new AlertDialog.Builder(this);
		
		
		if(!programaID.equals(-1)){
			Log.i("fitness", "Listando Etapas do Programa com ID" + programaID);
			this.listView.setAdapter(new EtapaAdapter(this, etapaDao.listarEtapaByProgramaID(programaID)));
			this.listView.setOnItemClickListener(this);
			this.listView.setOnItemLongClickListener(this);
		}
		
	}	
	// TODO Colocar opcoes do LongClick
	
	
	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
		final CharSequence[] items = getResources().getTextArray(R.array.array_opcoes);

		etapa_selecionada = (Etapa)adapter.getAdapter().getItem(pos);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.Opcoes);
		builder.setItems(items, (OnClickListener) this);
		AlertDialog alert = builder.create();
		alert.show();
		
		return true;
	}

	// TODO Abrir a Activity contendo os exercicios da etapa Clicada
	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		Etapa etapa_clicada = (Etapa)adapter.getAdapter().getItem(pos);
		startActivity(new Intent(this, ExercicioView.class).putExtra("etapaID", etapa_clicada.getId()));
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
	    	Log.i("log", "activity AddEtapa");
	        //chame aqui a activity de adicionar uma etapa ao programa
	    	startActivity(new Intent(this, AddEtapa.class).putExtra("programaID", programaID).putExtra("programaNome", programaNome));
	        return true;
	    case R.id.Opcoes:
	    	//chame aqui a activity de configuracoes
	    	Log.v("log", "activity configuracao");
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	protected void onResume() {
		this.listView.setAdapter(new EtapaAdapter(this, etapaDao.listarEtapaByProgramaID(programaID)));
		super.onResume();
	}
	
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Fecha o banco
        etapaDao.Fechar();
    }

	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void removerEtapa(){
		
		etapaDao.excluir(etapa_selecionada.getId());
		
	}
	
	public void atualizarEtapa(){
		
		
	}

	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub
		if(arg0 == dlgAlert ){
			if(arg1 == AlertDialog.BUTTON_POSITIVE){
				Log.i("Entrou ","no if");
				intent = new Intent(this,FitnessMobileTab.class).putExtra("aba", 1);
				startActivity(intent);	
			}
		}
		
		if(arg1 == 0){
			Log.i("Etapa","Selecionar");
		}//Colocar para selecionar e excluir mais de uma
		
		else if(arg1 == 1){
			Log.i("Etapa","Editar");
			atualizarEtapa();
			
		}
		else if (arg1 == 2){
			Log.i("Etapa","Excluir");
			removerEtapa();
			
			dlgAlert.setMessage("Sua etapa foi excluida");
			dlgAlert.setTitle("Etapa Excluida");
			dlgAlert.setPositiveButton("OK", this);
			dlgAlert.create().show();
			
	}
  
  }
}
		