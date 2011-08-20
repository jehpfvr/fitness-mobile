package br.com.fitnessmobile.adapters;

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
		Exercicio exercicio = lista.get(position);
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.adapter_musculo_layout, null);
		
		TextView tv_exercicio = (TextView) v.findViewById(R.id.adapter_exercicio_text);
		tv_exercicio.setText(exercicio.getNome());
		
		ImageView iv_musculo = (ImageView) v.findViewById(R.id.adapter_exercicio_imagem);
		iv_musculo.setImageResource(exercicio.getMusculoPrincipal().getImagem());
		
		return v;
	}

}
