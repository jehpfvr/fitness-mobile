package br.com.fitnessmobile.service;

import java.util.List;

import br.com.fitnessmobile.model.Usuario;

import br.com.fitnessmobile.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UsuarioListAdapter {
	
	private Context context;
	private List<Usuario> lista;
	
	public UsuarioListAdapter(Context context, List<Usuario> lista) {
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
		Usuario u = lista.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.usuario_lista, null);
		
		// Atualiza o valor do TextView

		TextView data = (TextView) view.findViewById(R.id.txvData);
		data.setText(u.getData());
		
		return view;
	}


}

