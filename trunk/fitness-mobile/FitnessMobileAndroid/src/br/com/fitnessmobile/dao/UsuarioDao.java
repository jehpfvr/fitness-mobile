package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.UsuarioCampos;
import br.com.fitnessmobile.model.Usuario;

public class UsuarioDao {
	
	private static final String CATEGORIA = "fitness";
	
	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS usuario";
	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table usuario ( _id integer primary key autoincrement, peso text not null, altura text not null, biceps_esquerdo text not null, biceps_direito text not null, triceps_esquerdo text not null, triceps_direito text not null, cintura text not null, peitoral text not null, coxa_esquerda text not null, coxa_direita text not null, panturrilha_esquerda text not null, panturrilha_direita text not null, quadril text not null, data text not null);"};
			
	// Nome do banco
	private static final String NOME_BANCO = "Mobilefitness";
	
	// Controle de versão
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String NOME_TABELA = "usuario";

	protected SQLiteDatabase db;
	
	// Classe utilitária para abrir, criar, e atualizar o banco de dados
	SQLiteHelper dbHelper;
	
	
	// Cria o banco de dados com um script SQL
	public UsuarioDao(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, UsuarioDao.NOME_BANCO, UsuarioDao.VERSAO_BANCO,
				UsuarioDao.SCRIPT_DATABASE_CREATE, UsuarioDao.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar também
		db = dbHelper.getWritableDatabase();
	}
	
	// Fecha o banco
	public void Fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
	
	public Cursor getCursor() {
		try {
			// select * from programas
			return db.query(NOME_TABELA, Usuario.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os dados de usuario: " + e.toString());
			return null;
		}
	}
	
	// Retorna uma lista com todos os programas
	public List<Usuario> listarUsuarios() {
		Cursor c = getCursor();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		if (c.moveToFirst()) {
			do {
				Usuario u = new Usuario();

				u.setId(c.getLong(c.getColumnIndex(UsuarioCampos.ID.getCampo())));
				u.setAltura(c.getString(c.getColumnIndex(UsuarioCampos.ALTURA.getCampo())));
				u.setPeso(c.getString(c.getColumnIndex(UsuarioCampos.PESO.getCampo())));
				u.setBicepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampos.BICEPS_ESQUERDO.getCampo())));
				u.setBicepsDireito(c.getString(c.getColumnIndex(UsuarioCampos.BICEPS_DIREITO.getCampo())));
				u.setTricepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampos.TRICEPS_ESQUERDO.getCampo())));				
				u.setTricepsDireito(c.getString(c.getColumnIndex(UsuarioCampos.TRICEPS_DIREITO.getCampo())));
				u.setCintura(c.getString(c.getColumnIndex(UsuarioCampos.CINTURA.getCampo())));
				u.setPeitoral(c.getString(c.getColumnIndex(UsuarioCampos.PEITORAL.getCampo())));
				u.setCoxaEsquerda(c.getString(c.getColumnIndex(UsuarioCampos.COXA_ESQUERDA.getCampo())));
				u.setCoxaDireita(c.getString(c.getColumnIndex(UsuarioCampos.COXA_DIREITA.getCampo())));
				u.setPanturrilhaEsquerda(c.getString(c.getColumnIndex(UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo())));
				u.setPanturrilhaDireita(c.getString(c.getColumnIndex(UsuarioCampos.PANTURRILHA_DIREITA.getCampo())));
				u.setQuadril(c.getString(c.getColumnIndex(UsuarioCampos.QUADRIL.getCampo())));			
				u.setData(c.getString(c.getColumnIndex(UsuarioCampos.DATA.getCampo())));			
				usuarios.add(u);
				
			} while (c.moveToNext());
		}
		
		return usuarios;
	}
	
	// Salva o programa, insere um novo ou atualiza
	public long salvar(Usuario usuario) {
		long id = usuario.getId();
		
		if (id != 0)
			atualizarUsuario(usuario);
		else
			id = inserir(usuario);
		
		return id;
				
	}
	

	// Insere um novo programa
	public long inserir(Usuario usuario) {
		ContentValues values = new ContentValues();
		
		values.put(UsuarioCampos.ALTURA.getCampo(), usuario.getAltura());
		values.put(UsuarioCampos.PESO.getCampo(), usuario.getPeso());
		values.put(UsuarioCampos.BICEPS_ESQUERDO.getCampo(), usuario.getBicepsEsquerdo());
		values.put(UsuarioCampos.BICEPS_DIREITO.getCampo(), usuario.getBicepsDireito());
		values.put(UsuarioCampos.TRICEPS_ESQUERDO.getCampo(), usuario.getTricepsEsquerdo());
		values.put(UsuarioCampos.TRICEPS_DIREITO.getCampo(), usuario.getTricepsDireito());
		values.put(UsuarioCampos.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampos.PEITORAL.getCampo(), usuario.getPeitoral());
		values.put(UsuarioCampos.COXA_ESQUERDA.getCampo(), usuario.getCoxaEsquerda());
		values.put(UsuarioCampos.COXA_DIREITA.getCampo(), usuario.getCoxaDireita());
		values.put(UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo(), usuario.getPanturrilhaEsquerda());
		values.put(UsuarioCampos.PANTURRILHA_DIREITA.getCampo(), usuario.getPanturrilhaDireita());
		values.put(UsuarioCampos.QUADRIL.getCampo(), usuario.getQuadril());
		values.put(UsuarioCampos.DATA.getCampo(), usuario.getData());	
		
		long id = db.insert(NOME_TABELA, "", values); 
		
		return id;
	}
	
	// Atualiza o programa com os valores abaixo
	// A cl�usula where � utilizada para identificar o programa a ser atualizado
	public int atualizarUsuario(Usuario usuario) {
		ContentValues values = new ContentValues();
		
		values.put(UsuarioCampos.ALTURA.getCampo(), usuario.getAltura());
		values.put(UsuarioCampos.PESO.getCampo(), usuario.getPeso());
		values.put(UsuarioCampos.BICEPS_ESQUERDO.getCampo(), usuario.getBicepsEsquerdo());
		values.put(UsuarioCampos.BICEPS_DIREITO.getCampo(), usuario.getBicepsDireito());
		values.put(UsuarioCampos.TRICEPS_ESQUERDO.getCampo(), usuario.getTricepsEsquerdo());
		values.put(UsuarioCampos.TRICEPS_DIREITO.getCampo(), usuario.getTricepsDireito());
		values.put(UsuarioCampos.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampos.PEITORAL.getCampo(), usuario.getPeitoral());
		values.put(UsuarioCampos.COXA_ESQUERDA.getCampo(), usuario.getCoxaEsquerda());
		values.put(UsuarioCampos.COXA_DIREITA.getCampo(), usuario.getCoxaDireita());
		values.put(UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo(), usuario.getPanturrilhaEsquerda());
		values.put(UsuarioCampos.PANTURRILHA_DIREITA.getCampo(), usuario.getPanturrilhaDireita());
		values.put(UsuarioCampos.QUADRIL.getCampo(), usuario.getQuadril());
		values.put(UsuarioCampos.DATA.getCampo(), usuario.getData());
		
		String id = String.valueOf(usuario.getId());
		String where = UsuarioCampos.ID.getCampo() + "=?";
		String[] whereArgs = new String[] { id };
		
		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros.");
		
		return count;
		
	}
	
	// Busca o programa pelo id
	public Usuario buscarUsuario(long id) {
		//faz um select na tabela programas passando o id
		Cursor c = db.query(true, NOME_TABELA, Usuario.colunas, UsuarioCampos.ID.getCampo() + "=" + id, null, null, null, null, null);
		
		if (c.getCount() >0) {
			// Posiciona no primeiro elemento do cursor
			c.moveToFirst();
			
			Usuario usuario = new Usuario();
			
			// Lendo os dados do cursor
			usuario.setId(c.getLong(c.getColumnIndex(UsuarioCampos.ID.getCampo())));
			usuario.setAltura(c.getString(c.getColumnIndex(UsuarioCampos.ALTURA.getCampo())));
			usuario.setPeso(c.getString(c.getColumnIndex(UsuarioCampos.PESO.getCampo())));
			usuario.setBicepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampos.BICEPS_ESQUERDO.getCampo())));
			usuario.setBicepsDireito(c.getString(c.getColumnIndex(UsuarioCampos.BICEPS_DIREITO.getCampo())));
			usuario.setTricepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampos.TRICEPS_ESQUERDO.getCampo())));				
			usuario.setTricepsDireito(c.getString(c.getColumnIndex(UsuarioCampos.TRICEPS_DIREITO.getCampo())));
			usuario.setCintura(c.getString(c.getColumnIndex(UsuarioCampos.CINTURA.getCampo())));
			usuario.setPeitoral(c.getString(c.getColumnIndex(UsuarioCampos.PEITORAL.getCampo())));
			usuario.setCoxaEsquerda(c.getString(c.getColumnIndex(UsuarioCampos.COXA_ESQUERDA.getCampo())));
			usuario.setCoxaDireita(c.getString(c.getColumnIndex(UsuarioCampos.COXA_DIREITA.getCampo())));
			usuario.setPanturrilhaEsquerda(c.getString(c.getColumnIndex(UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo())));
			usuario.setPanturrilhaDireita(c.getString(c.getColumnIndex(UsuarioCampos.PANTURRILHA_DIREITA.getCampo())));
			usuario.setQuadril(c.getString(c.getColumnIndex(UsuarioCampos.QUADRIL.getCampo())));	
			usuario.setData(c.getString(c.getColumnIndex(UsuarioCampos.DATA.getCampo())));	
			
			return usuario;
		}
		
		return null;
	}
	
	// Exclui o programa para o id passado
	public int excluir(long id) {
		String where =  UsuarioCampos.ID.getCampo() + "=?";
		
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] {_id};
		
		int count = db.delete(NOME_TABELA, where, whereArgs);
		
		Log.i(CATEGORIA, "Deletou ["+ count +" registros.");
		
		return count;
	}
	
	/**
	 * 
	 * Método utilizado para pegar as medidas do usuário por uma data.
	 * Será importante, principalmente, para o requisito de desenvolvimento do usuário
	 * por período.
	 * 
	 * @param data - Será passada um objeto String, já que o SQLite não suporta DateTime
	 * @return
	 */
	public Usuario getUsuarioByDate(String data) {
		
		Cursor c = db.query(true, NOME_TABELA, Usuario.colunas, UsuarioCampos.DATA.getCampo() + "=" + data, null, null, null, null, null);
		
		if (c.getCount() > 0) {

			c.moveToFirst();			
			Usuario usuario = new Usuario();
			
			usuario.setId(c.getLong(c.getColumnIndex(UsuarioCampos.ID.getCampo())));
			usuario.setAltura(c.getString(c.getColumnIndex(UsuarioCampos.ALTURA.getCampo())));
			usuario.setPeso(c.getString(c.getColumnIndex(UsuarioCampos.PESO.getCampo())));
			usuario.setBicepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampos.BICEPS_ESQUERDO.getCampo())));
			usuario.setBicepsDireito(c.getString(c.getColumnIndex(UsuarioCampos.BICEPS_DIREITO.getCampo())));
			usuario.setTricepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampos.TRICEPS_ESQUERDO.getCampo())));				
			usuario.setTricepsDireito(c.getString(c.getColumnIndex(UsuarioCampos.TRICEPS_DIREITO.getCampo())));
			usuario.setCintura(c.getString(c.getColumnIndex(UsuarioCampos.CINTURA.getCampo())));
			usuario.setPeitoral(c.getString(c.getColumnIndex(UsuarioCampos.PEITORAL.getCampo())));
			usuario.setCoxaEsquerda(c.getString(c.getColumnIndex(UsuarioCampos.COXA_ESQUERDA.getCampo())));
			usuario.setCoxaDireita(c.getString(c.getColumnIndex(UsuarioCampos.COXA_DIREITA.getCampo())));
			usuario.setPanturrilhaEsquerda(c.getString(c.getColumnIndex(UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo())));
			usuario.setPanturrilhaDireita(c.getString(c.getColumnIndex(UsuarioCampos.PANTURRILHA_DIREITA.getCampo())));
			usuario.setQuadril(c.getString(c.getColumnIndex(UsuarioCampos.QUADRIL.getCampo())));	
			usuario.setData(c.getString(c.getColumnIndex(UsuarioCampos.DATA.getCampo())));
			
			return usuario;
		}
		
		return null;
	}

}
