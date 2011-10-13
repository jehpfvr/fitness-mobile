package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class DialogExercicios extends Dialog{

	public DialogExercicios(Context context) {
		super(context);
		this.setTitle(R.string.Dialog_exer_titulo);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_exercicio);
	}

}
