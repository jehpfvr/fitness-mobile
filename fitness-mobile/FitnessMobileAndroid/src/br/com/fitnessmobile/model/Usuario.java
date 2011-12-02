package br.com.fitnessmobile.model;

import br.com.fitnessmobile.adapter.enums.UsuarioCampos;

public class Usuario {
	private Integer id;
	private float peso;
	private float bicepsEsquerdo;
	private float bicepsDireito;
	private float tricepsEsquerdo;
	private float tricepsDireito;
	private float cintura;
	private float peitoral;
	private float coxaEsquerda;
	private float coxaDireita;
	private float panturrilhaEsquerda;
	private float panturrilhaDireita;
	private float quadril;
	private String data;
	
	public static String[] colunas = {
		UsuarioCampos.ID.getCampo(), UsuarioCampos.PESO.getCampo(), UsuarioCampos.BICEPS_ESQUERDO.getCampo(), UsuarioCampos.BICEPS_DIREITO.getCampo(), UsuarioCampos.TRICEPS_ESQUERDO.getCampo(), UsuarioCampos.TRICEPS_DIREITO.getCampo(), UsuarioCampos.CINTURA.getCampo(), UsuarioCampos.PEITORAL.getCampo(), UsuarioCampos.COXA_ESQUERDA.getCampo(), UsuarioCampos.COXA_DIREITA.getCampo(), UsuarioCampos.PANTURRILHA_ESQUERDA.getCampo(), UsuarioCampos.PANTURRILHA_DIREITA.getCampo(), UsuarioCampos.QUADRIL.getCampo(), UsuarioCampos.DATA.getCampo()
	};
	
	public Usuario(){
		
	}
	
	public Usuario(float peso, float bicepsEsquerdo, float bicepsDireito, float tricepsEsquerdo, float tricepsDireito, float cintura, float peitoral, float coxaEsquerda, float coxaDireita, float panturrilhaEsquerda, float panturrilhaDireita, float quadril, String data){
		super();
		this.peso = peso;
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
	
	public Usuario(Integer id, float peso, float bicepsEsquerdo, float bicepsDireito, float tricepsEsquerdo, float tricepsDireito, float cintura, float peitoral, float coxaEsquerda, float coxaDireita, float panturrilhaEsquerda, float panturrilhaDireita, float quadril, String data){
		super();
		this.id = id;
		this.peso = peso;
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public float getBicepsEsquerdo() {
		return bicepsEsquerdo;
	}
	public void setBicepsEsquerdo(float bicepsEsquerdo) {
		this.bicepsEsquerdo = bicepsEsquerdo;
	}
	public float getBicepsDireito() {
		return bicepsDireito;
	}
	public void setBicepsDireito(float bicepsDireito) {
		this.bicepsDireito = bicepsDireito;
	}
	public float getTricepsEsquerdo() {
		return tricepsEsquerdo;
	}
	public void setTricepsEsquerdo(float tricepsEsquerdo) {
		this.tricepsEsquerdo = tricepsEsquerdo;
	}
	public float getTricepsDireito() {
		return tricepsDireito;
	}
	public void setTricepsDireito(float tricepsDireito) {
		this.tricepsDireito = tricepsDireito;
	}
	public float getCintura() {
		return cintura;
	}
	public void setCintura(float cintura) {
		this.cintura = cintura;
	}
	public float getPeitoral() {
		return peitoral;
	}
	public void setPeitoral(float peitoral) {
		this.peitoral = peitoral;
	}
	public float getCoxaEsquerda() {
		return coxaEsquerda;
	}
	public void setCoxaEsquerda(float coxaEsquerda) {
		this.coxaEsquerda = coxaEsquerda;
	}
	public float getCoxaDireita() {
		return coxaDireita;
	}
	public void setCoxaDireita(float coxaDireita) {
		this.coxaDireita = coxaDireita;
	}
	public float getPanturrilhaEsquerda() {
		return panturrilhaEsquerda;
	}
	public void setPanturrilhaEsquerda(float panturrilhaEsquerda) {
		this.panturrilhaEsquerda = panturrilhaEsquerda;
	}
	public float getPanturrilhaDireita() {
		return panturrilhaDireita;
	}
	public void setPanturrilhaDireita(float panturrilhaDireita) {
		this.panturrilhaDireita = panturrilhaDireita;
	}
	public float getQuadril() {
		return quadril;
	}
	public void setQuadril(float quadril) {
		this.quadril = quadril;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
