package br.com.fitnessmobile.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.model.*;

public class ProgramaAdapter extends BaseAdapter{
	
	private Context context;
	private List<Programa> lista;

	public ProgramaAdapter(Context context, List<Programa> lista) {
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
		Programa programa = lista.get(position);
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View v = inflate.inflate(R.layout.adapter_default_layout, null);
		
		TextView tv_etapa = (TextView) v.findViewById(R.id.adapter_default_text);
		tv_etapa.setText(programa.getNome());
		
		return v;
	}

}
