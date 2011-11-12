package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.AddEtapaExercicioAdapter;
import br.com.fitnessmobile.controller.Util;
import br.com.fitnessmobile.dao.EtapaExercicioDao;
import br.com.fitnessmobile.dao.ExercicioDao;
import br.com.fitnessmobile.model.EtapaExercicio;
import br.com.fitnessmobile.model.Exercicio;

public class AddEtapaExercicio extends Activity  {
	
	private TextView textView;
	private ListView listView;
	private Integer etapaID;
	private Integer diaID;

	private EtapaExercicioDao etapaExercicioDao;
	private ExercicioDao exercicioDao;
	
	private int ultimoID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.etapa);
		this.etapaID = getIntent().getIntExtra("etapaID", -1);
		this.diaID = getIntent().getIntExtra("diaID", -1);
		this.instanciarViews();
	}

	private void instanciarViews() {
		this.textView = (TextView) findViewById(R.id.etapa_textview);
		this.textView.setText("Adicionar Exercicios a Etapa");

		this.listView = (ListView) findViewById(R.id.etapa_listview);
		if (!etapaID.equals(-1)) {
			this.exercicioDao = new ExercicioDao(this);
			this.listView.setAdapter(new AddEtapaExercicioAdapter(this, this.exercicioDao.listarExercicios()));
			this.exercicioDao.Fechar();
			
			this.etapaExercicioDao = new EtapaExercicioDao(this);
			
			
			
			// Evento de Clicar uma vez no Exercicio
			this.listView.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			    	Exercicio exercicio_clicado = (Exercicio)parent.getAdapter().getItem(position);
			    	
			    	EtapaExercicio etapaExercicio = new EtapaExercicio();
			    	etapaExercicio.setExercicio(exercicio_clicado);
			    	etapaExercicio.setEtapaid(etapaID);
			    	etapaExercicio.setDiaID(diaID);
			    	etapaExercicio.setFlag(0); // Exercicio nao feito
			    	Log.i("Tipo do Exercicio",""+exercicio_clicado.getTipo());
			    	
			    	if (exercicio_clicado.getTipo().equals("A")) {
			    		// Pegar o ultimo ID inserido na tabela Aerobico e passar:
			    		etapaExercicio.setTipoID(0); // ID do ultimo Aerobico inserido
				    	etapaExercicioDao.salvar(etapaExercicio);
				    	etapaExercicioDao.Fechar();
				    	voltar();
			    	}
			    	else{
			    		etapaExercicio.setTipoID(1);  // ID do ultimo Anaerobico inserido
			    		etapaExercicioDao.salvar(etapaExercicio);
			    		ultimoID = etapaExercicioDao.getUltimoIDInsert();
				    	etapaExercicioDao.Fechar();
			    		iniciar_anaerobico();
			    		}
			    	

			    }
			});
		}
	}
	private void iniciar_anaerobico() {
		startActivity(new Intent(this, ExercicioAnaerobicoView.class).putExtra("etapaID", etapaID).putExtra("etapaExercicioID", ultimoID).putExtra("diaID", diaID));
		Log.i("Finish Tela", "Iniciando ExercicioAnaerobicoView e finalizando tela");
		this.finish();
	}
	private void voltar() {
		this.finish();
    	//startActivity(new Intent(this, ExercicioViewTab.class).putExtra("etapaID", etapaID).putExtra("diaID", diaID));
	}
}
