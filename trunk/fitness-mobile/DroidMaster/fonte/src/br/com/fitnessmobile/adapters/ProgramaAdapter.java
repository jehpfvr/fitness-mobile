package br.com.fitnessmobile.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.model.*;

public class ProgramaAdapter extends BaseAdapter implements OnClickListener {
	
	private Button btn_edit;
	private Button btn_del;
	
	private Context context;
	private List<Programa> lista;
	
	private List<View> listaView;

	public ProgramaAdapter(Context context, List<Programa> lista) {
		super();
		this.context = context;
		this.lista = lista;
		this.listaView = new ArrayList<View>();
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
		Programa serie = lista.get(position);
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.adapter_programa_layout, null);
		
		this.btn_edit = (Button) v.findViewById(R.id.serie_adp_edit);
		this.btn_edit.setOnClickListener(this);
		
		this.btn_del = (Button) v.findViewById(R.id.serie_adp_del);
		this.btn_del.setOnClickListener(this);
		
		TextView tv_exercicio = (TextView) v.findViewById(R.id.adapter_exercicio_text);
		tv_exercicio.setText(serie.getNome());
		
		ImageView iv_musculo = (ImageView) v.findViewById(R.id.adapter_exercicio_imagem);
		iv_musculo.setImageResource(android.R.drawable.ic_lock_idle_alarm);
		
		
		this.listaView.add(v);
		
		return v;
	}

	public void onClick(View v) {
			
			Log.v("posição", "testando");

	
		
	}
	
	public void ativarEdit(){
		for(View v: this.listaView){
			Button edit = (Button) v.findViewById(R.id.serie_adp_edit);
			edit.setVisibility(View.VISIBLE);
			
			Button del = (Button) v.findViewById(R.id.serie_adp_del);
			del.setVisibility(View.GONE);
		}
	}
	
	public void ativarDel(){
		for(View v: this.listaView){
			Button edit = (Button) v.findViewById(R.id.serie_adp_edit);
			edit.setVisibility(View.GONE);
			
			Button del = (Button) v.findViewById(R.id.serie_adp_del);
			del.setVisibility(View.VISIBLE);
		}
	}
	
	public void desativarBotoes(){
		for(View v: this.listaView){
			Button edit = (Button) v.findViewById(R.id.serie_adp_edit);
			edit.setVisibility(View.GONE);
			
			Button del = (Button) v.findViewById(R.id.serie_adp_del);
			del.setVisibility(View.GONE);
		}
	}

}
