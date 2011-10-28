package br.com.fitnessmobile.view;

import java.util.Calendar;
import java.util.Date;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EtapaExercicioAdapter;
import br.com.fitnessmobile.dao.EtapaExercicioDao;
import br.com.fitnessmobile.model.EtapaExercicio;

public class ExercicioView extends Activity implements OnItemLongClickListener, OnItemClickListener, OnClickListener {
	
	private ListView listView;
	private Button btn_exercicio_salvar;
	private Integer etapaID;
	private Integer diaID;
	private EtapaExercicioDao etapaExercicioDao; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.exercicio);
		etapaID = getIntent().getIntExtra("etapaID", -1);
		diaID = getIntent().getIntExtra("diaID", -1);
		this.instanciarViews();
	}

	private void instanciarViews() {
		etapaExercicioDao = new EtapaExercicioDao(this);
		this.listView = (ListView) findViewById(R.id.exercicio_listview);
		this.btn_exercicio_salvar = (Button) findViewById(R.id.add_exercicio_salvar);
		this.btn_exercicio_salvar.setOnClickListener(this);
		
		if(!etapaID.equals(-1)){
			Log.i("fitness", "Listando Exercicios da Etapa com ID " + etapaID + " do dia com ID " + diaID);
			this.listView.setAdapter(new EtapaExercicioAdapter(this, etapaExercicioDao.listarEtapaExerciciosByDay(etapaID,diaID)));
			this.listView.setOnItemClickListener(this);
			this.listView.setOnItemLongClickListener(this);
		}
	}

	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
		
		// nao dar baixa em exercicio que nao for do dia atual
		if (diaID != EtapaView.getIntDay()) { // TODO: adicionar aos casos de uso
			Toast.makeText(getApplicationContext(), "So e possivel dar Baixa dos Exercicios da Data Atual.", Toast.LENGTH_LONG).show();
			return;
		}
		
		EtapaExercicio etapaExercicio = (EtapaExercicio) adapter.getAdapter().getItem(pos);
		
		if(etapaExercicio.getExercicio().getTipo().equals("N")){
			if(etapaExercicio.getFlag().equals(1))
				etapaExercicio.setFlag(0);
			else
				etapaExercicio.setFlag(1);
		}else if(etapaExercicio.getExercicio().getTipo().equals("A")){
			if(etapaExercicio.getFlag().equals(1))
				etapaExercicio.setFlag(0);
			else{
				etapaExercicio.setFlag(1);
				startActivityForResult(new Intent(this, ExercicioAerobicoView.class).putExtra("etapaExercicioID", etapaExercicio.getDiaID()), 0);
			}
		}
		((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
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
	    	startActivity(new Intent(this, AddEtapaExercicio.class).putExtra("etapaID", etapaID).putExtra("diaID", diaID));
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

	public void onClick(View v) {
		if(btn_exercicio_salvar == v){
			if (diaID != EtapaView.getIntDay()) { // TODO: adicionar aos casos de uso
				Toast.makeText(getApplicationContext(), "So e possivel dar Baixa dos Exercicios da Data Atual.", Toast.LENGTH_LONG).show();
				return;
			}
			
			ListAdapter list = this.listView.getAdapter();
			for(int c=0;c<=list.getCount()-1;c++){
				EtapaExercicio exercicio = (EtapaExercicio) list.getItem(c);
				Date dataAtual = new Date(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				exercicio.setDtBaixa(dataAtual.getTime());
				etapaExercicioDao.salvar(exercicio);
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == 1 && requestCode == 0){
			String result = "Distancia: "+ data.getDoubleExtra("distancia", 0) +"/n"+"Tempo: "+ new Date(data.getLongExtra("tempo", 0)).toString()+
					        "Velocidade: "+data.getDoubleExtra("velocidade", 0);

			// TODO: Criar AerobicoDao, criar Enum Aerobico Campos e salvar essas informações no BD
			
			Toast.makeText(this, result, Toast.LENGTH_LONG).show();
		}
	}
}
