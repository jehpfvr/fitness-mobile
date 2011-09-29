package br.com.fitnessbd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ProgramaInsereAltera extends Activity {
	static final int RESULT_SALVAR = 1;
	static final int RESULT_EXCLUIR = 2;

	// Campos texto
	private EditText edtNome;
	private EditText edtDataInicial;
	private EditText edtDataFinal;
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.programa_form);

		edtNome = (EditText) findViewById(R.id.edtNome);
		edtDataInicial = (EditText) findViewById(R.id.edtDataInicial);
		edtDataFinal = (EditText) findViewById(R.id.edtDataFinal);

		id = null;

		Bundle extras = getIntent().getExtras();
		// Se for para Editar, recuperar os valores ...
		if (extras != null) {
			id = extras.getLong(ProgramaCampos.ID.getCampo());

			if (id != null) {
				// é uma edição, busca o programa...
				Programa p = buscarPrograma(id);
				
				edtNome.setText(p.getNome());
				edtDataInicial.setText(p.getDataInicial());
				edtDataFinal.setText(String.valueOf(p.getDataFinal()));
			}
		}

		Button btnCancelar = (Button) findViewById(R.id.btnCancelar);
		btnCancelar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				setResult(RESULT_CANCELED);
				// Fecha a tela
				finish();
			}
		});

		// Listener para salvar o programa
		Button btnSalvar = (Button) findViewById(R.id.btnSalvar);
		btnSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				salvar();
			}
		});

		//Button btnExcluir = (Button) findViewById(R.id.btExcluir);

//		if (id == null) {
//			// Se id está nulo, não pode excluir
//			btnExcluir.setVisibility(View.INVISIBLE);
//		} else {
//			// Listener para excluir o programa
//			btnExcluir.setOnClickListener(new OnClickListener() {
//				public void onClick(View view) {
//					excluir();
//				}
//			});
//		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Cancela para não ficar nada na tela pendente
		setResult(RESULT_CANCELED);

		// Fecha a tela
		finish();
	}

	public void salvar() {

		Programa programa = new Programa();
		
		if (id != null) {
			// É uma atualização
			programa.setId(id);
		}
		programa.setNome(edtNome.getText().toString());
		programa.setDataInicial(edtDataInicial.getText().toString());
		programa.setDataFinal(edtDataFinal.getText().toString());

		// Salvar
		salvarPrograma(programa);

		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}

	public void excluir() {
		if (id != null) {
			excluirPrograma(id);
		}

		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}

	// Buscar o programa pelo id
	protected Programa buscarPrograma(long id) {
		return ProgramaListActivity.programaDao.buscarPrograma(id);
	}

	// Salvar o programa
	protected void salvarPrograma(Programa programa) {
		ProgramaListActivity.programaDao.salvar(programa);
	}

	// Excluir o Programa
	protected void excluirPrograma(long id) {
		ProgramaListActivity.programaDao.excluir(id);
	}
}
