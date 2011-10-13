package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.fitnessmobile.model.Usuario;
import br.com.fitnessmobile.model.UsuarioCampo;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioDao extends Activity{
private static final String CATEGORIA = "fitness";
	
	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS usuario";
	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table usuario ( id integer primary key autoincrement, peso text not null, altura text not null, biceps_esquerdo text null, biceps_direito text null, triceps_esquerdo text null, triceps_direito text null, cintura text null, peitoral text null, coxa_esquerda text null, coxa_direita text null, panturrilha_esquerda text null, panturrilha_direita text null, quadril text null, data text not null);",};
			
	// Nome do banco
	private static final String NOME_BANCO = "fitness_mobile";
	
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
		this.db = dbHelper.getWritableDatabase();
	}
	
	// Fecha o banco
	public void Fechar() {
		// fecha o banco de dados
		if (this.db != null) {
			this.db.close();
		}
		//dbHelper.close();
	}
	
	
	public Cursor getCursor() {
		try {
			// select * from programas
			return this.db.query(NOME_TABELA, Usuario.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os dados de usuário: " + e.toString());
			return null;
		}
	}
	
	// Retorna uma lista com todos os programas
	public List<Usuario> listarUsuario() {
		Cursor c = getCursor();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		if (c.moveToFirst()) {
			do {
				
				Usuario u = new Usuario();
								
				u.setId(c.getLong(c.getColumnIndex(UsuarioCampo.ID.getCampo())));
				u.setAltura(c.getString(c.getColumnIndex(UsuarioCampo.ALTURA.getCampo())));
				u.setPeso(c.getString(c.getColumnIndex(UsuarioCampo.PESO.getCampo())));
				u.setBicepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampo.BICEPS_ESQUERDO.getCampo())));
				u.setBicepsDireito(c.getString(c.getColumnIndex(UsuarioCampo.BICEPS_DIREITO.getCampo())));
				u.setTricepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampo.TRICEPS_ESQUERDO.getCampo())));				
				u.setTricepsDireito(c.getString(c.getColumnIndex(UsuarioCampo.TRICEPS_DIREITO.getCampo())));
				u.setCintura(c.getString(c.getColumnIndex(UsuarioCampo.CINTURA.getCampo())));
				u.setPeitoral(c.getString(c.getColumnIndex(UsuarioCampo.PEITORAL.getCampo())));
				u.setCoxaEsquerda(c.getString(c.getColumnIndex(UsuarioCampo.COXA_ESQUERDA.getCampo())));
				u.setCoxaDireita(c.getString(c.getColumnIndex(UsuarioCampo.COXA_DIREITA.getCampo())));
				u.setPanturrilhaEsquerda(c.getString(c.getColumnIndex(UsuarioCampo.PANTURRILHA_ESQUERDA.getCampo())));
				u.setPanturrilhaDireita(c.getString(c.getColumnIndex(UsuarioCampo.PANTURRILHA_DIREITA.getCampo())));
				u.setQuadril(c.getString(c.getColumnIndex(UsuarioCampo.QUADRIL.getCampo())));
				u.setData(c.getString(c.getColumnIndex(UsuarioCampo.DATA.getCampo())));				
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
	private long inserir(Usuario usuario) {
		ContentValues values = new ContentValues();
		
		values.put(UsuarioCampo.ALTURA.getCampo(), usuario.getAltura());
		values.put(UsuarioCampo.PESO.getCampo(), usuario.getPeso());
		values.put(UsuarioCampo.BICEPS_ESQUERDO.getCampo(), usuario.getBicepsEsquerdo());
		values.put(UsuarioCampo.BICEPS_DIREITO.getCampo(), usuario.getBicepsDireito());
		values.put(UsuarioCampo.TRICEPS_ESQUERDO.getCampo(), usuario.getTricepsEsquerdo());
		values.put(UsuarioCampo.TRICEPS_DIREITO.getCampo(), usuario.getTricepsDireito());
		values.put(UsuarioCampo.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampo.PEITORAL.getCampo(), usuario.getPeitoral());
		values.put(UsuarioCampo.COXA_ESQUERDA.getCampo(), usuario.getCoxaEsquerda());
		values.put(UsuarioCampo.COXA_DIREITA.getCampo(), usuario.getCoxaDireita());
		values.put(UsuarioCampo.PANTURRILHA_ESQUERDA.getCampo(), usuario.getPanturrilhaEsquerda());
		values.put(UsuarioCampo.PANTURRILHA_DIREITA.getCampo(), usuario.getPanturrilhaDireita());
		values.put(UsuarioCampo.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampo.DATA.getCampo(), usuario.getData());
			
		
		long id = this.db.insert(NOME_TABELA, "", values); 
		
		return id;
	}
	
	// Atualiza o programa com os valores abaixo
	// A cláusula where é utilizada para identificar o programa a ser atualizado
	private int atualizarUsuario(Usuario usuario) {
		ContentValues values = new ContentValues();
		
		values.put(UsuarioCampo.ALTURA.getCampo(), usuario.getAltura());
		values.put(UsuarioCampo.PESO.getCampo(), usuario.getPeso());
		values.put(UsuarioCampo.BICEPS_ESQUERDO.getCampo(), usuario.getBicepsEsquerdo());
		values.put(UsuarioCampo.BICEPS_DIREITO.getCampo(), usuario.getBicepsDireito());
		values.put(UsuarioCampo.TRICEPS_ESQUERDO.getCampo(), usuario.getTricepsEsquerdo());
		values.put(UsuarioCampo.TRICEPS_DIREITO.getCampo(), usuario.getTricepsDireito());
		values.put(UsuarioCampo.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampo.PEITORAL.getCampo(), usuario.getPeitoral());
		values.put(UsuarioCampo.COXA_ESQUERDA.getCampo(), usuario.getCoxaEsquerda());
		values.put(UsuarioCampo.COXA_DIREITA.getCampo(), usuario.getCoxaDireita());
		values.put(UsuarioCampo.PANTURRILHA_ESQUERDA.getCampo(), usuario.getPanturrilhaEsquerda());
		values.put(UsuarioCampo.PANTURRILHA_DIREITA.getCampo(), usuario.getPanturrilhaDireita());
		values.put(UsuarioCampo.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampo.DATA.getCampo(), usuario.getData());
		
		String id = String.valueOf(usuario.getId());
		String where = UsuarioCampo.ID.getCampo() + "=?";
		String[] whereArgs = new String[] { id };
		
		int count = this.db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros.");
		
		return count;
		
	}
	
	// Busca o programa pelo id
	public Usuario buscarUsuario(long id) {
		//faz um select na tabela programas passando o id
		Cursor c = this.db.query(true, NOME_TABELA, Usuario.colunas, UsuarioCampo.ID.getCampo() + "=" + id, null, null, null, null, null);
		
		if (c.getCount() >0) {
			// Posiciona no primeiro elemento do cursor
			c.moveToFirst();
			
			Usuario usuario = new Usuario();
			
			// Lendo os dados do cursor
			usuario.setId(c.getLong(c.getColumnIndex(UsuarioCampo.ID.getCampo())));
			usuario.setAltura(c.getString(c.getColumnIndex(UsuarioCampo.ALTURA.getCampo())));
			usuario.setPeso(c.getString(c.getColumnIndex(UsuarioCampo.PESO.getCampo())));
			usuario.setBicepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampo.BICEPS_ESQUERDO.getCampo())));
			usuario.setBicepsDireito(c.getString(c.getColumnIndex(UsuarioCampo.BICEPS_DIREITO.getCampo())));
			usuario.setTricepsEsquerdo(c.getString(c.getColumnIndex(UsuarioCampo.TRICEPS_ESQUERDO.getCampo())));				
			usuario.setTricepsDireito(c.getString(c.getColumnIndex(UsuarioCampo.TRICEPS_DIREITO.getCampo())));
			usuario.setCintura(c.getString(c.getColumnIndex(UsuarioCampo.CINTURA.getCampo())));
			usuario.setPeitoral(c.getString(c.getColumnIndex(UsuarioCampo.PEITORAL.getCampo())));
			usuario.setCoxaEsquerda(c.getString(c.getColumnIndex(UsuarioCampo.COXA_ESQUERDA.getCampo())));
			usuario.setCoxaDireita(c.getString(c.getColumnIndex(UsuarioCampo.COXA_DIREITA.getCampo())));
			usuario.setPanturrilhaEsquerda(c.getString(c.getColumnIndex(UsuarioCampo.PANTURRILHA_ESQUERDA.getCampo())));
			usuario.setPanturrilhaDireita(c.getString(c.getColumnIndex(UsuarioCampo.PANTURRILHA_DIREITA.getCampo())));
			usuario.setQuadril(c.getString(c.getColumnIndex(UsuarioCampo.QUADRIL.getCampo())));
			usuario.setData(c.getString(c.getColumnIndex(UsuarioCampo.DATA.getCampo())));	
			
			return usuario;
		}
		
		return null;
	}
	
	// Exclui o programa para o id passado
	public int excluir(long id) {
		String where =  UsuarioCampo.ID.getCampo() + "=?";
		
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] {_id};
		
		int count = this.db.delete(NOME_TABELA, where, whereArgs);
		
		Log.i(CATEGORIA, "Deletou ["+ count +" registros.");
		
		return count;
	}

}
