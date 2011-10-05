package br.com.fitnessmobile.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.model.*;

public class ExercicioAdapter extends BaseAdapter {
	
	private Context context;
	private List<Exercicio> lista;

	public ExercicioAdapter(Context context, List<Exercicio> lista) {
		super();
		this.context = context;
		this.lista = lista;
	}

	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// Pega um Exercicio da Lista
		Exercicio exercicio = lista.get(position);
		
		// Layout
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.adapter_exercicio_layout, null);
	
		// Nome do Exercicio
		TextView tv_exercicio = (TextView) v.findViewById(R.id.adapter_exercicio_text);
		tv_exercicio.setText(exercicio.getNome());
		
		// Icone do Exercicio
		ImageView exercicio_icone = (ImageView) v.findViewById(R.id.adapter_exercicio_imagem);
		exercicio_icone.setImageResource(exercicio.getMusculoPrincipal().getMusculoIcone());
		return v;
	}
}
