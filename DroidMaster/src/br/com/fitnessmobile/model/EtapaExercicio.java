package br.com.fitnessmobile.model;

import java.io.Serializable;
import java.util.List;

public class EtapaExercicio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Aerobico aerobico;
	private Anaerobico anaerobico;
	private Etapa etapa;
	private String nome;
	private List<Exercicio> exercicios;
	
	public EtapaExercicio() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(List<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Etapa getEtapa() {
		return etapa;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}
	
	
	
}
