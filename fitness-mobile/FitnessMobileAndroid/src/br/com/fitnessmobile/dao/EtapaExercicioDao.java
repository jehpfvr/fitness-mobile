package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.EtapaExercicioCampos;
import br.com.fitnessmobile.model.EtapaExercicio;
import br.com.fitnessmobile.model.Exercicio;

public class EtapaExercicioDao extends Dao{
	

	// Nome da tabela
	public static final String NOME_TABELA = "etapa_exercicio";

	
	private Context context;
	
	// Cria o banco de dados com um script SQL
	public EtapaExercicioDao(Context ctx) {
		super(ctx);

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
	public List<EtapaExercicio> listarEtapaExerciciosByDay(Integer ete_id, Integer ete_diaid) {
		
		//TODO Erro aqui.
		Cursor c = db.query(true, NOME_TABELA, EtapaExercicio.colunas, EtapaExercicioCampos.ETAPA_ID.getCampo() + "=" + ete_id + " and " + EtapaExercicioCampos.DIA_ID.getCampo() +"="+ ete_diaid, null, null, null, null, null);
		Log.i("Chegou " , "Aqui Lista de exercicios da etapa");
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
				ee.setDiaID(c.getInt(c.getColumnIndex(EtapaExercicioCampos.DIA_ID.getCampo())));
				ee.setDtBaixa(c.getLong(c.getColumnIndex(EtapaExercicioCampos.DATA_BAIXA.getCampo())));
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
		values.put(EtapaExercicioCampos.DIA_ID.getCampo(), etapaExercicio.getDiaID());
		values.put(EtapaExercicioCampos.DATA_BAIXA.getCampo(), etapaExercicio.getDtBaixa());
		
		Integer id = (int) db.insert(NOME_TABELA, "", values);
		
		Log.i(CATEGORIA, "Inserindo no banco etapa exercicio: "+ values);
		
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
		values.put(EtapaExercicioCampos.DIA_ID.getCampo(), etapaExercicio.getDiaID());
		values.put(EtapaExercicioCampos.DATA_BAIXA.getCampo(), etapaExercicio.getDtBaixa());
		
		String id = String.valueOf(etapaExercicio.getId());
		String where = EtapaExercicioCampos.ID.getCampo() + "=?";
		String[] whereArgs = new String[] { id };
		
		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros.");
		
		return count;
	}

	// Retorna ultimo ID inserido no BD
	public int getUltimoIDInsert() {
		
		Cursor c = db.rawQuery("SELECT last_insert_rowid() from " + NOME_TABELA, null);
		int ultimoID = -1;
		
		c.moveToNext();
		ultimoID = c.getInt(0);

		return ultimoID;
	}
	
	
	public int excluir(long id) {
		String where =  EtapaExercicioCampos.ID.getCampo() + "=?";
		
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] {_id};
		
		int count = db.delete(NOME_TABELA, where, whereArgs);
		
		Log.i(CATEGORIA, "Deletou "+ count +" registros.");
		
		return count;
	}
}
