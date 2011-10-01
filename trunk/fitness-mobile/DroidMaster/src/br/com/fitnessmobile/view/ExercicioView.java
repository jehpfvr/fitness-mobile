package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EtapaExercicioAdapter;
import br.com.fitnessmobile.dao.EtapaExercicioDao;

public class ExercicioView extends Activity implements OnItemLongClickListener, OnItemClickListener, OnClickListener {
	
	private ListView listView;
	private Button btn_exercicio_salvar;
	private Integer etapaID;
	private EtapaExercicioDao etapaExercicioDao; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.exercicio);
		etapaID = getIntent().getIntExtra("etapaID", -1);
		this.instanciarViews();
	}

	private void instanciarViews() {
		etapaExercicioDao = new EtapaExercicioDao(this);
		this.listView = (ListView) findViewById(R.id.exercicio_listview);
		this.btn_exercicio_salvar = (Button) findViewById(R.id.add_exercicio_salvar);
		this.btn_exercicio_salvar.setOnClickListener(this);
		
		if(!etapaID.equals(-1)){
			Log.i("fitness", "Listando Exercicios da Etapa com ID" + etapaID);
			/*
			List<EtapaExercicio> lista_teste = new ArrayList<EtapaExercicio>();
			Exercicio exercicio_teste = new Exercicio();
			exercicio_teste.setNome("ExercicioTeste");
			EtapaExercicio etapa_exercicio_teste = new EtapaExercicio();
			etapa_exercicio_teste.setExercicio(exercicio_teste);
			etapa_exercicio_teste.setEtapaid(1);
			etapa_exercicio_teste.setFlag(1);
			lista_teste.add(etapa_exercicio_teste);
			lista_teste.add(etapa_exercicio_teste);
			lista_teste.add(etapa_exercicio_teste);
			this.listView.setAdapter(new EtapaExercicioAdapter(this, lista_teste));
			*/
			this.listView.setAdapter(new EtapaExercicioAdapter(this, etapaExercicioDao.listarEtapaExercicios(etapaID)));
			this.listView.setOnItemClickListener(this);
			this.listView.setOnItemLongClickListener(this);
		}
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		return false;
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
	    	Log.i("log", "activity AddEtapaExercicio");
	        //chame aqui a activity de adicionar uma etapa ao programa
	    	startActivity(new Intent(this, AddEtapaExercicio.class).putExtra("etapaID", etapaID));
	        return true;
	    case R.id.Opcoes:
	    	//chame aqui a activity de configuracoes
	    	Log.v("log", "activity configuracao");
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
