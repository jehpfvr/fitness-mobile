package br.com.fitnessmobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.AnaerobicoCampos;
import br.com.fitnessmobile.model.Anaerobico;

public class AnaerobicoDao extends Dao{
	

	// Nome da tabela
	public static final String NOME_TABELA = "eAnaerobico";

	// Cria o banco de dados com um script SQL
	 public AnaerobicoDao(Context ctx) {
		super(ctx);
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
