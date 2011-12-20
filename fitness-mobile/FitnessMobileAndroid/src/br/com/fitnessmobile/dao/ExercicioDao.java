package br.com.fitnessmobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import br.com.fitnessmobile.adapter.enums.EtapaExercicioCampos;
import br.com.fitnessmobile.adapter.enums.ExercicioCampos;
import br.com.fitnessmobile.adapter.enums.Musculo;
import br.com.fitnessmobile.model.EstaExercicio;
import br.com.fitnessmobile.model.Exercicio;

public class ExercicioDao extends Dao {

	// Nome da tabela
	public static final String NOME_TABELA = "exercicio";
	
	// Cria o banco de dados com um script SQL
	public ExercicioDao(Context ctx) {
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
		Log.i("Exercicio id",""+id);
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
	// A clausula where e utilizada para identificar o exercicio a ser atualizado
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
	
	public List<EstaExercicio> getEstatisticasByExer() {
		Cursor c = getCursor();
		
		List<EstaExercicio> lista_estaexercicio = new ArrayList<EstaExercicio>();
		
		if (c.moveToFirst()) {
			do {
				EstaExercicio ee = new EstaExercicio();
				ee.setId((c.getLong(c.getColumnIndex(ExercicioCampos.ID.getCampo()))));
				ee.setNomeExercicio(c.getString(c.getColumnIndex(ExercicioCampos.NOME.getCampo())));
				ee.setTotal(getCountTotalExercicio(ee.getId()));
				ee.setFeitos(getCountFeitosExercicio(ee.getId()));
				
				Log.i("Exercicio ID", "" + ee.getId());
				Log.i("Nome", ee.getNomeExercicio());
				Log.i("Total", "" + ee.getTotal());
				Log.i("Feitos", "" + ee.getFeitos());
				
				lista_estaexercicio.add(ee);

			} while (c.moveToNext());
		}
		return lista_estaexercicio;
	}

	public int getCountTotalExercicio(long id) {
		Cursor c = db.rawQuery("SELECT COUNT("+ EtapaExercicioCampos.EXERCICIO_ID.getCampo()+") from " + EtapaExercicioDao.NOME_TABELA + " WHERE "+ EtapaExercicioCampos.EXERCICIO_ID.getCampo()+" = " + id, null);
		int count = -1;
		c.moveToFirst();
		count = c.getInt(0);
		return count;
	}
	
	public int getCountFeitosExercicio(long id) {
		Cursor c = db.rawQuery("SELECT COUNT("+ EtapaExercicioCampos.EXERCICIO_ID.getCampo()+") from " + EtapaExercicioDao.NOME_TABELA + " WHERE "+ EtapaExercicioCampos.EXERCICIO_ID.getCampo()+" = " + id + " AND "+EtapaExercicioCampos.FLAG.getCampo()+" = 1", null);
		int count = -1;
		c.moveToFirst();
		count = c.getInt(0);
		return count;
	}
}