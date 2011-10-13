package br.com.fitnessmobile.view;


import br.com.fitnessmobile.R;
import br.com.fitnessmobile.dao.UsuarioDao;
import br.com.fitnessmobile.model.Usuario;
import br.com.fitnessmobile.model.UsuarioCampo;

import br.com.fitnessmobile.service.UsuarioListActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class TabUsuario extends Activity{
	static final int RESULT_SALVAR = 1;
	static final int RESULT_EXCLUIR = 2;
	
	public UsuarioDao usuarioDao;
	public Usuario usuario;

	// Campos texto
	private EditText edtPeso;
	private EditText edtAltura;
	private EditText edtBicepsEsquerdo;
	private EditText edtBicepsDireito;
	private EditText edtTricepsEsquerdo;
	private EditText edtTricepsDireito;
	private EditText edtCintura;
	private EditText edtPeitoral;
	private EditText edtCoxaEsquerdo;
	private EditText edtCoxaDireita;
	private EditText edtPanturrilhaEsquerda;
	private EditText edtPanturrilhaDireita;
	private EditText edtQuadril;
	private EditText edtData;
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.usuario_form);

		edtPeso = (EditText) findViewById(R.id.edtPeso);
		edtAltura = (EditText) findViewById(R.id.edtAltura);
		edtBicepsEsquerdo = (EditText) findViewById(R.id.edtBicepsEsquerdo);

		id = null;

		Bundle extras = getIntent().getExtras();
		// Se for para Editar, recuperar os valores ...
		if (extras != null) {
			id = extras.getLong(UsuarioCampo.ID.getCampo());

			if (id != null) {
				// é uma edição, busca o programa...
				Usuario u = buscarUsuario(id);
				
				edtPeso.setText(u.getPeso());
				edtAltura.setText(u.getAltura());
				edtBicepsEsquerdo.setText(u.getBicepsEsquerdo());
				edtBicepsDireito.setText(u.getBicepsDireito());
				edtTricepsEsquerdo.setText(u.getTricepsEsquerdo());
				edtTricepsDireito.setText(u.getTricepsDireito());
				edtCintura.setText(u.getCintura());
				edtPeitoral.setText(u.getPeitoral());
				edtCoxaEsquerdo.setText(u.getCoxaEsquerda());
				edtCoxaDireita.setText(u.getCoxaDireita());
				edtPanturrilhaEsquerda.setText(u.getPanturrilhaEsquerda());
				edtPanturrilhaDireita.setText(u.getPanturrilhaDireita());
				edtQuadril.setText(u.getQuadril());
				edtData.setText(u.getData());
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

		Usuario usuario = new Usuario();
		
		if (id != null) {
			// É uma atualização
			usuario.setId(id);
		}
		usuario.setPeso(edtPeso.getText().toString());
		usuario.setAltura(edtAltura.getText().toString());
		usuario.setBicepsEsquerdo(edtBicepsEsquerdo.getText().toString());
		usuario.setBicepsDireito(edtBicepsDireito.getText().toString());
		usuario.setTricepsEsquerdo(edtTricepsEsquerdo.getText().toString());
		usuario.setTricepsDireito(edtTricepsDireito.getText().toString());
		usuario.setCintura(edtCintura.getText().toString());
		usuario.setPeitoral(edtPeitoral.getText().toString());
		usuario.setCoxaEsquerda(edtCoxaEsquerdo.getText().toString());
		usuario.setCoxaDireita(edtCoxaDireita.getText().toString());
		usuario.setPanturrilhaEsquerda(edtPanturrilhaEsquerda.getText().toString());
		usuario.setPanturrilhaDireita(edtPanturrilhaDireita.getText().toString());
		usuario.setQuadril(edtQuadril.getText().toString());
		usuario.setData(edtData.getText().toString());
		
		// Salvar
		salvarUsuario(usuario);

		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}

	public void excluir() {
		if (id != null) {
			excluirUsuario(id);
		}

		// OK
		setResult(RESULT_OK, new Intent());

		// Fecha a tela
		finish();
	}

	// Buscar o programa pelo id
	protected Usuario buscarUsuario(long id) {
		return UsuarioListActivity.usuarioDao.buscarUsuario(id);
	}

	// Salvar o programa
	protected void salvarUsuario(Usuario usuario) {
		UsuarioListActivity.usuarioDao.salvar(usuario);
	}

	// Excluir o Programa
	protected void excluirUsuario(long id) {
		UsuarioListActivity.usuarioDao.excluir(id);
	}
}
