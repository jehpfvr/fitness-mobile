package br.com.fitnessmobile.model;

public class ModoVisualizacao {

	private String tema;
	public static ModoVisualizacao Instance;
	
	public ModoVisualizacao() { }
	
	public static ModoVisualizacao getInstance() {
		if (Instance == null)
			Instance = new ModoVisualizacao();
		
		return Instance;
	}
	
	public ModoVisualizacao(String tema) {
		this.tema = tema;
	}
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
}
