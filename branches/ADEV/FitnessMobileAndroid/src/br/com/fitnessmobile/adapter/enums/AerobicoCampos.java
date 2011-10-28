package br.com.fitnessmobile.adapter.enums;

public enum AerobicoCampos {
	
	ID ("_id"),
	DISTANCIA ("distancia"),
	DURACAO ("duracao"),
	VELOCIDADE("velocidade");
	
	private String campo;
	
	private AerobicoCampos(String campo) {
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

}
