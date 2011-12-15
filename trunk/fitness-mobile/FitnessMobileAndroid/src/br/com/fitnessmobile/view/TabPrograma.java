package br.com.fitnessmobile.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.ProgramaAdapter;
import br.com.fitnessmobile.dao.ProgramaDao;
import br.com.fitnessmobile.model.Programa;

public class TabPrograma extends Activity {

	private ListView listView;
	private ProgramaDao programaDao;
	private Intent intent;
	private Programa programa_selecionado;
	private Context _context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.programa);
		this._context = this;
		this.instanciarViews();
		this.configurarViews();
	}

	private void instanciarViews() {
		this.programaDao = new ProgramaDao(this);
		this.listView = (ListView) findViewById(R.id.series_listview);
	}

	private void configurarViews() {
		this.listView.setAdapter(new ProgramaAdapter(this, this.programaDao.listarProgramas()));

		this.listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View v, int pos, long id) {
				Programa programa = (Programa) adapter.getAdapter().getItem(pos);
				Intent intent = new Intent(_context, EtapaView.class);
				intent.putExtra("programaID", programa.getId());
				intent.putExtra("programaNome", programa.getNome());
				intent.putExtra("programaDtInicio", programa.getDataInicio());
				intent.putExtra("programaDtFim", programa.getDataFim());
				Log.i("passando parametros", "programa dt inicio" + programa.getDataInicio());
				Log.i("passando parametros", "programa dt fim" + programa.getDataFim());
				startActivity(intent);

			}
		});

		this.listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> adapter, View v, int pos, long id) {
				final CharSequence[] items = getResources().getTextArray(R.array.array_opcoes);

				programa_selecionado = (Programa)adapter.getAdapter().getItem(pos);

				AlertDialog.Builder builder = new AlertDialog.Builder(_context);
				builder.setTitle(R.string.Opcoes);
				builder.setItems(items, new OnClickListener() {
					public void onClick(DialogInterface dialog, int pos) {
						if(pos == 0) {
							Log.i("Programa", "Atualizar");
							intent = new Intent(_context,EditarPrograma.class).putExtra("Programa", programa_selecionado.getId());
							startActivity(intent);
						}
						else if (pos == 1){
							Log.i("Programa","Excluir");

							remover(); // TODO Validacoes

							Toast.makeText(_context, "Seu programa foi excluido", 500).show();
							startActivity(new Intent(getApplicationContext(),FitnessMobileTab.class).putExtra("aba", 0));
						}
					}
				});
				AlertDialog alert = builder.create();
				alert.show();

				return true;
			}

		});
	}

	public void remover() { // TODO: Colocar validacoes
		programaDao.excluir(programa_selecionado.getId());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_default, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Novo:
			Log.i("log", "activity AddPrograma");
			//chame aqui a activity de adicionar um programa
			startActivity(new Intent(this, AddPrograma.class));
			return true;
		case R.id.Opcoes:
			//chame aqui a activity de configuracoes
			Log.v("log", "activity configuracao");
			startActivity(new Intent(this, Configuracao.class));
			return true;
		case R.id.Home:
			Log.v("log", "activity Main");
			startActivity(new Intent("fitnessmobile.home"));
			this.finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		this.listView.setAdapter(new ProgramaAdapter(this, programaDao.listarProgramas()));
		super.onResume();
	}
}