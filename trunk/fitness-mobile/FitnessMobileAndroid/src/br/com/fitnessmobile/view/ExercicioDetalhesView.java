package br.com.fitnessmobile.view;

import android.app.Activity;
import android.graphics.Movie;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.model.Exercicio;

public class ExercicioDetalhesView extends Activity {

	private TextView nome;
	private TextView musculo_pri;
	private TextView grupo_musc;
	private TextView desc;
	private TextView indice_calorico;
	private ImageView icone;
	private ImageView gif;
	
	private Exercicio exercicio;
	
	private Movie movie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercicio_detalhes);
		
		exercicio = (Exercicio) getIntent().getSerializableExtra("exercicio");
		
		instanciarViews();
		configurarViews();
	}
	
	
	private void animacao(){
		 
	}
	
	

	private void instanciarViews() {
		this.nome = (TextView) findViewById(R.id.exer_det_textView1);
		this.musculo_pri = (TextView) findViewById(R.id.exer_det_textView3);
		this.grupo_musc = (TextView) findViewById(R.id.exer_det_textView5);
		this.indice_calorico = (TextView) findViewById(R.id.exer_det_textView7);
		this.desc = (TextView) findViewById(R.id.exer_det_textView9);
		this.icone = (ImageView) findViewById(R.id.exer_det_imageView1);
		this.gif = (ImageView) findViewById(R.id.exer_det_imageView2);
	}

	private void configurarViews() {
		this.icone.setImageResource(exercicio.getMusculoPrincipal().getMusculoIcone());
		this.gif.setImageResource(exercicio.getMusculoPrincipal().getMusculoIcone());
		this.nome.setText(exercicio.getNome());
		this.musculo_pri.setText(exercicio.getMusculoPrincipal().getMusculoNome());
		this.grupo_musc.setText(exercicio.getGrupoMuscular().toString());
		this.desc.setText(exercicio.getDescricao());
		this.indice_calorico.setText(String.valueOf(exercicio.getIndiceCalorico()));
	}
}