package br.com.fitnessmobile.model;

import java.io.Serializable;

public class ExercicioRealizado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private long realizacao;
	private EtapaExercicio etapaExercicio;
	private Aerobico aerobico;
	private Anaerobico anaerobico;
	
	public ExercicioRealizado() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public long getRealizacao() {
		return realizacao;
	}

	public void setRealizacao(long realizacao) {
		this.realizacao = realizacao;
	}

	public EtapaExercicio getEtapaExercicio() {
		return etapaExercicio;
	}

	public void setEtapaExercicio(EtapaExercicio etapaExercicio) {
		this.etapaExercicio = etapaExercicio;
	}

	public Aerobico getAerobico() {
		return aerobico;
	}

	public void setAerobico(Aerobico aerobico) {
		this.aerobico = aerobico;
	}

	public Anaerobico getAnaerobico() {
		return anaerobico;
	}

	public void setAnaerobico(Anaerobico anaerobico) {
		this.anaerobico = anaerobico;
	}	

}
