package br.com.fitnessmobile.view;

import controller.ControladorModoVisualizacao;
import br.com.fitnessmobile.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TabModoVisualizacao extends Activity implements OnClickListener {

	private TextView tvAlterarModoVisualizacao;
	private Button btnModoDiurno;
	private Button btnModoNoturno;
	private ControladorModoVisualizacao controlador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecionar_modo_de_visualizacao);

		this.tvAlterarModoVisualizacao = (TextView) findViewById(R.string.Modo_de_visualizacao);
		this.btnModoDiurno  = (Button) findViewById(R.id.btnModoVisualizacaoDiurno);
		this.btnModoNoturno = (Button) findViewById(R.id.btnModoVisualizacaoNoturno);
		this.btnModoDiurno.setOnClickListener(this);
		this.btnModoNoturno.setOnClickListener(this);

	}
		
	public void onClick(View view) {
		
		if (view.getId() == R.id.btnModoVisualizacaoDiurno) {
			controlador.setModoVisualizacao("Diurno");
		} else if (view.getId() == R.id.btnModoVisualizacaoNoturno) {
			controlador.setModoVisualizacao("Noturno");
		}
	}
	
}