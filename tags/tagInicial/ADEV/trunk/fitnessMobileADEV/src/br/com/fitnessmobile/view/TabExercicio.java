package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.ExercicioAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressWarnings("unused")
public class TabExercicio extends Activity implements OnClickListener {
	
	// Componentes da tela
	private ListView exercicio_listView;
	private Spinner exercicio_spinner_grupo_muscular;
	private Dialog exercicio_dialog_add_exerc;
	private Button exercicio_btn_novo_exerc;
	private DialogAddExercicio exercicio_dialog_add;
	
	// Adapter que ir� preencher a tela com Conteudo
	private ExercicioAdapter exercicio_adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Definir Layout
		setContentView(R.layout.exercicios);
		
		// Instanciar Views (Componentes da Tela)
		instanciarViews();
		
		// Instanciar o Adapter
		exercicio_adapter = ExercicioAdapter.getInstance(this);
	}
	
	// Instancia os Componentes de Tela e atribui os Eventos as mesmas.
	private void instanciarViews() {
		this.exercicio_listView = (ListView) findViewById(R.id.list_view_exercicio);
		this.exercicio_spinner_grupo_muscular = (Spinner) findViewById(R.id.sp_grupo_muscular);
		this.exercicio_btn_novo_exerc = (Button) findViewById(R.id.btn_add_exercicio);
		
		// Evento ao Bot�o Novo Exercicio
		this.exercicio_btn_novo_exerc.setOnClickListener(this);
		
		/**
		 * Adapter ao Spinner
		 */
		this.exercicio_spinner_grupo_muscular.setAdapter(ExercicioAdapter.getInstance(this).getArrayAdapterByMusculos());
		
		// M�todo do Spinner para capturar o item selecionado
		this.exercicio_spinner_grupo_muscular.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
 			public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
				// Pega nome pela posi��o
				String nome = parent.getItemAtPosition(position).toString();
				// Imprime um Toast na tela com o nome que foi selecionado
				Toast.makeText(getApplicationContext(), "Filtrando Exercicios por: " + nome, Toast.LENGTH_LONG).show();
				
				// Filtra a Lista de Exercicios contendo s� o Musculo Selecionado
				ExercicioAdapter.getInstance(getApplicationContext()).setListViewFiltro(nome);
				
			}
 			public void onNothingSelected(AdapterView<?> parent) { }
		});
		
		
		/**
		 * Adapter para o ListView
		 */
		exercicio_listView.setAdapter(ExercicioAdapter.getInstance(getApplicationContext()));
		
		// Evento de Clicar uma vez no Exercicio
		this.exercicio_listView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    	// Ao clicar, mostrar um Toast
		    	String nome = parent.getItemAtPosition(position).toString();
		    	Toast.makeText(getApplicationContext(), "Ver detalhes do Exercicio: " + nome +"\nMusculo Principal:" + parent.getAdapter().getItemId(position), Toast.LENGTH_SHORT).show();
		    }
		});
		
		// Evento de Segurar Click
		this.exercicio_listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				// Ao clicar, mostrar um Toast
				String nome = parent.getItemAtPosition(position).toString();
				Toast.makeText(getApplicationContext(), "Editar Exercicio: " + nome, Toast.LENGTH_SHORT).show();
				return false;
		    }
		});
	}

	// Eventos de Bot�o
	public void onClick(View v) {
		if (v == exercicio_btn_novo_exerc) {
			this.showDialog(0);
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		if (id == 0) {
			this.exercicio_dialog_add = new DialogAddExercicio(this,R.string.Exercicio_adicionar);
		}
		return this.exercicio_dialog_add;
	}
	
}