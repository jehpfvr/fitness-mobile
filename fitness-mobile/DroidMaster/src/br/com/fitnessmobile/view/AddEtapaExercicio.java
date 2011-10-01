package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.AddEtapaExercicioAdapter;
import br.com.fitnessmobile.dao.EtapaExercicioDao;
import br.com.fitnessmobile.dao.ExercicioDao;
import br.com.fitnessmobile.model.EtapaExercicio;
import br.com.fitnessmobile.model.Exercicio;

public class AddEtapaExercicio extends Activity  {
	
	private TextView textView;
	private ListView listView;
	private Integer etapaID;

	private EtapaExercicioDao etapaExercicioDao;
	private ExercicioDao exercicioDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.etapa);
		this.etapaID = getIntent().getIntExtra("etapaID", -1);
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
			    	etapaExercicio.setFlag(0); // Exercicio nao feito
			    	if (exercicio_clicado.getTipo() == "A") {
			    		//AerobicoDao aerobicoDao = new AerobicoDao();
			    		//Aerobico aerobico = new Aerobico();
			    		// TODO: Abrir dialog para preencher Duracao, Distancia e Velocidade
			    		// Pegar o ultimo ID inserido na tabela Aerobico e passar:
			    		etapaExercicio.setTipoID(0); // ID do ultimo Aerobico inserido
			    	}
			    	else
			    		etapaExercicio.setTipoID(1);  // ID do ultimo Anaerobico inserido
			    	
			    	etapaExercicioDao.salvar(etapaExercicio);
			    	etapaExercicioDao.Fechar();
			    	voltar();
			    }
			});
		}
	}
	private void voltar() {
    	startActivity(new Intent(this, ExercicioView.class).putExtra("etapaID", etapaID));
	}
}
