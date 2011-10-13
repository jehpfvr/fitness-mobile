package br.com.fitnessmobile.view;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.dao.EtapaDao;
import br.com.fitnessmobile.model.Etapa;

public class AddEtapa extends Activity implements OnClickListener, android.content.DialogInterface.OnClickListener{
	

	private EditText editview;
	private Button btn_salvar;

	private EtapaDao etapaDao;
	AlertDialog.Builder dlgAlert;
	
	private Integer programaID;
	private String programaNome;
	
	
	Intent intent;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_etapa);
		programaID = getIntent().getIntExtra("programaID", -1);
		programaNome = getIntent().getStringExtra("programaNome");
		this.instanciarViews();
	}
	
	private void instanciarViews() {
		etapaDao = new EtapaDao(this);
		editview = (EditText) findViewById(R.id.etapa_nome);
		btn_salvar = (Button) findViewById(R.id.btn_etapa_salvar);
		btn_salvar.setOnClickListener(this);
		dlgAlert  = new AlertDialog.Builder(this);
		
	}

	public void onClick(View v) {
		if (v == btn_salvar) {
			if (editview.length() >1) {
				Etapa novaEtapa = new Etapa();
				novaEtapa.setNome(editview.getText().toString());
				novaEtapa.setProgramaID(programaID);
				etapaDao.salvar(novaEtapa);
				
				dlgAlert.setMessage("Sua Etapa foi adicionada com sucesso");
				dlgAlert.setTitle("Etapa adicionado");
				dlgAlert.setPositiveButton("OK", this);
				dlgAlert.create().show();
			}
		}
	}
	public void onClick(DialogInterface dialog, int which) {
		Log.i("OnClickDialog","Laço OnClick");
		
		//Pega o evento do click do Alert e chama o menu com a Tab
		if (which == AlertDialog.BUTTON_POSITIVE){
			Log.i("OnClickDialog","EtapaView" );
			intent = new Intent(this,EtapaView.class).putExtra("programaID", programaID).putExtra("programaNome",programaNome);
			startActivity(intent);
		}
	}
}
