package br.com.fitnessmobile.adapter;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.dao.Banco;
import br.com.fitnessmobile.model.Exercicio;
import br.com.fitnessmobile.model.Musculo;

@SuppressWarnings("unused")
public class ExercicioAdapter extends BaseAdapter {
	
	// Duvida: Manter ExercicioAdapter no padr�o Singleton?
	private static ExercicioAdapter _instance;

	private List<String> adapter_lista_musculos;
	private List<Exercicio> adapter_lista_exercicios;
	private Banco adapter_banco;
	private Context context;
	private String adapter_listview_filtro;
	
	public static ExercicioAdapter getInstance(Context context) {
		if (_instance == null) {
			_instance = new ExercicioAdapter(context);
		}
		return _instance;
	}
	
	public ExercicioAdapter(Context context) {
		this.context = context;
		this.adapter_listview_filtro = "Musculo Um";
		
		// Instanciar Listas
		adapter_lista_musculos = new ArrayList<String>();
		adapter_lista_exercicios = new ArrayList<Exercicio>();
		
		// Preencher Lista de Musculos
		for (Musculo m : EnumSet.allOf(Musculo.class)) {
			adapter_lista_musculos.add(m.getMusculoNome());
		}
		// Preencher Lista de Exercicios
		/**
		 *  TODO: Abrir uma conex�o com o Banco
		 *  Carrega a tabela de Exercicios do Banco
		 *  Transforma cada linha da tabela de Exercicios em um objeto Exercicio
		 *  Adiciona todos os objetos no ArrayList de Exercicios  
		 *  Fecha conex�o com o Banco
		 */
		adapter_banco = new Banco(); // N�o us�vel ainda.
		
		// Para Teste (Tinha que vir do banco) **
		ArrayList<Musculo> grupos_musculares = new ArrayList<Musculo>();
		grupos_musculares.add(Musculo.MUSCULO_DOIS);
		grupos_musculares.add(Musculo.MUSCULO_TRES);
		Exercicio exercicio_um = new Exercicio(1,"Supino Puley", "Supino", Musculo.MUSCULO_UM, grupos_musculares);
		Exercicio exercicio_dois = new Exercicio(2,"Supino Reto", "Supino Reto", Musculo.MUSCULO_UM, grupos_musculares);
		Exercicio exercicio_tres = new Exercicio(3,"Rosca", "Bra�o", Musculo.MUSCULO_TRES, grupos_musculares);
		for (int i = 0; i < 5; i++) {
			adapter_lista_exercicios.add(exercicio_um);
			adapter_lista_exercicios.add(exercicio_dois);
			adapter_lista_exercicios.add(exercicio_tres);
		}
		// Fim
	}
	
	public List<Exercicio> getAdapterListaExercicioByFiltro() {
		List<Exercicio> lista_filtrada = new ArrayList<Exercicio>();
		
		// TODO: Criar uma nova lista filtrando a principal
		lista_filtrada = adapter_lista_exercicios;
		
		return lista_filtrada;
	}
	public void setListViewFiltro(String novo_filtro) {
		this.adapter_listview_filtro = novo_filtro;
	}

	public ArrayAdapter<String> getArrayAdapterByMusculos() {
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, this.adapter_lista_musculos);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		return arrayAdapter;
	}
	
	// M�todos do BaseAdapter
	// Ser� usado para Listar cada Item do ListView de Exercicios

	public int getCount() {
		return adapter_lista_exercicios.size();
	}

	public Exercicio getItem(int position) {
		return adapter_lista_exercicios.get(position);
	}

	public long getItemId(int position) {
		return adapter_lista_exercicios.get(position).getExercicioId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// Pega um Exercicio da Lista
		Exercicio exercicio = getAdapterListaExercicioByFiltro().get(position);
		
		// Atribui Layout ao Exercicio
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View adapter_exercicio_view = inflate.inflate(R.layout.adapter_musculo_layout, null);
	
		// Nome do Exercicio
		TextView adapter_exercicio_textview = (TextView) adapter_exercicio_view.findViewById(R.id.adapter_exercicio_text);
		adapter_exercicio_textview.setText(exercicio.getExercicioNome());
		
		// Icone do Exercicio
		ImageView adapter_exercicio_icone = (ImageView) adapter_exercicio_view.findViewById(R.id.adapter_exercicio_imagem);
		adapter_exercicio_icone.setImageResource(exercicio.getExercicioMusculoPrincipal().getMusculoIcone());
		return adapter_exercicio_view;
	}
	// Fim
}