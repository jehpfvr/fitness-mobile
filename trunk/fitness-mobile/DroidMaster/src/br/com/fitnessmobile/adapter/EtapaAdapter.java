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

public class EtapaAdapter extends BaseAdapter{
	
	private Context context;
	private List<Etapa> lista;

	public EtapaAdapter(Context context, List<Etapa> lista) {
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
		Etapa etapa = lista.get(position);
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View v = inflate.inflate(R.layout.adapter_default_layout, null);
		
		TextView tv_etapa = (TextView) v.findViewById(R.id.adapter_default_text);
		tv_etapa.setText(etapa.getNome());
		
		return v;
	}

}
