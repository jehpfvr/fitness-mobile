package br.com.fitnessmobile.model;

import java.io.Serializable;
import java.util.List;

import br.com.fitnessmobile.adapter.enums.ExercicioCampos;
import br.com.fitnessmobile.adapter.enums.Musculo;

public class Exercicio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String descricao;
	private Musculo musculoPrincipal;
	private String situacao = "A"; // Ativo ou Cancelado
	private String tipo; // Usado para N-Anaerobico e A-Aerobico
	private float indiceCalorico;
	private List<Musculo> grupoMuscular;
	public static String[] colunas = {
		ExercicioCampos.ID.getCampo(),
		ExercicioCampos.NOME.getCampo(),
		ExercicioCampos.DESCRICAO.getCampo(),
		ExercicioCampos.SITUACAO.getCampo(),
		ExercicioCampos.TIPO.getCampo(),
		ExercicioCampos.INDICE_CALORICO.getCampo(),
		ExercicioCampos.MUSCULO.getCampo(),
		ExercicioCampos.GRUPO_MUSCULAR.getCampo()
	};
	
	public Exercicio(Integer id, String nome, String descricao, Musculo musculoPrincipal, String situacao, String tipo, Integer indiceCalorico, List<Musculo> grupoMuscular) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.musculoPrincipal = musculoPrincipal;
		this.situacao = situacao;
		this.tipo = tipo;
		this.indiceCalorico = indiceCalorico;
		this.grupoMuscular = grupoMuscular;
	}
	
	public Exercicio() { }

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Musculo getMusculoPrincipal() {
		return musculoPrincipal;
	}
	public void setMusculoPrincipal(Musculo musculoPrincipal) {
		this.musculoPrincipal = musculoPrincipal;
	}
	public List<Musculo> getGrupoMuscular() {
		return grupoMuscular;
	}
	public void setGrupoMuscular(List<Musculo> grupoMuscular) {
		this.grupoMuscular = grupoMuscular;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public float getIndiceCalorico() {
		return indiceCalorico;
	}
	public void setIndiceCalorico(float indiceCalorico) {
		this.indiceCalorico = indiceCalorico;
	}
	
	// Para o ListView
	public String toString() {
		return this.getNome();
	}
}