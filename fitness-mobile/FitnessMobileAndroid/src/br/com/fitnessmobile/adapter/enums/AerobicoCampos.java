package br.com.fitnessmobile.adapter.enums;

public enum AerobicoCampos {

	ID ("ear_id"),
	DISTANCIA ("ear_distancia"),
	DURACAO ("ear_duracao"),
	VELOCIDADE("ear_velocidade"),
	EXR_ID ("ear_exr_id");

	private String campo;

	private AerobicoCampos(String campo) {
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}

}
