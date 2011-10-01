package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.ExercicioCampos;
import br.com.fitnessmobile.adapter.enums.Musculo;
import br.com.fitnessmobile.model.Exercicio;

public class ExercicioDao {

	private static final String CATEGORIA = "fitness";
	
	// Script para fazer drop na tabela
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS exercicio";
	
	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE_EXERCICIO = new String[] {
			"create table exercicio ( _id integer primary key autoincrement," +
			"nome text null," +
			"descricao text null," +
			"situacao text null," +
			"tipo text null," +
			"indicecalorico real null," +
			"grupomuscular text null," +
			"musculo text null);"};

	// Nome do banco
	private static final String NOME_BANCO = "fitness_mobile_exer";
	
	// Controle de vers�o
	private static final int VERSAO_BANCO = 1;

	// Nome da tabela
	public static final String NOME_TABELA = "exercicio";

	protected SQLiteDatabase db;
	
	// Classe utilit�ria para abrir, criar, e atualizar o banco de dados
	SQLiteHelper dbHelper;
	
	
	// Cria o banco de dados com um script SQL
	public ExercicioDao(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, ExercicioDao.NOME_BANCO, ExercicioDao.VERSAO_BANCO,
				ExercicioDao.SCRIPT_DATABASE_CREATE_EXERCICIO, ExercicioDao.SCRIPT_DATABASE_DELETE);

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
			// select * from exercicio
			return db.query(NOME_TABELA, Exercicio.colunas, null, null, null, null, null, null);
		} catch (SQLException e) {
			Log.e(CATEGORIA, "Erro ao buscar os exercicios: " + e.toString());
			return null;
		}
	}
	
	// Retorna uma lista com todos os exercicio
	public List<Exercicio> listarExercicios() {
		Cursor c = getCursor();
		
		List<Exercicio> exercicios = new ArrayList<Exercicio>();
		
		if (c.moveToFirst()) {
			do {
				
				Exercicio e = new Exercicio();
								
				e.setId(c.getInt(c.getColumnIndex(ExercicioCampos.ID.getCampo())));
				e.setNome(c.getString(c.getColumnIndex(ExercicioCampos.NOME.getCampo())));
				e.setDescricao(c.getString(c.getColumnIndex(ExercicioCampos.DESCRICAO.getCampo())));
				e.setSituacao(c.getString(c.getColumnIndex(ExercicioCampos.SITUACAO.getCampo())));
				e.setTipo(c.getString(c.getColumnIndex(ExercicioCampos.TIPO.getCampo())));
				String indiceCalorico = c.getString(c.getColumnIndex(ExercicioCampos.INDICE_CALORICO.getCampo()));
				e.setIndiceCalorico(Float.valueOf(indiceCalorico).floatValue());
				//Log.i(CATEGORIA, "Carregando exercicio: " + e.getNome());
				String musculo = c.getString(c.getColumnIndex(ExercicioCampos.MUSCULO.getCampo()));
				e.setMusculoPrincipal(Musculo.getEnumById(musculo));
				//Log.i(CATEGORIA, "Carregando Musculo : " + e.getMusculoPrincipal().getMusculoNome());
				ArrayList<Musculo> grupo_musculares = new ArrayList<Musculo>();
				String arrayGrupoMuscular = c.getString(c.getColumnIndex(ExercicioCampos.GRUPO_MUSCULAR.getCampo()));
				for (String idMusculo : arrayGrupoMuscular.split(",")) {
					grupo_musculares.add(Musculo.getEnumById(idMusculo));
					//Log.i(CATEGORIA, "Grupo Muscular Carregado " + idMusculo + " Musculo:" + Musculo.getEnumById(idMusculo).getMusculoNome()); // Teste
				}
				e.setGrupoMuscular(grupo_musculares);
				//Log.i(CATEGORIA, "Musculo Carregado " + musculo); // Teste
				//Log.i(CATEGORIA, "Grupo Muscular Carregados " + arrayGrupoMuscular); // Teste
				musculo = null;
				indiceCalorico = null;
				grupo_musculares =  null;
				
				exercicios.add(e);
				
			} while (c.moveToNext());
		}
		
		return exercicios;
	}
	
	// Retorna uma lista com os exercicio filtrados
	public List<Exercicio> listarExerciciosFiltrados(Integer id) {
		Cursor c = db.query(true, NOME_TABELA, Exercicio.colunas, ExercicioCampos.MUSCULO.getCampo() + "=" + id, null, null, null, null, null);
		
		List<Exercicio> exercicios = new ArrayList<Exercicio>();
		
		if (c.moveToFirst()) {
			do {
				Exercicio e = new Exercicio();
								
				e.setId(c.getInt(c.getColumnIndex(ExercicioCampos.ID.getCampo())));
				e.setNome(c.getString(c.getColumnIndex(ExercicioCampos.NOME.getCampo())));
				e.setDescricao(c.getString(c.getColumnIndex(ExercicioCampos.DESCRICAO.getCampo())));
				e.setSituacao(c.getString(c.getColumnIndex(ExercicioCampos.SITUACAO.getCampo())));
				e.setTipo(c.getString(c.getColumnIndex(ExercicioCampos.TIPO.getCampo())));
				String indiceCalorico = c.getString(c.getColumnIndex(ExercicioCampos.INDICE_CALORICO.getCampo()));
				e.setIndiceCalorico(Float.valueOf(indiceCalorico).floatValue());
				String musculo = c.getString(c.getColumnIndex(ExercicioCampos.MUSCULO.getCampo()));
				e.setMusculoPrincipal(Musculo.getEnumById(musculo));
				ArrayList<Musculo> grupo_musculares = new ArrayList<Musculo>();
				String arrayGrupoMuscular = c.getString(c.getColumnIndex(ExercicioCampos.GRUPO_MUSCULAR.getCampo()));
				for (String idMusculo : arrayGrupoMuscular.split(",")) {
					grupo_musculares.add(Musculo.getEnumById(idMusculo));
				}
				e.setGrupoMuscular(grupo_musculares);
				musculo = null;
				indiceCalorico = null;
				grupo_musculares =  null;
				
				exercicios.add(e);
				
			} while (c.moveToNext());
		}
		
		return exercicios;
	}
	

	// Salva o exercicio, insere um novo ou atualiza
	public Integer salvar(Exercicio exercicio) {
		Integer id = exercicio.getId();
		
		if (id != null)
			atualizarExercicio(exercicio);
		else
			id = inserir(exercicio);
		
		return id;
	}	

	// Insere um novo exercicio
	private Integer inserir(Exercicio exercicio) {
		ContentValues values = new ContentValues();
		
		values.put(ExercicioCampos.NOME.getCampo(), exercicio.getNome());
		values.put(ExercicioCampos.DESCRICAO.getCampo(), exercicio.getDescricao());
		values.put(ExercicioCampos.SITUACAO.getCampo(), exercicio.getSituacao());
		values.put(ExercicioCampos.TIPO.getCampo(), exercicio.getTipo());
		values.put(ExercicioCampos.INDICE_CALORICO.getCampo(), exercicio.getIndiceCalorico());
		values.put(ExercicioCampos.MUSCULO.getCampo(), exercicio.getMusculoPrincipal().getMusculoId());
		
		String grupo_musculares = null;
		int indice = 0;
		for (Musculo m : exercicio.getGrupoMuscular()) {
			if (indice == 0)
				grupo_musculares = "" + m.getMusculoId(); // 3
			else
				grupo_musculares = grupo_musculares + "," + m.getMusculoId(); // 1,2,4,6,7
			indice++;
		}
		values.put(ExercicioCampos.GRUPO_MUSCULAR.getCampo(), grupo_musculares);
		
		Integer id = (int) db.insert(NOME_TABELA, "", values);
		
		Log.i(CATEGORIA, "Inserindo no banco exercicio: "+ values);
		
		return id;
	}
	
	// Atualiza o exercicio com os valores abaixo
	// A cl�usula where � utilizada para identificar o exercicio a ser atualizado
	private int atualizarExercicio(Exercicio exercicio) {
		ContentValues values = new ContentValues();
		
		values.put(ExercicioCampos.NOME.getCampo(), exercicio.getNome());
		values.put(ExercicioCampos.DESCRICAO.getCampo(), exercicio.getDescricao());
		values.put(ExercicioCampos.SITUACAO.getCampo(), exercicio.getSituacao());
		values.put(ExercicioCampos.TIPO.getCampo(), exercicio.getTipo());
		values.put(ExercicioCampos.INDICE_CALORICO.getCampo(), exercicio.getIndiceCalorico());
		values.put(ExercicioCampos.MUSCULO.getCampo(), exercicio.getMusculoPrincipal().getMusculoId());
		
		String grupo_musculares = null;
		int indice = 0;
		for (Musculo m : exercicio.getGrupoMuscular()) {
			if (indice == 0)
				grupo_musculares = "" + m.getMusculoId(); // 3
			else
				grupo_musculares = grupo_musculares + "," + m.getMusculoId(); // 1,2,4,6,7
			indice++;
		}
		values.put(ExercicioCampos.GRUPO_MUSCULAR.getCampo(), grupo_musculares);

		String id = String.valueOf(exercicio.getId());
		String where = ExercicioCampos.ID.getCampo() + "=?";
		String[] whereArgs = new String[] { id };
		
		int count = db.update(NOME_TABELA, values, where, whereArgs);
		Log.i(CATEGORIA, "Atualizou [" + count + "] registros.");
		
		return count;
		
	}
	
	// Busca o exercicio pelo id
	public Exercicio buscarExercicio(long id) {
		//faz um select na tabela exercicios passando o id
		Cursor c = db.query(true, NOME_TABELA, Exercicio.colunas, ExercicioCampos.ID.getCampo() + "=" + id, null, null, null, null, null);
		
		if (c.getCount() >0) {
			// Posiciona no primeiro elemento do cursor
			c.moveToFirst();
			
			Exercicio e = new Exercicio();
			
			e.setId(c.getInt(c.getColumnIndex(ExercicioCampos.ID.getCampo())));
			e.setNome(c.getString(c.getColumnIndex(ExercicioCampos.NOME.getCampo())));
			e.setDescricao(c.getString(c.getColumnIndex(ExercicioCampos.DESCRICAO.getCampo())));
			e.setSituacao(c.getString(c.getColumnIndex(ExercicioCampos.SITUACAO.getCampo())));
			e.setTipo(c.getString(c.getColumnIndex(ExercicioCampos.TIPO.getCampo())));
			String indiceCalorico = c.getString(c.getColumnIndex(ExercicioCampos.INDICE_CALORICO.getCampo()));
			e.setIndiceCalorico(Float.valueOf(indiceCalorico).floatValue());
			String musculo = c.getString(c.getColumnIndex(ExercicioCampos.MUSCULO.getCampo()));
			e.setMusculoPrincipal(Musculo.getEnumById(musculo));
			ArrayList<Musculo> grupo_musculares = new ArrayList<Musculo>();
			String arrayGrupoMuscular = c.getString(c.getColumnIndex(ExercicioCampos.GRUPO_MUSCULAR.getCampo()));
			for (String idMusculo : arrayGrupoMuscular.split(",")) {
				grupo_musculares.add(Musculo.getEnumById(idMusculo));
			}
			e.setGrupoMuscular(grupo_musculares);
			musculo = null;
			indiceCalorico = null;
			grupo_musculares =  null;
			
			return e;
		}
		
		return null;
	}
	
	// Exclui o exercicio para o id passado
	public int excluir(long id) {
		String where =  ExercicioCampos.ID.getCampo() + "=?";
		
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] {_id};
		
		int count = db.delete(NOME_TABELA, where, whereArgs);
		
		Log.i(CATEGORIA, "Deletou ["+ count +" registros.");
		
		return count;
	}
}