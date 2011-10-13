package br.com.fitnessbd;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ProgramaListAdapter extends BaseAdapter {
	
	private Context context;
	private List<Programa> lista;
	
	public ProgramaListAdapter(Context context, List<Programa> lista) {
		super();
		this.context = context;
		this.lista = lista;
	}

	@Override
	public int getCount() {
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Programa p = lista.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.programa_lista, null);
		
		// Atualiza o valor do TextView
		TextView nome = (TextView) view.findViewById(R.id.txvNome);
		nome.setText(p.getNome());
		
		TextView dataInicial = (TextView) view.findViewById(R.id.txvDataInicial);
		dataInicial.setText(p.getDataInicial());
		
		TextView dataFinal = (TextView) view.findViewById(R.id.txvDataFinal);
		dataFinal.setText(p.getDataFinal());
		
		return view;
	}

}
