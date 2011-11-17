package br.com.fitnessmobile.model;

public class EstaExercicio {

	private Long id;
	private String nomeExercicio;
	private Integer total;
	private Integer feitos;
	
	public EstaExercicio() {
		
	}
	public EstaExercicio(Long id, String nomeExercicio, Integer total, Integer feitos) {
		this.id = id;
		this.nomeExercicio = nomeExercicio;
		this.total = total;
		this.feitos = feitos;
	}

	public String getNomeExercicio() {
		return nomeExercicio;
	}

	public void setNomeExercicio(String nomeExercicio) {
		this.nomeExercicio = nomeExercicio;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getFeitos() {
		return feitos;
	}

	public void setFeitos(Integer feitos) {
		this.feitos = feitos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}