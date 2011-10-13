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
		
		ImageView img_exercicio = (ImageView) v.findViewById(R.id.imagemExercicioView);
		
		TextView tv_aviso = (TextView) v.findViewById(R.id.avisoExercicioView);
		
		if (etapaExercicio.getFlag().equals(1)) {
			img_exercicio.setVisibility(ImageView.VISIBLE);
			tv_aviso.setVisibility(TextView.GONE);
		}
		else {
			img_exercicio.setVisibility(ImageView.GONE);
			if(etapaExercicio.getExercicio().getTipo().equals("A")){
				tv_aviso.setText(R.string.Marcar_gps);
				tv_aviso.setVisibility(TextView.VISIBLE);
			}else{
				tv_aviso.setText(R.string.Selecionar_exercicio);
				tv_aviso.setVisibility(TextView.VISIBLE);
			}
		}
		return v;
	}
}