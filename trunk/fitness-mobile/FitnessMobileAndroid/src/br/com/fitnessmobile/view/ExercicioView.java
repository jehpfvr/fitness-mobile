package br.com.fitnessmobile.view;

import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
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
		this.listView = (ListView) findViewById(R.id.exercicio_listview);
		listView.setCacheColorHint(0);
		this.btn_exercicio_salvar = (Button) findViewById(R.id.add_exercicio_salvar);
		this.btn_exercicio_salvar.setOnClickListener(this);
		
		if(!etapaID.equals(-1)){
			Log.i("fitness", "Listando Exercicios da Etapa com ID " + etapaID + " do dia com ID " + diaID);
			this.etapaExercicioDao = new EtapaExercicioDao(this);
			this.listView.setAdapter(new EtapaExercicioAdapter(this, etapaExercicioDao.listarEtapaExerciciosByDay(etapaID,diaID)));
			this.listView.setOnItemClickListener(this);
			this.listView.setOnItemLongClickListener(this);
			this.etapaExercicioDao.Fechar();
		}
		
	}
	
	public void vibrar() {
		Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		// Vibrate for 300 milliseconds
		vb.vibrate(300);
	}

	public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
		
		// nao dar baixa em exercicio que nao for do dia atual
		if (diaID != EtapaView.getIntDay()) { // TODO: adicionar aos casos de uso
			Toast.makeText(getApplicationContext(), "Só e possivel dar Baixa dos Exercicios da Data Atual.", Toast.LENGTH_LONG).show();
			vibrar();
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
				etapaExercicioDao = new EtapaExercicioDao(this);
				etapaExercicioDao.salvar(etapaExercicio);
				etapaExercicioDao.Fechar();
				this.escolherModoAerobico(etapaExercicio);
				//startActivityForResult(new Intent(this, ExercicioAerobicoView.class).putExtra("etapaExercicioID", etapaExercicio.getId()), 0);
			}
		}
		((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
	}

	private void escolherModoAerobico(final EtapaExercicio etapaExercicio) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);  
		builder.setMessage(R.string.Perg_Modo_Aero)  
		     .setCancelable(false)  
		     .setPositiveButton(R.string.Manual,  
		          new DialogInterface.OnClickListener(){  
		          public void onClick(DialogInterface dialog, int id){  
		        	  Intent intent = new Intent(ExercicioView.this, ExercicioAerobicoManual.class);
		        	  intent.putExtra("etapaExercicioID", etapaExercicio.getId());
		        	  intent.putExtra("indiceCalorico", etapaExercicio.getExercicio().getIndiceCalorico());
		        	 startActivity(intent);
		          }  
		     });  
		     builder.setNegativeButton(R.string.Modo_Corrida,  
		          new DialogInterface.OnClickListener(){  
		          public void onClick(DialogInterface dialog, int id){ 
		        	  Intent intent = new Intent(ExercicioView.this, ExercicioAerobicoView.class);
		        	  intent.putExtra("etapaExercicioID", etapaExercicio.getId());
		        	  intent.putExtra("indiceCalorico", etapaExercicio.getExercicio().getIndiceCalorico());
		             startActivity(intent);
		          }  
		     });  
		AlertDialog alert = builder.create();  
		alert.show();  
		
	}

	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
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
	    case R.id.Home:
	    	Log.v("log", "activity Main");
	    	this.finish();
	    	startActivity(new Intent("fitnessmobile.home"));
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
			etapaExercicioDao = new EtapaExercicioDao(this);
			ListAdapter list = this.listView.getAdapter();
			for(int c=0;c<=list.getCount()-1;c++){
				EtapaExercicio exercicio = (EtapaExercicio) list.getItem(c);
				Date dataAtual = new Date(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
				exercicio.setDtBaixa(dataAtual.getTime());
				etapaExercicioDao.salvar(exercicio);	
			}
			Toast.makeText(this, "Exercicíos salvos!", 500).show();
			etapaExercicioDao.Fechar();
		}
	}
	
	
	@Override
	protected void onResume() {
		etapaExercicioDao = new EtapaExercicioDao(this);
		this.listView.setAdapter(new EtapaExercicioAdapter(this, etapaExercicioDao.listarEtapaExerciciosByDay(etapaID,diaID)));
		etapaExercicioDao.Fechar();
		super.onResume();
	}
}