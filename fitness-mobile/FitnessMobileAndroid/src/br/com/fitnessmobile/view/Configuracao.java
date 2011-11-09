package br.com.fitnessmobile.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.fitnessmobile.R;

public class Configuracao extends Activity implements OnClickListener{
	
	private Button btn_Modo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuracao);
		
		this.instanciarViews();
	}

	private void instanciarViews() {
		// TODO Auto-generated method stub
		this.btn_Modo = (Button)findViewById(R.id.btn_Modo);
		btn_Modo.setOnClickListener(this);
		
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.equals(btn_Modo)){
			startActivity(new Intent(this, ModoVisualizacao.class));
		}
		
	}

}
