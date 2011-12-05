package br.com.fitnessmobile.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.controller.Util;

public class Sobre extends Activity {


	private TextView text;
	private ListView list;

	static final String[] Alunos = new String[] {
		"Arthur Azevedo", "Bruno Britto","Daniel Ataíde","Davi Alves","Edkarla Andrade","Gustavo Barbosa","Ivan Pagoto","Orlando Xavier","Paulo Roberto","Rafael Batista", "Thiago de Abreu Lima","Thulio Leitão","Wlysses Chaves", 
	};
	
	static final String[] Twitters = new String[]{
		"@artinfo", "@BrunoBrittoPB","@danieldafonseca","@davi_profile","@Edkarla_Andrade","@gustavocbjunior","@ivanpagoto", "@orlandoxavier","@bitou2k","@rafaelbbenedito","@thiago_alima","@thuliolf","@wlyssesUli",
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.inicioActivitySetTema(this);
		setContentView(R.layout.sobre);

		this.instanciarViews();
		this.configurarViews();
	}

	private void configurarViews() {
		// TODO Auto-generated method stub
		text.setText("\n  Fitness Mobile - O seu personal Trainer! \n" +
				" Projeto de conclusão de curso da turma \n " +
				" ADS 2009.1 da faculdade iDEZ. \n" +
				"\n" +
				"Integrantes:");

		list.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, Alunos));
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Toast.makeText(getApplicationContext(),Twitters[position] ,
						Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void instanciarViews() {
		// TODO Auto-generated method stub
		text = (TextView)findViewById(R.id.textView1);
		list = (ListView)findViewById(R.id.listView1);

	}



	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
