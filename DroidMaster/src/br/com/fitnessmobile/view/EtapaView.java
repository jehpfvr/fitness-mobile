package br.com.fitnessmobile.view;

import android.app.Activity;
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
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EtapaAdapter;
import br.com.fitnessmobile.dao.EtapaDao;
import br.com.fitnessmobile.model.Etapa;

public class EtapaView extends Activity implements OnItemClickListener, OnItemLongClickListener{
	private TextView textView;
	private ListView listView;
	private Integer programaID;
	private String programaNome;
	private EtapaDao etapaDao;
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
		
		
		if(!programaID.equals(-1)){
			Log.i("fitness", "Listando Etapas do Programa com ID" + programaID);
			this.listView.setAdapter(new EtapaAdapter(this, etapaDao.listarEtapaByProgramaID(programaID)));
			this.listView.setOnItemClickListener(this);
			this.listView.setOnItemLongClickListener(this);
		}
		
	}	
	// TODO Colocar op��es do LongClick
	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
		
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
}