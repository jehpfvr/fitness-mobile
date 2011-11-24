package br.com.fitnessmobile.dao;

import br.com.fitnessmobile.adapter.enums.AerobicoCampos;
import br.com.fitnessmobile.model.Aerobico;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

public class AerobicoDao extends Dao{

	// Nome da tabela
	public static final String NOME_TABELA = "exercicio_aerobico_realizado";

	public AerobicoDao(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
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
	public Integer inserir(Aerobico aerobico) {
		ContentValues values = new ContentValues();

		values.put(AerobicoCampos.EXR_ID.getCampo(), aerobico.getId_exc());
		values.put(AerobicoCampos.DISTANCIA.getCampo(), aerobico.getDistancia());
		values.put(AerobicoCampos.DURACAO.getCampo(), aerobico.getDuracao());
		values.put(AerobicoCampos.VELOCIDADE.getCampo(), aerobico.getVelocidade());

		Integer id = (int) db.insert(NOME_TABELA, "", values);

		Log.i(CATEGORIA, "Inserindo no banco Aerobico: "+ values);

		return id;
	}
}
