package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import android.app.Activity;
import android.os.Bundle;

public class TabPrograma extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Definir Layout
		setContentView(R.layout.programa);
		
		// Instanciar Views (Componentes da Tela)
		instanciarViews();
		
	}
	
	// Instancia os Componentes de Tela e atribui os Eventos as mesmas.
	private void instanciarViews() {
	}
}