package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import br.com.fitnessmobile.view.tab.Tab;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Tela inicial - Menu Principal
 * 
 * @version 1.0
 * @author ADEV
 */
public class FitnessMobile extends Activity implements OnClickListener {

	// Botões
	private Button btn_main_medidas;
	private Button btn_main_programa;
	private Button btn_main_exercicio;
	private Button btn_main_configuracao;
	private Button btn_main_teste;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Definir Layout
		setContentView(R.layout.main);

		// Instanciar Views (Componentes da Tela)
		instanciarViews();
	}

	// Instancia os Componentes de Tela e atribui os Eventos as mesmas.
	private void instanciarViews() {
		// Instanciar os Botões
		this.btn_main_programa 		= (Button) findViewById(R.id.btn_main_programa);
		this.btn_main_exercicio 	= (Button) findViewById(R.id.btn_main_exercicio);
		this.btn_main_medidas 		= (Button) findViewById(R.id.btn_main_medidas);
		this.btn_main_configuracao  = (Button) findViewById(R.id.btn_main_conf);
		this.btn_main_teste 		= (Button) findViewById(R.id.btn_main_teste);

		// Eventos On Click Listeners
		this.btn_main_programa.setOnClickListener(this);
		this.btn_main_exercicio.setOnClickListener(this);
		this.btn_main_medidas.setOnClickListener(this);
		this.btn_main_configuracao.setOnClickListener(this);
		this.btn_main_teste.setOnClickListener(this);
	}

	/* Evento dos Botões
	 * Recebe a um Componente de Tela (View) e verifiqua qual é.
	 * O método putExtra() é usado para passar uma identificação de qual botão
	 * foi clicado para aparecer na proxima tela.
	 */
	public void onClick(View v) {
		if (v == btn_main_programa) { startActivity(new Intent(this,Tab.class).putExtra("abaSelecionada", 0)); } // TabPrograma
		else if (v == btn_main_exercicio) { startActivity(new Intent(this,Tab.class).putExtra("abaSelecionada", 1)); } // TabExercicio
		else if (v == btn_main_medidas) { startActivity(new Intent(this,Tab.class).putExtra("abaSelecionada", 2)); } // TabMedidas
		else if (v == btn_main_configuracao) { } // Tela de Configuração
		else if (v == btn_main_teste) { } // Cronometro ?
	}
}