package br.com.fitnessmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.AnaerobicoCampos;
import br.com.fitnessmobile.model.Anaerobico;

public class AnaerobicoDao {
	
private static final String CATEGORIA = "fitness";
	
	// Script para fazer drop na tabela
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {"DROP TABLE IF EXISTS exercicio"};
	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE_EAnaerobico = new String[] {
			"create table eAnaerobico ( _id integer primary key ," +
			"peso real null," +
			"repeticao integer null," +
			"serie integer null);"};

	// Nome do banco
	private static final String NOME_BANCO = "fitness_mobile_EAna";
	
	// Controle de versao
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String NOME_TABELA = "eAnaerobico";

	protected SQLiteDatabase db;
	
	// Classe utilitaria para abrir, criar, e atualizar o banco de dados
	SQLiteHelper dbHelper;
	
	// Cria o banco de dados com um script SQL
	 public AnaerobicoDao(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, AnaerobicoDao.NOME_BANCO, AnaerobicoDao.VERSAO_BANCO,
				AnaerobicoDao.SCRIPT_DATABASE_CREATE_EAnaerobico, AnaerobicoDao.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar tambem
		db = dbHelper.getWritableDatabase();
	}
	
	// Fecha o banco
	public void Fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}
	
	// Insere um novo exercicio
	public Integer inserir(Anaerobico anaerobico) {
		ContentValues values = new ContentValues();
		
		values.put(AnaerobicoCampos.ID.getCampo(), anaerobico.getId());
		values.put(AnaerobicoCampos.PESO.getCampo(), anaerobico.getPeso());
		values.put(AnaerobicoCampos.SERIE.getCampo(), anaerobico.getSerie());
		values.put(AnaerobicoCampos.REPETICAO.getCampo(), anaerobico.getRepeticoes());
		
		Integer id = (int) db.insert(NOME_TABELA, "", values);
		
		Log.i(CATEGORIA, "Inserindo no banco Anaerobico: "+ values);
		
		return id;
	}
}
