package br.com.fitnessmobile.view;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.enums.UsuarioCampos;
import br.com.fitnessmobile.model.Usuario;


public class UsuarioInsertAltera extends Activity implements OnClickListener, OnDateSetListener, android.content.DialogInterface.OnClickListener {
	   
	    static final int DIALOG_DATA = 0;
	    static final int RESULT_SALVAR = 1;

    	
    	// Campos texto
    	private Button btData;
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
    	private DatePickerDialog dialogData;
    	private Long id;
    	private int dialogSelecionado;
    	Intent intent;

    	@Override
    	protected void onCreate(Bundle icicle) {
    		super.onCreate(icicle);

    		setContentView(R.layout.usuario_form);

    		edtPeso = (EditText) findViewById(R.id.edtPeso);
    		edtAltura = (EditText) findViewById(R.id.edtAltura);
    		edtBicepsEsquerdo = (EditText) findViewById(R.id.edtBicepsEsquerdo);
    		edtBicepsDireito = (EditText) findViewById(R.id.edtBicepsDireito);
    		edtTricepsEsquerdo = (EditText) findViewById(R.id.edtTricepsEsquerdo);
    		edtTricepsDireito = (EditText) findViewById(R.id.edtTricepsDireito);
    		edtCintura = (EditText) findViewById(R.id.edtCintura);
    		edtPeitoral = (EditText) findViewById(R.id.edtPeitoral);
    		edtCoxaEsquerdo = (EditText) findViewById(R.id.edtCoxaEsquerda);
    		edtCoxaDireita = (EditText) findViewById(R.id.edtCoxaDireita);
    		edtPanturrilhaEsquerda = (EditText) findViewById(R.id.edtPanturrilhaEsquerda);
    		edtPanturrilhaDireita = (EditText) findViewById(R.id.edtPanturrilhaDireita);
    		edtQuadril = (EditText) findViewById(R.id.edtQuadril);
    		edtData = (EditText) findViewById(R.id.edtData);
    		id = null;
    		
    		this.btData = (Button) findViewById(R.id.add_data_usuario);
    		this.btData.setOnClickListener(this);

    		Bundle extras = getIntent().getExtras();
    		// Se for para Editar, recuperar os valores ...
    		if (extras != null) {
    			id = extras.getLong(UsuarioCampos.ID.getCampo());

    			if (id != null) {
    				// � uma edi��o, busca o programa...
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
    	}

    	@Override
    	protected void onPause() {
    		super.onPause();
    		// Cancela para n�o ficar nada na tela pendente
    		setResult(RESULT_CANCELED);

    		// Fecha a tela
    		finish();
    	}

    	public void salvar() {

    		Usuario usuario = new Usuario();
    		
    		if (id != null) {
    			// � uma atualiza��o
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
    		return TabUsuario.usuarioDao.buscarUsuario(id);
    	}

    	// Salvar o programa
    	protected void salvarUsuario(Usuario usuario) {
    		TabUsuario.usuarioDao.salvar(usuario);
    	}

    	// Excluir o Programa
    	protected void excluirUsuario(long id) {
    		TabUsuario.usuarioDao.excluir(id);
    	}

		public void onClick(DialogInterface dialog, int which) {
			/*Log.i("OnClickDialog","Laço OnClick");
			
			//Pega o evento do click do Alert e chama o menu com a Tab
			if (which == AlertDialog.BUTTON_POSITIVE){
				Log.i("OnClickDialog","TESTE" );
				intent = new Intent(this,FitnessMobileTab.class).putExtra("aba", 2);
				startActivity(intent);
			}*/
			
		}

		public void onDateSet(DatePicker dialog, int year, int month, int day) {
			if(this.dialogSelecionado == DIALOG_DATA){
				this.edtData.setText(String.valueOf(day)+"-"+String.valueOf(dialog.getMonth()+1)+"-"+String.valueOf(year));
				//this.data = new Date(year, month, day);	
			}
		}

		public void onClick(View v) {
			if (v == this.btData){
				this.dialogSelecionado = DIALOG_DATA;
				showDialog(DIALOG_DATA);			
		}
    }
		
		@Override
		protected Dialog onCreateDialog(int id) {
			int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
			int mesAtual = Calendar.getInstance().get(Calendar.MONTH);
			int diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			
			if(id == DIALOG_DATA){
				this.dialogData = new DatePickerDialog(this, this, anoAtual,mesAtual, diaAtual);
				return this.dialogData;
			}			
			return super.onCreateDialog(id);
		}
}