package br.com.fitnessmobile.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
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
import br.com.fitnessmobile.dao.EtapaExercicioDao;
import br.com.fitnessmobile.dao.ExercicioDao;
import br.com.fitnessmobile.model.Exercicio;

public class TabExercicio extends Activity {

	private ListView listView;
	private Spinner sp_GrupoMuscular;
	private Button btn_addExercicio;
	private ExercicioDao exercicioDao;
	private Context _context;
	private String nomeFiltro = "Todos"; // Padrao listar todos
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this._context = this;

		// Definir Layout
		setContentView(R.layout.exercicios);

		/// Instanciar Views (Componentes da Tela)
		instanciarViews();
		
		
	}

	// Instancia os Componentes de Tela e atribui os Eventos as mesmas.
	private void instanciarViews() {
		this.exercicioDao = new ExercicioDao(this);
		this.listView = (ListView) findViewById(R.id.list_view_exercicio);
		this.sp_GrupoMuscular = (Spinner) findViewById(R.id.sp_grupo_muscular);
		this.btn_addExercicio = (Button) findViewById(R.id.btn_add_exercicio);

		// Evento ao Botao Novo Exercicio
		this.btn_addExercicio.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (v == btn_addExercicio) {
					startActivity(new Intent(getApplicationContext(),AddExercicio.class));
				}
			}
		});

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
				Toast.makeText(getApplicationContext(), "Filtrando Exercicios por: " + nomeFiltro, Toast.LENGTH_SHORT).show();

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
				Exercicio exercicio_clicado = (Exercicio)parent.getAdapter().getItem(position);
				startActivity(new Intent(getApplicationContext(),ExercicioDetalhesView.class).putExtra("exercicio", exercicio_clicado));

				//String nome = parent.getItemAtPosition(position).toString();
				/*Toast.makeText(getApplicationContext(),
		    			"Ver detalhes do Exercicio: " + nome
		    			+ "\nMusculo Principal: " + exercicio_clicado.getMusculoPrincipal().getMusculoNome()
		    			+ "\nGrupo Musculares: " + exercicio_clicado.getGrupoMuscular().toString()
		    			+ "\nDescricao: " + exercicio_clicado.getDescricao()
		    			, Toast.LENGTH_LONG).show();
				 */
			}
		});
		
		

		// Evento de Segurar Click
		this.listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// Ao clicar, mostrar um Toast
				final CharSequence[] items = getResources().getTextArray(R.array.array_opcoes);

				final Exercicio exercicio_selecionado = (Exercicio)parent.getAdapter().getItem(position);

				AlertDialog.Builder builder = new AlertDialog.Builder(_context);
				builder.setTitle(R.string.Opcoes);
				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int pos) {
						if(pos == 0) {
							Log.i("Exercicio", "Atualizar");

						}
						else if (pos == 1){
							Log.i("Exercicio", "Excluir");
							if (exercicio_selecionado.getSituacao().equals("A")) {
								Toast.makeText(_context, "Impossível excluir Exercícios da Aplicação.", 500).show();
								vibrar();
								return;
							}
							remover(exercicio_selecionado.getId()); // TODO Validacoes
						}
					}
				});
				AlertDialog alert = builder.create();
				alert.show();

				return true;
			}
		});
	}
	public void remover(long id) {
		EtapaExercicioDao etapaExercicioDao = new EtapaExercicioDao(this);
		int count = etapaExercicioDao.getCountExercicioByID(id);
		etapaExercicioDao.Fechar();
		if (count > 0) {
			Toast.makeText(this, "Impossível excluir. Este exercício está em uso.", 500).show();
			vibrar();
			return;
		}
		else {
			exercicioDao.excluir(id);
			Toast.makeText(this, "Exercicio excluído com sucesso.", 500).show();
			onResume();
		}
	}

	public void vibrar() {
		Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		// Vibrate for 300 milliseconds
		vb.vibrate(300);
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