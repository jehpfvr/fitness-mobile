package br.com.fitnessbd;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ProgramaListActivity extends ListActivity {
	protected static final int INSERIR_EDITAR = 1;
	
	public static ProgramaDao programaDao;
	
	private List<Programa> programas;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        programaDao = new ProgramaDao(this);
        
        View header = getLayoutInflater().inflate(R.layout.programa_lista_header, null);
        ListView listView = getListView();
        listView.addHeaderView(header, null, false);
        
        atualizaLista();
    }
    
    public void atualizaLista() {
    	programas = programaDao.listarProgramas();
    	
    	setListAdapter(new ProgramaListAdapter(this, programas));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	
    	menu.add(0, INSERIR_EDITAR, 0, "Inserir Novo");
    	
    	return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	switch (item.getItemId()) {
			case INSERIR_EDITAR:
				// Abre a tela com o formulário para adicionar
				startActivityForResult(new Intent(this, ProgramaInsereAltera.class), INSERIR_EDITAR);
				Toast.makeText(this, "Programa inserido!", 5);
				break;
    	}
    	
    	return true;
    }
    
    @Override
    protected void onActivityResult(int codigo, int codigoRetorno, Intent it) {
    	super.onActivityResult(codigo, codigoRetorno, it);
    	
    	// Quando a activity retorna atualizar a lista
    	if (codigoRetorno == RESULT_OK) {
    		atualizaLista();
    	}
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int posicao, long id) {
    	super.onListItemClick(l, v, posicao, id);
    	editarPrograma(--posicao);
    }
    
    protected void editarPrograma(int posicao) {
    	// Recupera o programa selecionado
    	Programa programa = programas.get(posicao);
    	
    	// Cria a intent para abrir a tela de edição de programa
    	Intent it = new Intent(this, ProgramaInsereAltera.class);
    	
    	// Passa o id do programa como parâmetro
    	it.putExtra(ProgramaCampos.ID.getCampo(), programa.getId());
    	
    	// Abre a tela de edição
    	startActivityForResult(it, INSERIR_EDITAR);
    }
    
    @Override
    protected void onDestroy() {	
    	super.onDestroy();
    	
    	//Fechar banco
    	programaDao.Fechar();
    }
    
}