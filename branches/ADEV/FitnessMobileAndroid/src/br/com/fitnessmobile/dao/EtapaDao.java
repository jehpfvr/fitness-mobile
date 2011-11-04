package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.EtapaCampos;
import br.com.fitnessmobile.model.Etapa;

public class EtapaDao extends Dao {

	// Nome da tabela
	public static final String NOME_TABELA = "etapa";

	
	// Cria o banco de dados com um script SQL
	public EtapaDao(Context ctx) {
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
		String where =  EtapaCampos.ID.getCampo() + "=?";
		
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] {_id};
		
		int count = db.delete(NOME_TABELA, where, whereArgs);
		
		Log.i(CATEGORIA, "Deletou ["+ count +"] registros.");
		
		return count;
	}
	
	// Busca o etapa pelo id
	public Etapa buscarEtapa(long id) {
		//faz um select na tabela etapas passando o id
		Cursor c = db.query(true, NOME_TABELA, Etapa.colunas, EtapaCampos.ID.getCampo() + "=" + id, null, null, null, null, null);
		
		if (c.getCount() >0) {
			// Posiciona no primeiro elemento do cursor
			c.moveToFirst();
			
			Etapa etapa = new Etapa();
			
			// Lendo os dados do cursor
			etapa.setId(c.getInt(c.getColumnIndex(EtapaCampos.ID.getCampo())));
			etapa.setProgramaID(c.getInt(c.getColumnIndex(EtapaCampos.PROGRAMA_ID.getCampo())));
			etapa.setNome(c.getString(c.getColumnIndex(EtapaCampos.NOME.getCampo())));
			etapa.setDataInicio(c.getLong(c.getColumnIndex(EtapaCampos.DATA_INICIAL.getCampo())));
			etapa.setDataFim(c.getLong(c.getColumnIndex(EtapaCampos.DATA_FINAL.getCampo())));
			
			return etapa;
		}
		
		return null;
	}
	
}