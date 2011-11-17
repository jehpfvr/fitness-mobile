package br.com.fitnessmobile.model;

import java.io.Serializable;

import br.com.fitnessmobile.adapter.enums.EtapaCampos;

public class Etapa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private Integer programaID;
	private long dataInicio;
	private long dataFim;
	
	public static String[] colunas = {
		EtapaCampos.ID.getCampo(),
		EtapaCampos.NOME.getCampo(),
		EtapaCampos.PROGRAMA_ID.getCampo(),
		EtapaCampos.DATA_INICIAL.getCampo(),
		EtapaCampos.DATA_FINAL.getCampo()
	};
	
	public Etapa() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getProgramaID() {
		return programaID;
	}

	public void setProgramaID(Integer programaID) {
		this.programaID = programaID;
	}
	
	public long getDataInicio() {
		return dataInicio;
	}

	public long getDataFim() {
		return dataFim;
	}

	public void setDataInicio(long time) {
		this.dataInicio = time;
	}

	public void setDataFim(long time) {
		this.dataFim = time;
	}	
}