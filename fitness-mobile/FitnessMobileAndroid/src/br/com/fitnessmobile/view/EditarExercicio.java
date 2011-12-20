package br.com.fitnessmobile.view;

import java.util.ArrayList;
import java.util.EnumSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.enums.Musculo;
import br.com.fitnessmobile.dao.ExercicioDao;
import br.com.fitnessmobile.model.Exercicio;

public class EditarExercicio extends Activity implements android.view.View.OnClickListener {
	private EditText et_nome_exercicio;
	private EditText et_indice_calorico;
	private Button btn_exercicio_salvar;
	private Button btn_exercicio_cancelar;
	private Button btn_add_musc_sec;
	private Spinner sp_musculo_pri;
	private Spinner sp_tipo;
	private TextView tv_indice_calorico;
	private CharSequence musculoPriSelect;
	private CharSequence tipoExerSelect;
	private CharSequence[] arrayMusculos;
	private ArrayList<String> lista_musculos;
	private ArrayList<CharSequence> selectedMusculo;
	private AlertDialog dialogMusc;
	private boolean[] checkedMusculo;
	private ExercicioDao exercicioDao;
	Intent intent;
	private int exercicioId;
	Exercicio exercicio;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_exercicio);
		exercicioId = getIntent().getIntExtra("Exercicio",-1);
		Log.i("Recebido exercicio do id",""+exercicioId);
		this.instanciarViews();
		this.configurarViews();
	}

	private void instanciarViews() {
		exercicioDao = new ExercicioDao(this);
		exercicio = exercicioDao.buscarExercicio(exercicioId);
		
		this.btn_exercicio_salvar = (Button) findViewById(R.id.btn_exercicio_salvar);
		this.btn_exercicio_cancelar = (Button) findViewById(R.id.btn_exercicio_cancelar);
		this.btn_add_musc_sec = (Button) findViewById(R.id.btn_exercicio_add_mus_sec);
		this.tv_indice_calorico = (TextView) findViewById(R.id.tv_indice_calorico);

		this.sp_musculo_pri = (Spinner) findViewById(R.id.exercicio_musc_pri);
		this.sp_tipo = (Spinner) findViewById(R.id.exercicio_tipo);

		this.et_indice_calorico = (EditText) findViewById(R.id.exercicio_indicecalorico);
		this.et_nome_exercicio = (EditText) findViewById(R.id.exercicio_nome);

		lista_musculos = new ArrayList<String>();
		for (Musculo m : EnumSet.allOf(Musculo.class)) {
			lista_musculos.add(m.getMusculoNome());
		}

		this.arrayMusculos = this.getResources().getTextArray(R.array.array_grupo_muscular);
		this.selectedMusculo = new ArrayList<CharSequence>();
	}

	private void configurarViews() {
		btn_exercicio_salvar.setOnClickListener(this);
		btn_exercicio_cancelar.setOnClickListener(this);
		btn_add_musc_sec.setOnClickListener(this);

		//Preencher os valores do Banco na tela
		this.et_nome_exercicio.setText(exercicio.getNome());
		String indice = String.valueOf(exercicio.getIndiceCalorico()); 
		this.et_indice_calorico.setText(indice);
		
		
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.array_grupo_muscular, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		this.sp_musculo_pri.setAdapter(arrayAdapter);
		this.sp_musculo_pri.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				musculoPriSelect = (CharSequence)parent.getItemAtPosition(pos);
			}
			public void onNothingSelected(AdapterView<?> adapter) { }
		});

		ArrayAdapter<CharSequence> arrayAdapterTipo = ArrayAdapter.createFromResource(this, R.array.array_tipo_exercicio, android.R.layout.simple_spinner_item);
		arrayAdapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		this.sp_tipo.setAdapter(arrayAdapterTipo);
		this.sp_tipo.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				tipoExerSelect = (CharSequence)parent.getItemAtPosition(pos);

				String tipo = tipoExerSelect.toString();

				if(tipo.equals("Aerobico")){
					et_indice_calorico.setVisibility(View.VISIBLE);
					tv_indice_calorico.setVisibility(View.VISIBLE);
				}
				else{ 
					et_indice_calorico.setVisibility(View.GONE);
					tv_indice_calorico.setVisibility(View.GONE);
				}
			}
			public void onNothingSelected(AdapterView<?> adapter) { }
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

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.Exercicio_adicionar_musc_sec);
		builder.setMultiChoiceItems(arrayMusculos, checkedMusculo, musculoDialogListener);
		builder.setPositiveButton(R.string.Selecionar, sp_musculo_pri );

		dialogMusc = builder.create();
		dialogMusc.show();
	}


	public void onClick(View v) {
		String mensagemExercicio = null;
		String TipoExe = null;
		if(v == btn_exercicio_salvar){
			validarCampos();
			
			if (musculoPriSelect == null) return;
			if (et_indice_calorico.getText() == null) return;

			if(!selectedMusculo.isEmpty()){
				Exercicio novoExercicio = new Exercicio();
				novoExercicio.setNome(et_nome_exercicio.getText().toString());
				novoExercicio.setMusculoPrincipal(Musculo.getEnumByNome(musculoPriSelect.toString()));
				if (tipoExerSelect.toString().equals("Aerobico")){
					novoExercicio.setTipo("A");	
					novoExercicio.setIndiceCalorico(Float.parseFloat(et_indice_calorico.getText().toString()));
				}else
					novoExercicio.setTipo("N");
				novoExercicio.setSituacao("C");//Exercicio Custom
				novoExercicio.setDescricao("Exercício adicionado pelo usuário"); //Descrição default para exercicios custom

				if(novoExercicio.getTipo() == "A")TipoExe = "Aerobico";
				else TipoExe = "Anaerobico";
				mensagemExercicio = "Novo Exercicio " 
						+ "\n Nome: " + novoExercicio.getNome()
						+ "\n Musc. Principal: " + novoExercicio.getMusculoPrincipal()
						+ "\n Tipo: " + TipoExe
						+ "\n Muscs. Secs\n";
				ArrayList<Musculo> grupo_muscular = new ArrayList<Musculo>();
				for(CharSequence i: selectedMusculo){
					grupo_muscular.add(Musculo.getEnumByNome(i.toString()));
					mensagemExercicio += Musculo.getEnumByNome(i.toString())+" \n";
				}
				novoExercicio.setGrupoMuscular(grupo_muscular);
				exercicioDao.atualizarExercicio(novoExercicio);
				this.et_nome_exercicio.setText("");
				this.sp_musculo_pri.setSelection(0);
				this.sp_tipo.setSelection(0);

				this.zerarSelecaoMuscSec();
				Toast.makeText(this, mensagemExercicio, Toast.LENGTH_LONG).show();
				//startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 2));
				this.finish();
			}

		}else if(v == btn_exercicio_cancelar){
			Toast.makeText(this, "Exercicio Cancelado", Toast.LENGTH_SHORT).show();
			this.finish();
			startActivity(new Intent(this,FitnessMobileTab.class).putExtra("aba", 1));

		}else if(v == btn_add_musc_sec){
			this.showDialogMusculosSec();
		}
	}

	public void vibrar() {
		Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		// Vibrate for 300 milliseconds
		vb.vibrate(300);
	}

	private void validarCampos() {

		if(et_nome_exercicio.getText().length() == 0){
			Toast.makeText(this, "Informe o nome do exercicio!", 500).show();
			vibrar();
			return;
		}
		if(tipoExerSelect.toString().equals("Aerobico")){
			if(et_indice_calorico.getText().length() == 0){
				Toast.makeText(this, "Informe o índice calórico!", 500).show();
				vibrar();
				return;
			}
		}
		if(selectedMusculo.isEmpty()){
			Toast.makeText(this, "Selecione pelo menos um musculo secundário!", 500).show();
			vibrar();
			return;
		}
	}

	private void zerarSelecaoMuscSec() {
		this.selectedMusculo.clear();
		checkedMusculo = new boolean[this.lista_musculos.size()]; 

		for(int i = 0; i < this.lista_musculos.size(); i++)
			checkedMusculo[i] = this.selectedMusculo.contains(this.lista_musculos.get(i));
	}
}