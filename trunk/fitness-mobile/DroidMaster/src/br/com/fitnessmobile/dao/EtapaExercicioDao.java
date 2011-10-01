package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.EtapaCampos;
import br.com.fitnessmobile.adapter.enums.EtapaExercicioCampos;
import br.com.fitnessmobile.model.Etapa;
import br.com.fitnessmobile.model.EtapaExercicio;
import br.com.fitnessmobile.model.Exercicio;

public class EtapaExercicioDao {
	
	private static final String CATEGORIA = "fitness";
	
	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS exercicio_etapa";
	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE_EXERCICIO_ETAPA = new String[] {
			"create table exercicio_etapa ( _id integer primary key autoincrement," +
			"flag integer null," +
			"tipoid integer null," +
			"etapaid integer null," +
			"exercicioid integer null);"};

	// Nome do banco
	private static final String NOME_BANCO = "fitness_mobile_exercicio_etapa";
	
	// Controle de versao
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String NOME_TABELA = "exercicio_etapa";

	protected SQLiteDatabase db;
	
	// Classe utilitaria para abrir, criar, e atualizar o banco de dados
	SQLiteHelper dbHelper;
	
	private Context context;
	
	// Cria o banco de dados com um script SQL
	public EtapaExercicioDao(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, EtapaExercicioDao.NOME_BANCO, EtapaExercicioDao.VERSAO_BANCO,
				EtapaExercicioDao.SCRIPT_DATABASE_CREATE_EXERCICIO_ETAPA, EtapaExercicioDao.SCRIPT_DATABASE_DELETE);

		// abre o banco no modo escrita para poder alterar tambem
		db = dbHelper.getWritableDatabase();
		this.context = ctx;
	}
	
	// Fecha o banco
	public void Fechar() {
		// fecha o banco de dados
		if (db != null) {
			db.close();
		}
	}


	// Retorna uma lista com os exercicio da etapa
	public List<EtapaExercicio> listarEtapaExercicios(Integer id) {
		Cursor c = db.query(true, NOME_TABELA, EtapaExercicio.colunas, EtapaExercicioCampos.ETAPA_ID.getCampo() + "=" + id, null, null, null, null, null);
		
		List<EtapaExercicio> etapa_exercicios = new ArrayList<EtapaExercicio>();
		ExercicioDao exercicioDao = new ExercicioDao(context);
		if (c.moveToFirst()) {
			do {
				EtapaExercicio ee = new EtapaExercicio();
								
				ee.setId(c.getInt(c.getColumnIndex(EtapaExercicioCampos.ID.getCampo())));
				ee.setEtapaid(c.getInt(c.getColumnIndex(EtapaExercicioCampos.ETAPA_ID.getCampo())));
				ee.setTipoID(c.getInt(c.getColumnIndex(EtapaExercicioCampos.TIPO_ID.getCampo())));
				Integer idexercicio = c.getInt(c.getColumnIndex(EtapaExercicioCampos.EXERCICIO_ID.getCampo()));
				Exercicio exercicio_bd = exercicioDao.buscarExercicio(idexercicio);
				ee.setExercicio(exercicio_bd);
				ee.setFlag(c.getInt(c.getColumnIndex(EtapaExercicioCampos.FLAG.getCampo())));
				etapa_exercicios.add(ee);
				
			} while (c.moveToNext());
		}
		exercicioDao.Fechar();
		return etapa_exercicios;
	}
	
	// Salva o selecionado exercicio a etapa, insere uma nova ou atualiza
	public Integer salvar(EtapaExercicio etapaExercicio) {
		Integer id = etapaExercicio.getId();
		
		if (id != null)
			atualizarEtapa(etapaExercicio);
		else
			id = inserir(etapaExercicio);
		
		return id;
	}
	
	// Insere um novo Exercicio a Etapa
	private Integer inserir(EtapaExercicio etapaExercicio) {
		ContentValues values = new ContentValues();
		
		values.put(EtapaExercicioCampos.ETAPA_ID.getCampo(), etapaExercicio.getEtapaid());
		values.put(EtapaExercicioCampos.EXERCICIO_ID.getCampo(), etapaExercicio.getExercicio().getId());
		values.put(EtapaExercicioCampos.FLAG.getCampo(), etapaExercicio.getFlag());
		values.put(EtapaExercicioCampos.TIPO_ID.getCampo(), etapaExercicio.getTipoID());

		Integer id = (int) db.insert(NOME_TABELA, "", values);
		
		Log.i(CATEGORIA, "Inserindo no banco etapa: "+ values);
		
		return id;
	}
	
	// Atualiza os exercicios da etapa com os valores abaixo
	// A clausula where e utilizada para identificar o exercicio da etapa a ser atualizado
	// Quando for dar baixa no exercicio
	private int atualizarEtapa(EtapaExercicio etapaExercicio) {
		ContentValues values = new ContentValues();
		
		values.put(EtapaExercicioCampos.ETAPA_ID.getCampo(), etapaExercicio.getEtapaid());
		values.put(EtapaExercicioCampos.EXERCICIO_ID.getCampo(), etapaExercicio.getExercicio().getId());
		values.put(EtapaExercicioCampos.FLAG.getCampo(), etapaExercicio.getFlag());
		values.put(EtapaExercicioCampos.TIPO_ID.getCampo(), etapaExercicio.getTipoID());
		
		String id = String.valueOf(etapaExercicio.getId());
		String where = EtapaExercicioCampos.ID.getCampo() + "=?";
		String[] whereArgs = new String[] { id };
		
		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros.");
		
		return count;
	}
}
