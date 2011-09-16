package br.com.fitnessbd;

public class Programa {
	private long id;
	private String nome;
	private String DataInicial;
	private String DataFinal;
	public static String[] colunas = {
		ProgramaCampos.ID.getCampo(), ProgramaCampos.NOME.getCampo(), ProgramaCampos.DATA_INICIAL.getCampo(), ProgramaCampos.DATA_FINAL.getCampo()
	};
	
	public Programa() {
		
	}
	
	
	public Programa(String nome, String dataInicial, String dataFinal) {
		super();
		this.nome = nome;
		DataInicial = dataInicial;
		DataFinal = dataFinal;
	}



	public Programa(int id, String nome, String dataInicial, String dataFinal) {
		super();
		this.id = id;
		this.nome = nome;
		DataInicial = dataInicial;
		DataFinal = dataFinal;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDataInicial() {
		return DataInicial;
	}


	public void setDataInicial(String dataInicial) {
		DataInicial = dataInicial;
	}


	public String getDataFinal() {
		return DataFinal;
	}


	public void setDataFinal(String dataFinal) {
		DataFinal = dataFinal;
	}

}
