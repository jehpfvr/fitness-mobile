package br.com.fitnessmobile.model;

import br.com.fitnessmobile.adapter.enums.EtapaExercicioCampos;

public class EtapaExercicio {

	private Integer id;
	private Exercicio exercicio;
	private Integer etapaid;
	private Integer flag;
	private Integer tipoID;
	public static String[] colunas = {
		EtapaExercicioCampos.ID.getCampo(),
		EtapaExercicioCampos.ETAPA_ID.getCampo(),
		EtapaExercicioCampos.EXERCICIO_ID.getCampo(),
		EtapaExercicioCampos.FLAG.getCampo(),
		EtapaExercicioCampos.TIPO_ID.getCampo()
	};
	
	public EtapaExercicio() { }

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEtapaid() {
		return etapaid;
	}
	public void setEtapaid(Integer etapaid) {
		this.etapaid = etapaid;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Exercicio getExercicio() {
		return exercicio;
	}
	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}
	public Integer getTipoID() {
		return tipoID;
	}
	public void setTipoID(Integer tipoID) {
		this.tipoID = tipoID;
	}
}