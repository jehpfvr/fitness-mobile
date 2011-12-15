package br.com.fitnessmobile.view;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.adapter.UsuarioListAdapter;
import br.com.fitnessmobile.adapter.enums.UsuarioCampos;
import br.com.fitnessmobile.dao.MedidasDao;
import br.com.fitnessmobile.model.Usuario;

public class MedidasListActivity extends ListActivity  {
	protected static final int INSERIR_EDITAR = 1;
	
	public static MedidasDao usuarioDao;
	
	private List<Usuario> usuarios;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioDao = new MedidasDao(this);
        View header = getLayoutInflater().inflate(R.layout.usuario_lista_header, null);
        ListView listView = getListView();
        listView.addHeaderView(header, null, false);
        
        atualizaLista();
    }
    
    public void atualizaLista() {
    	usuarios = usuarioDao.listarUsuario();
    	
    	setListAdapter(new UsuarioListAdapter(this, usuarios));
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
				startActivityForResult(new Intent(this, TabMedidas.class), INSERIR_EDITAR);
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
    	Intent it = new Intent(this, TabMedidas.class);
    	
    	// Passa o id do programa como par�metro
    	it.putExtra(UsuarioCampos.ID.getCampo(), usuario.getId());
    	
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
