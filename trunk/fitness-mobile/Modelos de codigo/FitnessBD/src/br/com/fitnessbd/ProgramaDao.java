package br.com.fitnessbd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProgramaDao {
	
	private static final String CATEGORIA = "fitness";
	
	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS programa";
	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
			"create table programa ( _id integer primary key autoincrement, nome text not null, data_inicial text not null, data_final text not null);",
			"insert into programa(nome,data_inicial,data_final) values('Ganhar massa','10/08/2011','10/09/2011');",
			"insert into programa(nome,data_inicial,data_final) values('Perder peso','10/05/2011','10/08/2011');"};

	// Nome do banco
	private static final String NOME_BANCO = "fitness_mobile";
	
	// Controle de versão
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String NOME_TABELA = "programa";

	protected SQLiteDatabase db;
	
	// Classe utilitária para abrir, criar, e atualizar o banco de dados
	SQLiteHelper dbHelper;
	
	
	// Cria o banco de dados com um script SQL
	public ProgramaDao(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, ProgramaDao.NOME_BANCO, ProgramaDao.VERSAO_BANCO,
				ProgramaDao.SCRIPT_DATABASE_CREATE, ProgramaDao.SCRIPT_DATABASE_DELETE);

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
			return db.query(NOME_TABELA, Programa.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os programas: " + e.toString());
			return null;
		}
	}
	
	// Retorna uma lista com todos os programas
	public List<Programa> listarProgramas() {
		Cursor c = getCursor();
		
		List<Programa> programas = new ArrayList<Programa>();
		
		if (c.moveToFirst()) {
			do {
				
				Programa p = new Programa();
								
				p.setId(c.getLong(c.getColumnIndex(ProgramaCampos.ID.getCampo())));
				p.setNome(c.getString(c.getColumnIndex(ProgramaCampos.NOME.getCampo())));
				p.setDataInicial(c.getString(c.getColumnIndex(ProgramaCampos.DATA_INICIAL.getCampo())));
				p.setDataFinal(c.getString(c.getColumnIndex(ProgramaCampos.DATA_FINAL.getCampo())));
				
				programas.add(p);
				
			} while (c.moveToNext());
		}
		
		return programas;
	}
	
	// Salva o programa, insere um novo ou atualiza
	public long salvar(Programa programa) {
		long id = programa.getId();
		
		if (id != 0)
			atualizarPrograma(programa);
		else
			id = inserir(programa);
		
		return id;
				
	}
	

	// Insere um novo programa
	private long inserir(Programa programa) {
		ContentValues values = new ContentValues();
		
		values.put(ProgramaCampos.NOME.getCampo(), programa.getNome());
		values.put(ProgramaCampos.DATA_INICIAL.getCampo(), programa.getDataInicial());
		values.put(ProgramaCampos.DATA_FINAL.getCampo(), programa.getDataFinal());
		
		long id = db.insert(NOME_TABELA, "", values); 
		
		return id;
	}
	
	// Atualiza o programa com os valores abaixo
	// A cláusula where é utilizada para identificar o programa a ser atualizado
	private int atualizarPrograma(Programa programa) {
		ContentValues values = new ContentValues();
		
		values.put(ProgramaCampos.NOME.getCampo(), programa.getNome());
		values.put(ProgramaCampos.DATA_INICIAL.getCampo(), programa.getDataInicial());
		values.put(ProgramaCampos.DATA_FINAL.getCampo(), programa.getDataFinal());
		
		String id = String.valueOf(programa.getId());
		String where = ProgramaCampos.ID.getCampo() + "=?";
		String[] whereArgs = new String[] { id };
		
		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros.");
		
		return count;
		
	}
	
	// Busca o programa pelo id
	public Programa buscarPrograma(long id) {
		//faz um select na tabela programas passando o id
		Cursor c = db.query(true, NOME_TABELA, Programa.colunas, ProgramaCampos.ID.getCampo() + "=" + id, null, null, null, null, null);
		
		if (c.getCount() >0) {
			// Posiciona no primeiro elemento do cursor
			c.moveToFirst();
			
			Programa programa = new Programa();
			
			// Lendo os dados do cursor
			programa.setId(c.getLong(c.getColumnIndex(ProgramaCampos.ID.getCampo())));
			programa.setNome(c.getString(c.getColumnIndex(ProgramaCampos.NOME.getCampo())));
			programa.setDataInicial(c.getString(c.getColumnIndex(ProgramaCampos.DATA_INICIAL.getCampo())));
			programa.setDataFinal(c.getString(c.getColumnIndex(ProgramaCampos.DATA_FINAL.getCampo())));
			
			return programa;
		}
		
		return null;
	}
	
	// Exclui o programa para o id passado
	public int excluir(long id) {
		String where =  ProgramaCampos.ID.getCampo() + "=?";
		
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] {_id};
		
		int count = db.delete(NOME_TABELA, where, whereArgs);
		
		Log.i(CATEGORIA, "Deletou ["+ count +" registros.");
		
		return count;
	}
}
