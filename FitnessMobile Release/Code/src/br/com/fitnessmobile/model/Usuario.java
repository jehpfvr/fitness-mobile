package br.com.fitnessmobile.model;

public class Usuario {
	private long id;
	private String peso;
	private String altura;
	private String bicepsEsquerdo;
	private String bicepsDireito;
	private String tricepsEsquerdo;
	private String tricepsDireito;
	private String cintura;
	private String peitoral;
	private String coxaEsquerda;
	private String coxaDireita;
	private String panturrilhaEsquerda;
	private String panturrilhaDireita;
	private String quadril;
	private String data;
	
	public static String[] colunas = {
		UsuarioCampo.ID.getCampo(), UsuarioCampo.PESO.getCampo(), UsuarioCampo.ALTURA.getCampo(), UsuarioCampo.BICEPS_ESQUERDO.getCampo(), UsuarioCampo.BICEPS_DIREITO.getCampo(), UsuarioCampo.TRICEPS_ESQUERDO.getCampo(), UsuarioCampo.TRICEPS_DIREITO.getCampo(), UsuarioCampo.CINTURA.getCampo(), UsuarioCampo.PEITORAL.getCampo(), UsuarioCampo.COXA_ESQUERDA.getCampo(), UsuarioCampo.COXA_ESQUERDA.getCampo(), UsuarioCampo.PANTURRILHA_ESQUERDA.getCampo(), UsuarioCampo.PANTURRILHA_DIREITA.getCampo(), UsuarioCampo.CINTURA.getCampo(), UsuarioCampo.DATA.getCampo()
	};
	
	public Usuario(){
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getBicepsEsquerdo() {
		return bicepsEsquerdo;
	}
	public void setBicepsEsquerdo(String bicepsEsquerdo) {
		this.bicepsEsquerdo = bicepsEsquerdo;
	}
	public String getBicepsDireito() {
		return bicepsDireito;
	}
	public void setBicepsDireito(String bicepsDireito) {
		this.bicepsDireito = bicepsDireito;
	}
	public String getTricepsEsquerdo() {
		return tricepsEsquerdo;
	}
	public void setTricepsEsquerdo(String tricepsEsquerdo) {
		this.tricepsEsquerdo = tricepsEsquerdo;
	}
	public String getTricepsDireito() {
		return tricepsDireito;
	}
	public void setTricepsDireito(String tricepsDireito) {
		this.tricepsDireito = tricepsDireito;
	}
	public String getCintura() {
		return cintura;
	}
	public void setCintura(String cintura) {
		this.cintura = cintura;
	}
	public String getPeitoral() {
		return peitoral;
	}
	public void setPeitoral(String peitoral) {
		this.peitoral = peitoral;
	}
	public String getCoxaEsquerda() {
		return coxaEsquerda;
	}
	public void setCoxaEsquerda(String coxaEsquerda) {
		this.coxaEsquerda = coxaEsquerda;
	}
	public String getCoxaDireita() {
		return coxaDireita;
	}
	public void setCoxaDireita(String coxaDireita) {
		this.coxaDireita = coxaDireita;
	}
	public String getPanturrilhaEsquerda() {
		return panturrilhaEsquerda;
	}
	public void setPanturrilhaEsquerda(String panturrilhaEsquerda) {
		this.panturrilhaEsquerda = panturrilhaEsquerda;
	}
	public String getPanturrilhaDireita() {
		return panturrilhaDireita;
	}
	public void setPanturrilhaDireita(String panturrilhaDireita) {
		this.panturrilhaDireita = panturrilhaDireita;
	}
	public String getQuadril() {
		return quadril;
	}
	public void setQuadril(String quadril) {
		this.quadril = quadril;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
