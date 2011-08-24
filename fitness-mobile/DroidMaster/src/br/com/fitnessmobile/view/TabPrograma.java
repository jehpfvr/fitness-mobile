package br.com.fitnessmobile.view;

import java.util.ArrayList;
import java.util.List;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.EtapaAdapter;
import br.com.fitnessmobile.model.Etapa;
import br.com.fitnessmobile.model.Exercicio;
import br.com.fitnessmobile.model.ExercicioAerobico;
import br.com.fitnessmobile.model.Musculo;
import br.com.fitnessmobile.model.Programa;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class TabPrograma extends Activity implements OnItemLongClickListener, OnItemClickListener{
	private final int DIALOG_SELECIONAR = 1;
	private ListView listView;
	private TextView tv_prog_nome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programa);
		this.instanciarViews();

	}

	private void instanciarViews() {
		
		this.tv_prog_nome = (TextView) findViewById(R.id.programa_nome);
		
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
		
		Etapa etapa = new Etapa();
		etapa.setExercicios(listExercicio);
		etapa.setNome("Peito e Corrida");
		
		List<Etapa> listaEtapa = new ArrayList<Etapa>();
		listaEtapa.add(etapa);
		
		Programa programa = new Programa();
		programa.setNome("Hipertrofia");
		programa.setEtapas(listaEtapa);
		
		List<Programa> listSerie = new ArrayList<Programa>();
		listSerie.add(programa);
		
		
		this.tv_prog_nome.setText(programa.getNome());
		
		this.listView.setAdapter(new EtapaAdapter(this, listaEtapa));
		this.listView.setOnItemClickListener(this);
		this.listView.setOnItemLongClickListener(this);
	}

	public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
		final CharSequence[] items = getResources().getTextArray(R.array.array_opcoes);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.Opcoes);
		builder.setItems(items, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int item) {
		        if(item == 0){
		        	showDialog(DIALOG_SELECIONAR);
		        }
		    }
		});
		AlertDialog alert = builder.create();
		alert.show();
		return true;
	}

	public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
		showDialog(DIALOG_SELECIONAR);	
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

}
