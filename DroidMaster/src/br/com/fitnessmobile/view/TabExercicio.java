package br.com.fitnessmobile.view;


import java.util.ArrayList;
import java.util.List;

import br.com.fitnessmobile.R;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import br.com.fitnessmobile.adapter.ExercicioAdapter;
import br.com.fitnessmobile.model.*;

public class TabExercicio extends Activity implements OnClickListener {
	
	private ListView listView;
	private Musculo musculo;
	private Exercicio exercicio;
	private Spinner sp_GrupoMuscular;
	private Dialog dialogAddExercicio;
	private Button btn_addExercicio;
	private final int id_dialog_musc_pri = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicios);
		
		this.instanciarViews();
		
		musculo.setImagem(R.drawable.icon);
		
		
		exercicio.setNome("Supino Reto");
		exercicio.setMusculoPrincipal(musculo);
		
		List<Exercicio> listExercicio = new ArrayList<Exercicio>();
		listExercicio.add(exercicio);
		
		
		this.listView = (ListView) findViewById(R.id.list_view_exercicio);
		this.listView.setAdapter(new ExercicioAdapter(this, listExercicio));
		
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.array_grupo_muscular, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.sp_GrupoMuscular.setAdapter(arrayAdapter);
		
	}

	private void instanciarViews() {
		this.musculo = new Musculo();
		this.exercicio = new Exercicio();
		this.sp_GrupoMuscular = (Spinner) findViewById(R.id.sp_grupo_muscular);
		this.btn_addExercicio = (Button) findViewById(R.id.btn_add_exercicio);
		this.btn_addExercicio.setOnClickListener(this);
		
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		this.dialogAddExercicio = new DialogAddMusculo(this,R.string.Exercicio_adicionar);
		return this.dialogAddExercicio;
	}
	

	public void onClick(View v) {
		this.showDialog(id_dialog_musc_pri);
		
	}


	
}
