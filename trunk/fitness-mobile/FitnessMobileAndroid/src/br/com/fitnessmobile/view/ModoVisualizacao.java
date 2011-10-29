package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;

public class ModoVisualizacao extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.selecionar_modo_de_visualizacao);

		findViewById(R.id.btnModoVisualizacaoDiurno).setOnClickListener(this);
		findViewById(R.id.btnModoVisualizacaoNoturno).setOnClickListener(this);
	}

	public void onClick(View v) {

		if (v.getId() == R.id.btnModoVisualizacaoDiurno) {
			switch (Util.sTheme) {
			case 0:
				Context context3 = getApplicationContext();
				CharSequence text3 = "O Tema diurno já está sendo utilizado";
				int duration3 = Toast.LENGTH_SHORT;

				Toast toast3 = Toast.makeText(context3, text3, duration3);
				toast3.show();
				break;

			case 1:
				Context context1 = getApplicationContext();
				CharSequence text1 = "Tema alterado com sucesso";
				int duration1 = Toast.LENGTH_SHORT;

				Toast toast1 = Toast.makeText(context1, text1, duration1);
				toast1.show();

				Util.mudarParaTema(this, Util.TEMA_DIA);
				
				break;
			}
		}
		if (v.getId() == R.id.btnModoVisualizacaoNoturno) {
			switch (Util.sTheme) {
			case 0:
				Context context2 = getApplicationContext();
				CharSequence text2 = "Tema alterado com sucesso";
				int duration2 = Toast.LENGTH_SHORT;

				Toast toast2 = Toast.makeText(context2, text2, duration2);
				toast2.show();

				Util.mudarParaTema(this, Util.TEMA_NOITE);
		        
				break;
			case 1:
				Context context4 = getApplicationContext();
				CharSequence text4 = "O Tema noturno já está sendo utilizado";
				int duration4 = Toast.LENGTH_SHORT;

				Toast toast4 = Toast.makeText(context4, text4, duration4);
				toast4.show();
			}

		}
	}
}