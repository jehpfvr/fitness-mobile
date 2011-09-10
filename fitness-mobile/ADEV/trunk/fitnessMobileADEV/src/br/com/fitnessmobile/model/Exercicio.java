package br.com.fitnessmobile.model;

import java.util.List;
import br.com.fitnessmobile.model.enums.Musculo;

/**
 * Esta classe ir� instanciar um Exercicio.
 */
public class Exercicio {
	
	// Vari�veis
	private String exercicio_nome;
	private String exercicio_desc;
	private Musculo exercicio_musculo_principal;
	private List<Musculo> exercicio_grupo_muscular;
	
	public Exercicio(String exercicio_nome, String exercicio_desc, Musculo exercicio_musculo_principal, List<Musculo> exercicio_grupo_muscular) {
		this.exercicio_nome = exercicio_nome;
		this.exercicio_desc = exercicio_desc;
		this.exercicio_musculo_principal = exercicio_musculo_principal;
		this.exercicio_grupo_muscular = exercicio_grupo_muscular;
	}

	public String getExercicioNome() {
		return exercicio_nome;
	}

	public String getExercicioDesc() {
		return exercicio_desc;
	}

	public Musculo getExercicioMusculoPrincipal() {
		return exercicio_musculo_principal;
	}

	public List<Musculo> getExercicioGrupoMuscular() {
		return exercicio_grupo_muscular;
	}

	public void setExercicioNome(String exercicio_nome) {
		this.exercicio_nome = exercicio_nome;
	}

	public void setExercicioDesc(String exercicio_desc) {
		this.exercicio_desc = exercicio_desc;
	}

	public void setExercicioMusculoPrincipal(Musculo exercicio_musculo_principal) {
		this.exercicio_musculo_principal = exercicio_musculo_principal;
	}

	public void setExercicioGrupoMuscular(List<Musculo> exercicio_grupo_muscular) {
		this.exercicio_grupo_muscular = exercicio_grupo_muscular;
	}
}