package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.UsuarioCampos;
import br.com.fitnessmobile.model.Usuario;

public class MedidasDao extends Dao {

	// Nome da tabela
	public static final String NOME_TABELA = "medida";


	// Cria o banco de dados com um script SQL
	public MedidasDao(Context ctx) {
		super(ctx);

		// abre o banco no modo escrita para poder alterar tamb�m
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
			return db.query(NOME_TABELA, Usuario.colunas, null, null, null,
					null, null);
		} catch (SQLException e) {
			Log.e(CATEGORIA,
					"Erro ao buscar os dados de usuario: " + e.toString());
			return null;
		}
	}


	// Retorna uma lista com todos os usuarios
	public List<Usuario> listarUsuario() {
		Cursor c = getCursor();

		List<Usuario> usuarios = new ArrayList<Usuario>();

		if (c.moveToFirst()) {
			do {

				Usuario u = new Usuario();

				u.setId(c.getInt(c.getColumnIndex(UsuarioCampos.ID.getCampo())));
				u.setPeso(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.PESO.getCampo()))));
				u.setBicepsEsquerdo(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.BICEPS_ESQUERDO
								.getCampo()))));
				u.setBicepsDireito(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.BICEPS_DIREITO.getCampo()))));
				u.setTricepsEsquerdo(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.TRICEPS_ESQUERDO
								.getCampo()))));
				u.setTricepsDireito(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.TRICEPS_DIREITO
								.getCampo()))));
				u.setCintura(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.CINTURA.getCampo()))));
				u.setPeitoral(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.PEITORAL.getCampo()))));
				u.setCoxaEsquerda(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.COXA_ESQUERDA.getCampo()))));
				u.setCoxaDireita(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.COXA_DIREITA.getCampo()))));
				u.setPanturrilhaEsquerda(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.PANTURRILHA_ESQUERDA
								.getCampo()))));
				u.setPanturrilhaDireita(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.PANTURRILHA_DIREITA
								.getCampo()))));
				u.setQuadril(Float.parseFloat(c.getString(c
						.getColumnIndex(UsuarioCampos.QUADRIL.getCampo()))));
				u.setData(c.getString(c.getColumnIndex(UsuarioCampos.DATA
						.getCampo())));
				usuarios.add(u);

			} while (c.moveToNext());
		}

		return usuarios;
	}

	// Salva o programa, insere um novo ou atualiza
	public Integer salvar(Usuario usuario) {
		Integer id = usuario.getId();

		if (id != null)
			atualizarUsuario(usuario);
		else
			id = inserir(usuario);

		return id;

	}

	// Insere um novo usuario
	private Integer inserir(Usuario usuario) {
		ContentValues values = new ContentValues();

		values.put(UsuarioCampos.PESO.getCampo(), usuario.getPeso());
		values.put(UsuarioCampos.BICEPS_ESQUERDO.getCampo(),
				usuario.getBicepsEsquerdo());
		values.put(UsuarioCampos.BICEPS_DIREITO.getCampo(),
				usuario.getBicepsDireito());
		values.put(UsuarioCampos.TRICEPS_ESQUERDO.getCampo(),
				usuario.getTricepsEsquerdo());
		values.put(UsuarioCampos.TRICEPS_DIREITO.getCampo(),
				usuario.getTricepsDireito());
		values.put(UsuarioCampos.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampos.PEITORAL.getCampo(), usuario.getPeitoral());
		values.put(UsuarioCampos.COXA_ESQUERDA.getCampo(),
				usuario.getCoxaEsquerda());
		values.put(UsuarioCampos.COXA_DIREITA.getCampo(),
				usuario.getCoxaDireita());
		values.put(UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo(),
				usuario.getPanturrilhaEsquerda());
		values.put(UsuarioCampos.PANTURRILHA_DIREITA.getCampo(),
				usuario.getPanturrilhaDireita());
		values.put(UsuarioCampos.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampos.DATA.getCampo(), usuario.getData());

		Integer id = (int) db.insert(NOME_TABELA, "", values); 

		Log.i(CATEGORIA, "Inserindo nova medida ao banco: "+ values);

		return id;
	}

	// Atualiza o usuario com os valores abaixo
	// A cl�usula where � utilizada para identificar o usuario a ser atualizado
	private int atualizarUsuario(Usuario usuario) {
		ContentValues values = new ContentValues();

		values.put(UsuarioCampos.PESO.getCampo(), usuario.getPeso());
		values.put(UsuarioCampos.BICEPS_ESQUERDO.getCampo(),
				usuario.getBicepsEsquerdo());
		values.put(UsuarioCampos.BICEPS_DIREITO.getCampo(),
				usuario.getBicepsDireito());
		values.put(UsuarioCampos.TRICEPS_ESQUERDO.getCampo(),
				usuario.getTricepsEsquerdo());
		values.put(UsuarioCampos.TRICEPS_DIREITO.getCampo(),
				usuario.getTricepsDireito());
		values.put(UsuarioCampos.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampos.PEITORAL.getCampo(), usuario.getPeitoral());
		values.put(UsuarioCampos.COXA_ESQUERDA.getCampo(),
				usuario.getCoxaEsquerda());
		values.put(UsuarioCampos.COXA_DIREITA.getCampo(),
				usuario.getCoxaDireita());
		values.put(UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo(),
				usuario.getPanturrilhaEsquerda());
		values.put(UsuarioCampos.PANTURRILHA_DIREITA.getCampo(),
				usuario.getPanturrilhaDireita());
		values.put(UsuarioCampos.CINTURA.getCampo(), usuario.getCintura());
		values.put(UsuarioCampos.DATA.getCampo(), usuario.getData());

		String id = String.valueOf(usuario.getId());
		String where = UsuarioCampos.ID.getCampo() + "=?";
		String[] whereArgs = new String[] { id };

		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros.");

		return count;

	}

	// Busca o usuario pelo id
	public Usuario buscarUsuario(Integer id) {
		// faz um select na tabela usuarios passando o id
		Cursor c = db.query(true, NOME_TABELA, Usuario.colunas,
				UsuarioCampos.ID.getCampo() + "=" + id, null, null, null, null,
				null);

		if (c.getCount() > 0) {
			// Posiciona no primeiro elemento do cursor
			c.moveToFirst();

			Usuario usuario = new Usuario();

			// Lendo os dados do cursor
			usuario.setId(c.getInt(c.getColumnIndex(UsuarioCampos.ID
					.getCampo())));
			usuario.setPeso(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.PESO.getCampo()))));
			usuario.setBicepsEsquerdo(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.BICEPS_ESQUERDO.getCampo()))));
			usuario.setBicepsDireito(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.BICEPS_DIREITO.getCampo()))));
			usuario.setTricepsEsquerdo(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.TRICEPS_ESQUERDO.getCampo()))));
			usuario.setTricepsDireito(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.TRICEPS_DIREITO.getCampo()))));
			usuario.setCintura(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.CINTURA.getCampo()))));
			usuario.setPeitoral(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.PEITORAL.getCampo()))));
			usuario.setCoxaEsquerda(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.COXA_ESQUERDA.getCampo()))));
			usuario.setCoxaDireita(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.COXA_DIREITA.getCampo()))));
			usuario.setPanturrilhaEsquerda(Float.parseFloat(c.getString(c.getColumnIndex(UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo()))));
			usuario.setPanturrilhaDireita(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.PANTURRILHA_DIREITA
							.getCampo()))));
			usuario.setQuadril(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.QUADRIL.getCampo()))));
			usuario.setData(c.getString(c.getColumnIndex(UsuarioCampos.DATA
					.getCampo())));

			return usuario;
		}

		return null;
	}

	// Exclui o usuario para o id passado
	public int excluir(Integer id) {
		String where = UsuarioCampos.ID.getCampo() + "=?";

		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id };

		int count = db.delete(NOME_TABELA, where, whereArgs);

		Log.i(CATEGORIA, "Deletou [" + count + "} registros.");

		return count;
	}

	public Usuario getUsuarioByDate(String data) throws Exception {

		Cursor c = db.query(true, NOME_TABELA, Usuario.colunas,
				UsuarioCampos.DATA.getCampo() + " = '" + data + "'", null, null, null,
				null, null);

		if (c.getCount() > 0) {

			c.moveToFirst();
			Usuario usuario = new Usuario();

			usuario.setId(c.getInt(c.getColumnIndex(UsuarioCampos.ID
					.getCampo())));
			usuario.setPeso(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.PESO.getCampo()))));
			usuario.setBicepsEsquerdo(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.BICEPS_ESQUERDO.getCampo()))));
			usuario.setBicepsDireito(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.BICEPS_DIREITO.getCampo()))));
			usuario.setTricepsEsquerdo(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.TRICEPS_ESQUERDO.getCampo()))));
			usuario.setTricepsDireito(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.TRICEPS_DIREITO.getCampo()))));
			usuario.setCintura(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.CINTURA.getCampo()))));
			usuario.setPeitoral(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.PEITORAL.getCampo()))));
			usuario.setCoxaEsquerda(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.COXA_ESQUERDA.getCampo()))));
			usuario.setCoxaDireita(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.COXA_DIREITA.getCampo()))));
			usuario.setPanturrilhaEsquerda(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.PANTURRILHA_ESQUERDA
							.getCampo()))));
			usuario.setPanturrilhaDireita(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.PANTURRILHA_DIREITA
							.getCampo()))));
			usuario.setQuadril(Float.parseFloat(c.getString(c
					.getColumnIndex(UsuarioCampos.QUADRIL.getCampo()))));
			usuario.setData(c.getString(c.getColumnIndex(UsuarioCampos.DATA
					.getCampo())));

			return usuario;
		}

		throw new Exception();
	}
}
