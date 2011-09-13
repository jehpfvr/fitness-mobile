package br.com.fitnessmobile.view;

import br.com.fitnessmobile.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

public class DialogAddExercicio extends Dialog {

	public DialogAddExercicio(Context context, int title) {
		super(context);
		this.setTitle(title);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_exercicio);
	}
}