package br.com.fitnessmobile.model;

import br.com.fitnessmobile.adapter.enums.UsuarioCampos;

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
		UsuarioCampos.ID.getCampo(), UsuarioCampos.PESO.getCampo(), UsuarioCampos.ALTURA.getCampo(), UsuarioCampos.BICEPS_ESQUERDO.getCampo(), UsuarioCampos.BICEPS_DIREITO.getCampo(), UsuarioCampos.TRICEPS_ESQUERDO.getCampo(), UsuarioCampos.TRICEPS_DIREITO.getCampo(), UsuarioCampos.CINTURA.getCampo(), UsuarioCampos.PEITORAL.getCampo(), UsuarioCampos.COXA_ESQUERDA.getCampo(), UsuarioCampos.COXA_DIREITA.getCampo(), UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo(), UsuarioCampos.PANTURRILHA_DIREITA.getCampo(), UsuarioCampos.QUADRIL.getCampo(), UsuarioCampos.DATA.getCampo()
	};
	
	public Usuario(){
		
	}
	
	public Usuario(String peso, String altura, String bicepsEsquerdo, String bicepsDireito, String tricepsEsquerdo, String tricepsDireito, String cintura, String peitoral, String coxaEsquerda, String coxaDireita, String panturrilhaEsquerda, String panturrilhaDireita, String quadril, String data){
		super();
		this.peso = peso;
		this.altura = altura;
		this.bicepsEsquerdo = bicepsEsquerdo;
		this.bicepsDireito = bicepsDireito;
		this.tricepsEsquerdo = tricepsEsquerdo;
		this.tricepsDireito = tricepsDireito;
		this.cintura = cintura;
		this.peitoral = peitoral;
		this.coxaEsquerda = coxaEsquerda;
		this.coxaDireita = coxaDireita;
		this.panturrilhaEsquerda = panturrilhaEsquerda;
		this.panturrilhaDireita = panturrilhaDireita;
		this.quadril = quadril;
		this.data = data;
	}	
	
	public Usuario(long id, String peso, String altura, String bicepsEsquerdo, String bicepsDireito, String tricepsEsquerdo, String tricepsDireito, String cintura, String peitoral, String coxaEsquerda, String coxaDireita, String panturrilhaEsquerda, String panturrilhaDireita, String quadril, String data){
		super();
		this.id = id;
		this.peso = peso;
		this.altura = altura;
		this.bicepsEsquerdo = bicepsEsquerdo;
		this.bicepsDireito = bicepsDireito;
		this.tricepsEsquerdo = tricepsEsquerdo;
		this.tricepsDireito = tricepsDireito;
		this.cintura = cintura;
		this.peitoral = peitoral;
		this.coxaEsquerda = coxaEsquerda;
		this.coxaDireita = coxaDireita;
		this.panturrilhaEsquerda = panturrilhaEsquerda;
		this.panturrilhaDireita = panturrilhaDireita;
		this.quadril = quadril;
		this.data = data;
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
