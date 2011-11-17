package br.com.fitnessmobile.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.model.EstaExercicio;

public class EstaExerAdapter extends BaseAdapter {

	private Context context;
	private List<EstaExercicio> lista;
	
	public EstaExerAdapter(Context context, List<EstaExercicio> lista) {
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
		EstaExercicio estaExercicio = lista.get(position);
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View v = inflate.inflate(R.layout.adapter_esta_exer_layout, null);
		
		TextView tv_nomeExercicio = (TextView) v.findViewById(R.id.esta_exer_textView1);
		tv_nomeExercicio.setText(estaExercicio.getNomeExercicio());
		
		TextView tv_qt = (TextView) v.findViewById(R.id.esta_exer_textview2);
		tv_qt.setText("("+estaExercicio.getFeitos()+"/"+estaExercicio.getTotal()+")");
		
		ProgressBar progresso = (ProgressBar) v.findViewById(R.id.esta_exer_progressBar1);
		progresso.setMax(estaExercicio.getTotal());
		progresso.setProgress(estaExercicio.getFeitos());
		
		TextView tv_faltam = (TextView) v.findViewById(R.id.esta_exer_textView3);
		Integer restante = estaExercicio.getTotal() - estaExercicio.getFeitos();
		if (restante == 0)
			tv_faltam.setText("Nenhum exercicio pendente.");
		else if (restante == 1)
			tv_faltam.setText("Falta "+restante+" exercicio.");
		else 
			tv_faltam.setText("Faltam "+restante+" exercicios.");
		return v;
	}
}