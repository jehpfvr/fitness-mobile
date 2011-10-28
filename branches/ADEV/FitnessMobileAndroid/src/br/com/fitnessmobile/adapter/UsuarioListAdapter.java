package br.com.fitnessmobile.adapter;

import java.util.List;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.model.Usuario;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UsuarioListAdapter extends BaseAdapter{
	
	private Context context;
	private List<Usuario> usuarios;
	
	public UsuarioListAdapter(Context context, List<Usuario> lista) {
		super();
		this.context = context;
		this.usuarios = lista;
	}

	public int getCount() {
		return usuarios.size();
	}

	public Object getItem(int position) {
		return usuarios.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		Usuario u = usuarios.get(position);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.usuario_lista, null);
		
		// Atualiza o valor do TextView

		TextView data = (TextView) view.findViewById(R.id.txvData);
		data.setText(u.getData());
		
		return view;
	}


}
