package br.com.fitnessmobile.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.ExercicioAdapter;
import br.com.fitnessmobile.adapter.enums.Musculo;
import br.com.fitnessmobile.controller.Util;
import br.com.fitnessmobile.dao.ExercicioDao;
import br.com.fitnessmobile.model.Exercicio;

public class TabExercicio extends Activity implements OnClickListener {
	
	private ListView listView;
	private Spinner sp_GrupoMuscular;
	private Dialog dialogAddExercicio;
	private Button btn_addExercicio;
	
	private ExercicioDao exercicioDao;
	
	private String nomeFiltro = "Todos"; // Padrão listar todos

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);

		// Definir Layout
		setContentView(R.layout.exercicios);
		
		/// Instanciar Views (Componentes da Tela)
		instanciarViews();
	}

	// Instancia os Componentes de Tela e atribui os Eventos as mesmas.
	private void instanciarViews() {
		exercicioDao = new ExercicioDao(this);
		
		this.listView = (ListView) findViewById(R.id.list_view_exercicio);
		this.sp_GrupoMuscular = (Spinner) findViewById(R.id.sp_grupo_muscular);
		this.btn_addExercicio = (Button) findViewById(R.id.btn_add_exercicio);
		
		// Evento ao Botao Novo Exercicio
		this.btn_addExercicio.setOnClickListener(this);
		
		/**
		 * Adapter ao Spinner
		 */
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.array_filtro_muscular, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.sp_GrupoMuscular.setAdapter(arrayAdapter);
		
		// Metodo do Spinner para capturar o item selecionado
		this.sp_GrupoMuscular.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
 			public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
				// Pega nome pela posicao
 				nomeFiltro = parent.getItemAtPosition(position).toString();
				// Imprime um Toast na tela com o nome que foi selecionado
				Toast.makeText(getApplicationContext(), "Filtrando Exercicios por: " + nomeFiltro, Toast.LENGTH_LONG).show();
				
				// Filtra a Lista de Exercicios contendo so o Musculo Selecionado
				if (nomeFiltro.equals("Todos"))
					listView.setAdapter(new ExercicioAdapter(getApplicationContext(), exercicioDao.listarExercicios()));
				else
					listView.setAdapter(new ExercicioAdapter(getApplicationContext(), exercicioDao.listarExerciciosFiltrados(Musculo.getEnumByNome(nomeFiltro).getMusculoId())));
				
			}
 			public void onNothingSelected(AdapterView<?> parent) { }
		});
		
		/**
		 * Adapter para o ListView
		 */
		listView.setAdapter(new ExercicioAdapter(this, this.exercicioDao.listarExercicios()));
		
		// Evento de Clicar uma vez no Exercicio
		this.listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	// Ao clicar, mostrar um Toast
		    	String nome = parent.getItemAtPosition(position).toString();
		    	Exercicio exercicio_clicado = (Exercicio)parent.getAdapter().getItem(position);
		    	Toast.makeText(getApplicationContext(),
		    			"Ver detalhes do Exercicio: " + nome
		    			+ "\nMusculo Principal: " + exercicio_clicado.getMusculoPrincipal().getMusculoNome()
		    			+ "\nGrupo Musculares: " + exercicio_clicado.getGrupoMuscular().toString()
		    			+ "\nDescricao: " + exercicio_clicado.getDescricao()
		    			, Toast.LENGTH_LONG).show();
		    }
		});
		
		// Evento de Segurar Click
		this.listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// Ao clicar, mostrar um Toast
				String nome = parent.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), "Editar Exercicio: " + nome, Toast.LENGTH_SHORT).show();
				return false;
		    }
		});
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == 0) {
			this.dialogAddExercicio = new DialogAddMusculo(this,R.string.Exercicio_adicionar);
		}
		return this.dialogAddExercicio;
	}

	// Eventos de Botao
	public void onClick(View v) {
		if (v == btn_addExercicio) {
			this.showDialog(0);
		}
	}

	@Override
	protected void onResume() {
		if (nomeFiltro.equals("Todos"))
			this.listView.setAdapter(new ExercicioAdapter(this, exercicioDao.listarExercicios()));
		else
			this.listView.setAdapter(new ExercicioAdapter(this, exercicioDao.listarExerciciosFiltrados(Musculo.getEnumByNome(nomeFiltro).getMusculoId())));
		
		super.onResume();
	}
	
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Fecha o banco
        exercicioDao.Fechar();
    }
}