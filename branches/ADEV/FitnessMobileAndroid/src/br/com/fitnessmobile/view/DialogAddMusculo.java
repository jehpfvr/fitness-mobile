package br.com.fitnessmobile.view;

import java.util.ArrayList;
import java.util.EnumSet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.enums.Musculo;
import br.com.fitnessmobile.dao.ExercicioDao;
import br.com.fitnessmobile.model.Exercicio;

public class DialogAddMusculo extends Dialog implements android.view.View.OnClickListener {
	private EditText et_nome_exercicio;
	private Button btn_exercicio_salvar;
	private Button btn_exercicio_cancelar;
	private Button btn_add_musc_sec;
	private Spinner sp_musculo_pri;
	private Spinner sp_tipo;
	private CharSequence musculoPriSelect;
	private CharSequence tipoExerSelect;
	private Context context;
	private CharSequence[] arrayMusculos;
	private ArrayList<String> lista_musculos;
	private ArrayList<CharSequence> selectedMusculo;
	private AlertDialog dialogMusc;
	private boolean[] checkedMusculo ;
	private ExercicioDao exercicioDao;
	Intent intent;
	
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
		exercicioDao = new ExercicioDao(context);
		this.btn_exercicio_salvar = (Button) findViewById(R.id.btn_exercicio_salvar);
		this.btn_exercicio_cancelar = (Button) findViewById(R.id.btn_exercicio_cancelar);
		this.btn_add_musc_sec = (Button) findViewById(R.id.btn_exercicio_add_mus_sec);
		
		this.sp_musculo_pri = (Spinner) findViewById(R.id.exercicio_musc_pri);
		this.sp_tipo = (Spinner) findViewById(R.id.exercicio_tipo);
		
		this.et_nome_exercicio = (EditText) findViewById(R.id.exercicio_nome);
		
		lista_musculos = new ArrayList<String>();
		for (Musculo m : EnumSet.allOf(Musculo.class)) {
			lista_musculos.add(m.getMusculoNome());
		}
		
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
		this.sp_musculo_pri.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				musculoPriSelect = (CharSequence)parent.getItemAtPosition(pos);
			}
			public void onNothingSelected(AdapterView<?> arg0) { }
		});
		
		ArrayAdapter<CharSequence> arrayAdapterTipo = ArrayAdapter.createFromResource(this.context, R.array.array_tipo_exercicio, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		this.sp_tipo.setAdapter(arrayAdapterTipo);
		this.sp_tipo.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				tipoExerSelect = (CharSequence)parent.getItemAtPosition(pos);
			}
			public void onNothingSelected(AdapterView<?> arg0) { }
		});
	}

	
	public void showDialogMusculosSec(){
		checkedMusculo = new boolean[this.lista_musculos.size()]; 
		
		for(int i = 0; i < this.lista_musculos.size(); i++)
			checkedMusculo[i] = this.selectedMusculo.contains(this.lista_musculos.get(i));
		 
		  DialogInterface.OnMultiChoiceClickListener musculoDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
		   public void onClick(DialogInterface dialog, int which, boolean isChecked) {
		      if(isChecked)
		    	  selectedMusculo.add(lista_musculos.get(which));
		      else
		    	  selectedMusculo.remove(lista_musculos.get(which));
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
				Exercicio novoExercicio = new Exercicio();
				novoExercicio.setNome(et_nome_exercicio.getText().toString());
				novoExercicio.setMusculoPrincipal(Musculo.getEnumByNome(musculoPriSelect.toString()));
				if (tipoExerSelect.toString().equals("Aerobico"))
					novoExercicio.setTipo("A");	
				else
					novoExercicio.setTipo("N");
				
				mensagemExercicio = "Novo Exercicio - Nome " + novoExercicio.getNome()
						+ "\n Musc. Principal: " + novoExercicio.getMusculoPrincipal()
						+ "\n Tipo:" + novoExercicio.getTipo()
						+ "\n Muscs. Secs\n";
				ArrayList<Musculo> grupo_muscular = new ArrayList<Musculo>();
				for(CharSequence i: selectedMusculo){
					grupo_muscular.add(Musculo.getEnumByNome(i.toString()));
					mensagemExercicio += Musculo.getEnumByNome(i.toString())+" \n";
				}
				novoExercicio.setGrupoMuscular(grupo_muscular);
				exercicioDao.salvar(novoExercicio);
				this.et_nome_exercicio.setText("");
				this.sp_musculo_pri.setSelection(0);
				this.sp_tipo.setSelection(0);
				
				this.zerarSelecaoMuscSec();
			}
			Toast.makeText(this.context, mensagemExercicio, Toast.LENGTH_LONG).show();
			this.cancel(); // TODO retorna a tela tabexercicio

		}else if(v == btn_exercicio_cancelar){
			Toast.makeText(this.context, "Exercicio Cancelado", Toast.LENGTH_SHORT).show();
			this.cancel();
		}else if(v == btn_add_musc_sec){
			this.showDialogMusculosSec();
		}
	}

	private void zerarSelecaoMuscSec() {
		this.selectedMusculo.clear();
		checkedMusculo = new boolean[this.lista_musculos.size()]; 
		
		for(int i = 0; i < this.lista_musculos.size(); i++)
			checkedMusculo[i] = this.selectedMusculo.contains(this.lista_musculos.get(i));
	}
}