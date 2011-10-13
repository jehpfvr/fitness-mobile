package br.com.fitnessmobile.model;

import java.io.Serializable;

public class Agenda implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String diaSemana;
	
	public Agenda() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}	

}
