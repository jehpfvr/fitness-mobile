package br.com.fitnessmobile.view;


import java.util.ArrayList;
import br.com.fitnessmobile.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DialogAddMusculo extends Dialog implements android.view.View.OnClickListener, OnItemSelectedListener{
	private EditText et_nome_exercicio;
	private Button btn_exercicio_salvar;
	private Button btn_exercicio_cancelar;
	private Button btn_add_musc_sec;
	private Spinner sp_musculo_pri;
	private CharSequence musculoPriSelect;
	private Context context;
	private CharSequence[] arrayMusculos;
	private ArrayList<CharSequence> selectedMusculo;
	private AlertDialog dialogMusc;
	private boolean[] checkedMusculo ;
	
	public DialogAddMusculo(Context context, int title) {
		super(context);
		this.context = context;
		this.setTitle(title);
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_exercicio);
		
		this.instanciarViews();
		this.configurarViews();
		
	}

	private void instanciarViews() {
		this.btn_exercicio_salvar = (Button) findViewById(R.id.btn_exercicio_salvar);
		this.btn_exercicio_cancelar = (Button) findViewById(R.id.btn_exercicio_cancelar);
		this.btn_add_musc_sec = (Button) findViewById(R.id.btn_exercicio_add_mus_sec);
		
		this.sp_musculo_pri = (Spinner) findViewById(R.id.exercicio_musc_pri);
		
		this.et_nome_exercicio = (EditText) findViewById(R.id.exercicio_nome);
		
		this.arrayMusculos = this.context.getResources().getTextArray(R.array.array_grupo_muscular);
		this.selectedMusculo = new ArrayList<CharSequence>();
	}
	
	private void configurarViews() {
		this.btn_exercicio_salvar.setOnClickListener(this);
		this.btn_exercicio_cancelar.setOnClickListener(this);
		this.btn_add_musc_sec.setOnClickListener(this);
		
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.context, R.array.array_grupo_muscular, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		this.sp_musculo_pri.setAdapter(arrayAdapter);
		this.sp_musculo_pri.setOnItemSelectedListener(this);
	}

	
	public void showDialogMusculosSec(){
		checkedMusculo = new boolean[this.arrayMusculos.length]; 
		
		for(int i = 0; i < this.arrayMusculos.length; i++)
			checkedMusculo[i] = this.selectedMusculo.contains(this.arrayMusculos[i]);
		 
		  DialogInterface.OnMultiChoiceClickListener musculoDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
		   public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		      if(isChecked)
		    	  selectedMusculo.add(arrayMusculos[which]);
		      else
		    	  selectedMusculo.remove(arrayMusculos[which]);
		    }
		   };
		 
		  AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
		  builder.setTitle(R.string.Exercicio_adicionar_musc_sec);
		  builder.setMultiChoiceItems(arrayMusculos, checkedMusculo, musculoDialogListener);
		  builder.setPositiveButton(R.string.Selecionar, this.criarListenerDialog());
		 
		  dialogMusc = builder.create();
		  dialogMusc.show();
	}
		
	private OnClickListener criarListenerDialog() {
		OnClickListener listener = new OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, "musculos selecionados", Toast.LENGTH_SHORT).show();
			}
		};
		return listener;
	}


	public void onClick(View v) {
		String mensagemExercicio = null;
		if(v == btn_exercicio_salvar){
			if(!selectedMusculo.isEmpty()){
				mensagemExercicio = "nome: "+et_nome_exercicio.getText()+" \n musc. principal: "+musculoPriSelect+" musculo secs ";
				for(CharSequence i: selectedMusculo){
					mensagemExercicio += i+"\n";
				}
				this.et_nome_exercicio.setText("");
				this.sp_musculo_pri.setSelection(0);
				this.zerarSelecaoMuscSec();
			}
			Toast.makeText(this.context, mensagemExercicio, Toast.LENGTH_LONG).show();
			this.cancel();

		}else if(v == btn_exercicio_cancelar){
			Toast.makeText(this.context, "Exercicio Cancelado", Toast.LENGTH_SHORT).show();
			this.cancel();
		}else if(v == btn_add_musc_sec){
			this.showDialogMusculosSec();
		}
		
	}

	private void zerarSelecaoMuscSec() {
		this.selectedMusculo.clear();
		checkedMusculo = new boolean[this.arrayMusculos.length]; 
		
		for(int i = 0; i < this.arrayMusculos.length; i++)
			checkedMusculo[i] = this.selectedMusculo.contains(this.arrayMusculos[i]);
		
	}


	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		this.musculoPriSelect = (CharSequence)parent.getItemAtPosition(pos);

	}


	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
