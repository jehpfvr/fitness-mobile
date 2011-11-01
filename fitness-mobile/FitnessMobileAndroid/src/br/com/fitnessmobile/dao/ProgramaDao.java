package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;
import br.com.fitnessmobile.adapter.enums.ProgramaCampos;
import br.com.fitnessmobile.model.Programa;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProgramaDao extends Dao {
	
	// Nome da tabela
	public static final String NOME_TABELA = "programa";
		
	// Cria o banco de dados com um script SQL
	public ProgramaDao(Context ctx) {
		super(ctx);
		
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
								
				p.setId(c.getInt(c.getColumnIndex(ProgramaCampos.ID.getCampo())));
				p.setNome(c.getString(c.getColumnIndex(ProgramaCampos.NOME.getCampo())));
				p.setDataInicio(c.getLong(c.getColumnIndex(ProgramaCampos.DATA_INICIAL.getCampo())));
				p.setDataFim(c.getLong(c.getColumnIndex(ProgramaCampos.DATA_FINAL.getCampo())));
				
				programas.add(p);
				
			} while (c.moveToNext());
		}
		
		return programas;
	}
	
	// Salva o programa, insere um novo ou atualiza
	public Integer salvar(Programa programa) {
		Integer id = programa.getId();
		
		if (id != null)
			atualizarPrograma(programa);
		else
			id = inserir(programa);
		
		return id;
				
	}
	

	// Insere um novo programa
	private Integer inserir(Programa programa) {
		ContentValues values = new ContentValues();
		
		values.put(ProgramaCampos.NOME.getCampo(), programa.getNome());
		values.put(ProgramaCampos.DATA_INICIAL.getCampo(), programa.getDataInicio());
		values.put(ProgramaCampos.DATA_FINAL.getCampo(), programa.getDataFim());
		
		Integer id = (int) db.insert(NOME_TABELA, "", values); 
		
		return id;
	}
	
	// Atualiza o programa com os valores abaixo
	// A cláusula where é utilizada para identificar o programa a ser atualizado
	private int atualizarPrograma(Programa programa) {
		ContentValues values = new ContentValues();
		
		values.put(ProgramaCampos.NOME.getCampo(), programa.getNome());
		values.put(ProgramaCampos.DATA_INICIAL.getCampo(), programa.getDataInicio());
		values.put(ProgramaCampos.DATA_FINAL.getCampo(), programa.getDataFim());
		
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
			programa.setId(c.getInt(c.getColumnIndex(ProgramaCampos.ID.getCampo())));
			programa.setNome(c.getString(c.getColumnIndex(ProgramaCampos.NOME.getCampo())));
			programa.setDataInicio(c.getLong(c.getColumnIndex(ProgramaCampos.DATA_INICIAL.getCampo())));
			programa.setDataFim(c.getLong(c.getColumnIndex(ProgramaCampos.DATA_FINAL.getCampo())));
			
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
