package br.com.fitnessmobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Implementacao de SQLiteOpenHelper
 * 
 * Classe utilitaria para abrir, criar, e atualizar o banco de dados
 * 
 * @author Gustavo
 */
class SQLiteHelper extends SQLiteOpenHelper {

	private static final String CATEGORIA = "Fitness";

	private String[] scriptSQLCreate;
	private String[] scriptSQLDelete;

	/**
	 * Cria uma instancia de SQLiteHelper
	 * 
	 * @param context
	 * @param nomeBanco nome do banco de dados
	 * @param versaoBanco versao do banco de dados (se for diferente e para atualizar)
	 * @param scriptSQLCreate SQL com o create table..
	 * @param scriptSQLDelete SQL com o drop table...
	 */
	SQLiteHelper(Context context, String nomeBanco, int versaoBanco, String[] scriptSQLCreate, String[] scriptSQLDelete) {
		super(context, nomeBanco, null, versaoBanco);
		this.scriptSQLCreate = scriptSQLCreate;
		this.scriptSQLDelete = scriptSQLDelete;
	}

	@Override
	// Criar novo banco...
	public void onCreate(SQLiteDatabase db) {
		Log.i(CATEGORIA, "Criando banco com sql");
		int qtdeScripts = scriptSQLCreate.length;

		// Executa cada sql passado como parametro
		for (int i = 0; i < qtdeScripts; i++) {
			String sql = scriptSQLCreate[i];
			Log.i(CATEGORIA, sql);
			// Cria o banco de dados executando o script de criacao
			db.execSQL(sql);
		}
	}

	public void onDelete(SQLiteDatabase db) {
	 			Log.i(CATEGORIA, "Criando banco com sql");
	 			int qtdeScripts = scriptSQLDelete.length;
	 			// Executa cada sql passado como parâmetro
	 			for (int i = 0; i < qtdeScripts; i++) {
	 				String sql = scriptSQLDelete[i];
	 				Log.i(CATEGORIA, sql);
	 				// Cria o banco de dados executando o script de criação
	 				db.execSQL(sql);
	 
	 			}
	 
	 		}
	
	
	
	@Override
	// Mudou a versao...
	public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int novaVersao) {
		Log.w(CATEGORIA, "Atualizando da versao " + versaoAntiga + " para " + novaVersao + ". Todos os registros serao deletados.");
		Log.i(CATEGORIA, "Apagando as tabelas para recriar o banco.");
		// Deleta as tabelas...
		onDelete(db);
		// Cria novamente...
		onCreate(db);
	}
}