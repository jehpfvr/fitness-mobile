package br.com.fitnessmobile.service;

import java.util.List;

import br.com.fitnessmobile.dao.UsuarioDao;
import br.com.fitnessmobile.model.Usuario;
import br.com.fitnessmobile.model.UsuarioCampo;
import br.com.fitnessmobile.view.TabUsuario;

import br.com.fitnessmobile.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class UsuarioListActivity extends ListActivity  {
	protected static final int INSERIR_EDITAR = 1;
	
	public static UsuarioDao usuarioDao;
	
	private List<Usuario> usuarios;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioDao = new UsuarioDao(this);
        
        View header = getLayoutInflater().inflate(R.layout.usuario_lista_header, null);
        ListView listView = getListView();
        listView.addHeaderView(header, null, false);
        
        atualizaLista();
    }
    
    public void atualizaLista() {
    	usuarios = usuarioDao.listarUsuarios();
    	
    	setListAdapter((ListAdapter) new UsuarioListAdapter(this, usuarios));
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
				// Abre a tela com o formul�rio para adicionar
				startActivityForResult(new Intent(this, TabUsuario.class), INSERIR_EDITAR);
				Toast.makeText(this, "Dados de usuario inserido!", 5);
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
    	Usuario usuario = usuarios.get(posicao);
    	
    	// Cria a intent para abrir a tela de edi��o de programa
    	Intent it = new Intent(this, TabUsuario.class);
    	
    	// Passa o id do programa como par�metro
    	it.putExtra(UsuarioCampo.ID.getCampo(), usuario.getId());
    	
    	// Abre a tela de edi��o
    	startActivityForResult(it, INSERIR_EDITAR);
    }
    
    @Override
    protected void onDestroy() {	
    	super.onDestroy();
    	
    	//Fechar banco
    	usuarioDao.Fechar();
    }

}
