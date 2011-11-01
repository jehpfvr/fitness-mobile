package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.EtapaCampos;
import br.com.fitnessmobile.adapter.enums.ProgramaCampos;
import br.com.fitnessmobile.model.Etapa;

public class EtapaDao {
	private static final String CATEGORIA = "fitness";
	
	// Script para fazer drop na tabela
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] {"DROP TABLE IF EXISTS etapa"};
	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE_ETAPA = new String[] {
			"create table etapa ( _id integer primary key autoincrement," +
			"nome text null," +
			"data_inicial numeric not null, data_final numeric not null," +		
			"programaid integer null);"};

	// Nome do banco
	private static final String NOME_BANCO = "fitness_mobile_etapa";
	
	// Controle de versao
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String NOME_TABELA = "etapa";

	protected SQLiteDatabase db;
	
	// Classe utilitaria para abrir, criar, e atualizar o banco de dados
	SQLiteHelper dbHelper;
	
	
	// Cria o banco de dados com um script SQL
	public EtapaDao(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, EtapaDao.NOME_BANCO, EtapaDao.VERSAO_BANCO,
				EtapaDao.SCRIPT_DATABASE_CREATE_ETAPA, EtapaDao.SCRIPT_DATABASE_DELETE);

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
	
	// Retorna uma lista com as etapas do programa ID
	public List<Etapa> listarEtapaByProgramaID(Integer id) {
		Cursor c = db.query(true, NOME_TABELA, Etapa.colunas, EtapaCampos.PROGRAMA_ID.getCampo() + "=" + id, null, null, null, null, null);

		List<Etapa> etapas = new ArrayList<Etapa>();

		if (c.moveToFirst()) {
			do {
				Etapa e = new Etapa();
								
				e.setId(c.getInt(c.getColumnIndex(EtapaCampos.ID.getCampo())));
				e.setNome(c.getString(c.getColumnIndex(EtapaCampos.NOME.getCampo())));
				e.setProgramaID(Integer.parseInt(c.getString(c.getColumnIndex(EtapaCampos.PROGRAMA_ID.getCampo()))));
				e.setDataInicio(c.getLong(c.getColumnIndex(EtapaCampos.DATA_INICIAL.getCampo())));
				e.setDataFim(c.getLong(c.getColumnIndex(EtapaCampos.DATA_FINAL.getCampo())));

				etapas.add(e);

			} while (c.moveToNext());
		}
		return etapas;
	}
	
	// Salva a etapa, insere uma nova ou atualiza
	public Integer salvar(Etapa etapa) {
		Integer id = etapa.getId();
		
		if (id != null)
			atualizarEtapa(etapa);
		else
			id = inserir(etapa);
		
		return id;
	}	

	// Insere uma nova Etapa
	private Integer inserir(Etapa etapa) {
		ContentValues values = new ContentValues();
		
		values.put(EtapaCampos.NOME.getCampo(), etapa.getNome());
		values.put(EtapaCampos.PROGRAMA_ID.getCampo(), etapa.getProgramaID());
		values.put(EtapaCampos.DATA_INICIAL.getCampo(), etapa.getDataInicio());
		values.put(EtapaCampos.DATA_FINAL.getCampo(), etapa.getDataFim());

		Integer id = (int) db.insert(NOME_TABELA, "", values);
		
		Log.i(CATEGORIA, "Inserindo nova etapa ao banco: "+ values);
		
		return id;
	}
	
	// Atualiza a etapa com os valores abaixo
	// A clausula where e utilizada para identificar a etapa a ser atualizada
	private int atualizarEtapa(Etapa etapa) {
		ContentValues values = new ContentValues();
		
		values.put(EtapaCampos.NOME.getCampo(), etapa.getNome());
		values.put(EtapaCampos.PROGRAMA_ID.getCampo(), etapa.getProgramaID());
		values.put(EtapaCampos.DATA_INICIAL.getCampo(), etapa.getDataInicio());
		values.put(EtapaCampos.DATA_FINAL.getCampo(), etapa.getDataFim());
		
		String id = String.valueOf(etapa.getId());
		String where = EtapaCampos.ID.getCampo() + "=?";
		String[] whereArgs = new String[] { id };
		
		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros.");
		
		return count;
		
	}

	public int excluir(long id) {
		String where =  ProgramaCampos.ID.getCampo() + "=?";
		
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] {_id};
		
		int count = db.delete(NOME_TABELA, where, whereArgs);
		
		Log.i(CATEGORIA, "Deletou ["+ count +" registros.");
		
		return count;
	}
}