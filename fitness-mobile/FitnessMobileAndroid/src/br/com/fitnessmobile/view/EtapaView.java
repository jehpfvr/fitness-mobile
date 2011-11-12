package br.com.fitnessmobile.view;

import java.util.Calendar;
import java.util.Date;

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
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EtapaAdapter;
import br.com.fitnessmobile.controller.Util;
import br.com.fitnessmobile.dao.EtapaDao;
import br.com.fitnessmobile.model.Etapa;

public class EtapaView extends Activity implements OnItemClickListener, OnItemLongClickListener, OnLongClickListener,DialogInterface.OnClickListener{
	private TextView textView;
	private TextView tvDtProg;
	private ListView listView;
	private Integer programaID;
	private String programaNome;
	private long programaDtInicio;
	private long programaDtFim;
	private EtapaDao etapaDao;
	private Etapa etapa_selecionada;
	private Intent intent;
	AlertDialog.Builder dlgAlert;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.etapa);
		
		programaID = getIntent().getIntExtra("programaID", -1);
		programaNome = getIntent().getStringExtra("programaNome");
		programaDtInicio = getIntent().getLongExtra("programaDtInicio", -1);
		programaDtFim = getIntent().getLongExtra("programaDtFim", -1);
		
		this.instanciarView();
	}
	
	private void instanciarView() {
		
		Calendar dtInicio = Calendar.getInstance();
		dtInicio.setTimeInMillis(programaDtInicio);
		
		Calendar dtFim = Calendar.getInstance();
		dtFim.setTimeInMillis(programaDtFim);
		
		this.etapaDao = new EtapaDao(this);
		this.textView = (TextView) findViewById(R.id.etapa_textview);
		this.textView.setText("Etapas do Programa: " + programaNome);
		this.tvDtProg = (TextView) findViewById(R.id.Dtetapa_textview);
		
		int monthInicio = dtInicio.get(Calendar.MONTH)+1;
		int monthFim = dtFim.get(Calendar.MONTH)+1;
		
		this.tvDtProg.setText("De: "
				+dtInicio.get(Calendar.DAY_OF_MONTH)+"/"+monthInicio+"/"+dtInicio.get(Calendar.YEAR)+" "
				+ "Ate: "
				+dtFim.get(Calendar.DAY_OF_MONTH)+"/"+monthFim+"/"+dtFim.get(Calendar.YEAR));
		
		this.listView = (ListView) findViewById(R.id.etapa_listview);
		this.dlgAlert = new AlertDialog.Builder(this);
		
		if(!programaID.equals(-1)){
			Log.i("fitness", "Listando Etapas do Programa com ID " + programaID);
			this.listView.setAdapter(new EtapaAdapter(this, etapaDao.listarEtapaByProgramaID(programaID)));
			this.listView.setOnItemClickListener(this);
			this.listView.setOnItemLongClickListener(this);
		}
		etapaDao.Fechar();
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
		//Pegar dia de Hj e transformar em um int de 0 a 6 (seg a dom)
		startActivity(new Intent(this, ExercicioViewTab.class).putExtra("etapaID", etapa_clicada.getId()).putExtra("diaID", getIntDay()));
	}
	
	public static int getIntDay() {
		Calendar cal = Calendar.getInstance();  
		cal.setTime(new Date());
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int dayInt = -2;
		switch (day) {
		    case Calendar.MONDAY: // segunda
		    	dayInt = 0;
		        break;
		    case Calendar.TUESDAY:  
		    	dayInt = 1;
		        break;
		    case Calendar.WEDNESDAY:  
		    	dayInt = 2;  
		        break;
		    case Calendar.THURSDAY:
		    	dayInt = 3;  
		        break;
		    case Calendar.FRIDAY:  
		    	dayInt = 4;  
		        break;
		    case Calendar.SATURDAY:  
		    	dayInt = 5;  
		        break;
		    case Calendar.SUNDAY:  
		    	dayInt = 6;  
		        break;
		    default:
		    	dayInt = -2;
		        break;  
		}  
		return dayInt;
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
	    	startActivity(new Intent(this, AddEtapa.class).putExtra("programaID", programaID).putExtra("programaNome", programaNome).putExtra("programaDtInicio", programaDtInicio).putExtra("programaDtFim", programaDtFim));
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
		this.etapaDao = new EtapaDao(this);
		this.listView.setAdapter(new EtapaAdapter(this, etapaDao.listarEtapaByProgramaID(programaID)));
		this.etapaDao.Fechar();
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
		this.etapaDao = new EtapaDao(this);
		etapaDao.excluir(etapa_selecionada.getId());
		this.listView.setAdapter(new EtapaAdapter(this, etapaDao.listarEtapaByProgramaID(programaID)));
		this.etapaDao.Fechar();
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
			Log.i("Etapa", "Atualizar");
			
			intent = new Intent(this,EditarEtapa.class).putExtra("programaID", etapa_selecionada.getId());
			Log.i("Visualizando etapa com id", etapa_selecionada.getId().toString());
			startActivity(intent);
			
		}
		else if (arg1 == 2){
			Log.i("Etapa","Excluir");
			removerEtapa();
			
			Toast.makeText(this, "Sua etapa foi excluida", 500).show();
			
	}
  
  }
}
		