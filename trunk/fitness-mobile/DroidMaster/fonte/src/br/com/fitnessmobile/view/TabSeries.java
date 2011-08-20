package br.com.fitnessmobile.view;

import java.util.ArrayList;
import java.util.List;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapters.SeriesAdapter;
import br.com.fitnessmobile.model.Exercicio;
import br.com.fitnessmobile.model.ExercicioAerobico;
import br.com.fitnessmobile.model.Musculo;
import br.com.fitnessmobile.model.Serie;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;

public class TabSeries extends Activity implements OnClickListener, OnItemSelectedListener{
	private ListView listView;
	private Button btn_add;
	private Button btn_edit;
	private Button btn_del;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.series);
		
		this.instanciarViews();
		

	}

	private void instanciarViews() {
		
		this.btn_edit = (Button) findViewById(R.id.series_btn_edit);
		this.btn_edit.setOnClickListener(this);
		
		this.btn_del = (Button) findViewById(R.id.series_btn_del);
		this.btn_del.setOnClickListener(this);
		
		this.btn_add = (Button) findViewById(R.id.series_btn_add);
		this.btn_add.setOnClickListener(this);
		
		this.listView = (ListView) findViewById(R.id.series_listview);
		
		Musculo musculo = new Musculo();
		musculo.setImagem(R.drawable.icon);
		musculo.setNome("Peitoral");
		List<Musculo> listMusculo = new ArrayList<Musculo>();
		listMusculo.add(musculo);
		
		Exercicio exercicio = new Exercicio();
		exercicio.setDescricao("Supino Reto");
		exercicio.setGrupoMuscular(listMusculo);
		exercicio.setMusculoPrincipal(musculo);
		exercicio.setNome("Supino Reto");
		
		ExercicioAerobico corrida = new ExercicioAerobico();
		corrida.setDescricao("Corrida");
		corrida.setGrupoMuscular(listMusculo);
		corrida.setMusculoPrincipal(musculo);
		corrida.setNome("Corrida");
		
		List<Exercicio> listExercicio= new ArrayList<Exercicio>();
		listExercicio.add(exercicio);
		listExercicio.add(corrida);
		
		Serie serie = new Serie();
		serie.setNome("Peito e Triceps");
		serie.setExercicio(listExercicio);
		serie.setPeso(70.00);
		serie.setRepeticao(3);
		List<Serie> listSerie = new ArrayList<Serie>();
		listSerie.add(serie);

		
		this.listView.setAdapter(new SeriesAdapter(this, listSerie));
		this.listView.setOnItemSelectedListener(this);
	}

	public void onClick(View v) {
		Button button = (Button) v;
		
		if(button.getText().equals(getString(R.string.Voltar))){
			SeriesAdapter sa =  (SeriesAdapter) this.listView.getAdapter();
			sa.desativarBotoes();
			this.btn_add.setText(R.string.Adicionar);
			this.btn_edit.setText(R.string.Editar);
			this.btn_del.setText(R.string.Excluir);
		}else if(v == btn_edit){
			SeriesAdapter sa =  (SeriesAdapter) this.listView.getAdapter();
			sa.ativarEdit();
			this.btn_add.setText(R.string.Voltar);
			this.btn_edit.setText(R.string.Voltar);
			this.btn_del.setText(R.string.Voltar);
		}else if(v == btn_del){
			SeriesAdapter sa =  (SeriesAdapter) this.listView.getAdapter();
			sa.ativarDel();
			this.btn_add.setText(R.string.Voltar);
			this.btn_edit.setText(R.string.Voltar);
			this.btn_del.setText(R.string.Voltar);
			
		}else if (v == btn_add){
			
		}
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
