package br.com.fitnessmobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.model.EtapaExercicio;

public class EtapaExercicioAdapter extends BaseAdapter{
	
	private Context context;
	private List<EtapaExercicio> lista;

	public EtapaExercicioAdapter(Context context, List<EtapaExercicio> lista) {
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
		EtapaExercicio etapaExercicio = lista.get(position);
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View v = inflate.inflate(R.layout.adapter_exercicio_view, null);
		
		TextView tv_nomeExercicio = (TextView) v.findViewById(R.id.textExercicioView);
		tv_nomeExercicio.setText(etapaExercicio.getExercicio().getNome());
		
		CheckBox ch_exercicio = (CheckBox) v.findViewById(R.id.checkBoxExercicioView);
		if (etapaExercicio.getFlag().equals(1)) {
			ch_exercicio.setChecked(true);
		}
		else {
			ch_exercicio.setChecked(false);
		}
		return v;
	}
}