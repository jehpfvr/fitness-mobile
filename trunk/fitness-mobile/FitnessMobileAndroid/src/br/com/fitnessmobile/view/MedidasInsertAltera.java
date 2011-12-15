package br.com.fitnessmobile.view;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.enums.UsuarioCampos;
import br.com.fitnessmobile.model.Usuario;


public class MedidasInsertAltera extends Activity implements OnClickListener, OnDateSetListener, android.content.DialogInterface.OnClickListener {
	   
	    static final int DIALOG_DATA = 0;
	    static final int RESULT_SALVAR = 1;

    	
    	// Campos texto
    	private Button btData;
    	private EditText edtPeso;
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
    	private TextView edtData;
    	private DatePickerDialog dialogData;
    	private Integer id;
    	private int dialogSelecionado;
    	Intent intent;

    	@Override
    	protected void onCreate(Bundle icicle) {
    		super.onCreate(icicle);
    		setContentView(R.layout.medida_form);

    		this.instanciarViews();
    		
    		Bundle extras = getIntent().getExtras();
    		// Se for para Editar, recuperar os valores ...
    		if (extras != null) {
    			id = extras.getInt(UsuarioCampos.ID.getCampo());

    			if (id != null) {
    				// é uma edição, busca o programa...
    				Usuario u = buscarUsuario(id);
    				
    				edtPeso.setText(String.valueOf(u.getPeso()));
    				edtBicepsEsquerdo.setText(String.valueOf(u.getBicepsEsquerdo()));
    				edtBicepsDireito.setText(String.valueOf(u.getBicepsDireito()));
    				edtTricepsEsquerdo.setText(String.valueOf(u.getTricepsEsquerdo()));
    				edtTricepsDireito.setText(String.valueOf(u.getTricepsDireito()));
    				edtCintura.setText(String.valueOf(u.getCintura()));
    				edtPeitoral.setText(String.valueOf(u.getPeitoral()));
    				edtCoxaEsquerdo.setText(String.valueOf(u.getCoxaEsquerda()));
    				edtCoxaDireita.setText(String.valueOf(u.getCoxaDireita()));
    				edtPanturrilhaEsquerda.setText(String.valueOf(u.getPanturrilhaEsquerda()));
    				edtPanturrilhaDireita.setText(String.valueOf(u.getPanturrilhaDireita()));
    				edtQuadril.setText(String.valueOf(u.getQuadril()));
    				edtData.setText(String.valueOf(u.getData()));
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

    	private void instanciarViews() {
			// TODO Auto-generated method stub
    		edtPeso = (EditText) findViewById(R.id.edtPeso);
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
    		edtData = (TextView) findViewById(R.id.edtData);
    		id = null;
    		
    		this.btData = (Button) findViewById(R.id.add_data_usuario);
    		this.btData.setOnClickListener(this);

		}

		@Override
    	protected void onPause() {
    		super.onPause();
    		// Cancela para n�o ficar nada na tela pendente
    		setResult(RESULT_CANCELED);

    		// Fecha a tela
    		finish();
    	}
		
		public void vibrar() {
			Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

			// Vibrate for 300 milliseconds
			vb.vibrate(300);
		}

    	public void salvar() {

    		if ((edtPeso.getText().toString().equals("") || edtBicepsEsquerdo.getText().toString().equals("")
    				|| edtBicepsDireito.getText().toString().equals("") || edtTricepsEsquerdo.getText().toString().equals("")
    				|| edtTricepsDireito.getText().toString().equals("") || edtCintura.getText().toString().equals("")
    				|| edtPeitoral.getText().toString().equals("") || edtCoxaEsquerdo.getText().toString().equals("")
    				|| edtCoxaDireita.getText().toString().equals("") || edtPanturrilhaEsquerda.getText().toString().equals("")
    				|| edtPanturrilhaDireita.getText().toString().equals("") || edtQuadril.getText().toString().equals("")
    				|| edtData.getText().toString().equals(""))) {

    				Toast.makeText(getApplicationContext(),  "Não podem haver campos nulos.", Toast.LENGTH_SHORT).show();
    				vibrar();
    				return;
    		}
    		else {
    			Usuario usuario = new Usuario();

    			if (id != null) {
    				usuario.setId(id);
    			}

    			usuario.setPeso(Float.parseFloat(edtPeso.getText().toString()));
    			usuario.setBicepsEsquerdo(Float.parseFloat(edtBicepsEsquerdo.getText().toString()));
    			usuario.setBicepsDireito(Float.parseFloat(edtBicepsDireito.getText().toString()));
    			usuario.setTricepsEsquerdo(Float.parseFloat(edtTricepsEsquerdo.getText().toString()));
    			usuario.setTricepsDireito(Float.parseFloat(edtTricepsDireito.getText().toString()));
    			usuario.setCintura(Float.parseFloat(edtCintura.getText().toString()));
    			usuario.setPeitoral(Float.parseFloat(edtPeitoral.getText().toString()));
    			usuario.setCoxaEsquerda(Float.parseFloat(edtCoxaEsquerdo.getText().toString()));
    			usuario.setCoxaDireita(Float.parseFloat(edtCoxaDireita.getText().toString()));
    			usuario.setPanturrilhaEsquerda(Float.parseFloat(edtPanturrilhaEsquerda.getText().toString()));
    			usuario.setPanturrilhaDireita(Float.parseFloat(edtPanturrilhaDireita.getText().toString()));
    			usuario.setQuadril(Float.parseFloat(edtQuadril.getText().toString()));
    			usuario.setData((edtData.getText().toString()));

    			Log.i("Salvando dados", "" + usuario.getId());

    			// Salvar
    			salvarUsuario(usuario);

    			// OK
    			setResult(RESULT_OK, new Intent());

    			// Fecha a tela
    			finish();
    		}
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
    	protected Usuario buscarUsuario(Integer id) {
    		return TabMedidas.usuarioDao.buscarUsuario(id);
    	}

    	// Salvar o programa
    	protected void salvarUsuario(Usuario usuario) {
    		TabMedidas.usuarioDao.salvar(usuario);
    	}

    	// Excluir o Programa
    	protected void excluirUsuario(Integer id) {
    		TabMedidas.usuarioDao.excluir(id);
    	}

		public void onClick(DialogInterface dialog, int which) {		
		}

		public void onDateSet(DatePicker dialog, int year, int month, int day) {
			if(this.dialogSelecionado == DIALOG_DATA){
				this.edtData.setText(String.valueOf(day)+"-"+String.valueOf(dialog.getMonth()+1)+"-"+String.valueOf(year));
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