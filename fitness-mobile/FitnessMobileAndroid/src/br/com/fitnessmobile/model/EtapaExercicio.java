package br.com.fitnessmobile.model;

import java.io.Serializable;

import br.com.fitnessmobile.adapter.enums.EtapaExercicioCampos;

public class EtapaExercicio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Exercicio exercicio;
	private Integer etapaid;
	private Integer flag;
	private Integer tipoID;
	private Integer diaID;
	private long dtBaixa;
	public static String[] colunas = {
		EtapaExercicioCampos.ID.getCampo(),
		EtapaExercicioCampos.ETAPA_ID.getCampo(),
		EtapaExercicioCampos.EXERCICIO_ID.getCampo(),
		EtapaExercicioCampos.FLAG.getCampo(),
		EtapaExercicioCampos.TIPO_ID.getCampo(),
		EtapaExercicioCampos.DIA_ID.getCampo(),
		EtapaExercicioCampos.DATA_BAIXA.getCampo()
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
	public Integer getDiaID() {
		return diaID;
	}
	public void setDiaID(Integer diaID) {
		this.diaID = diaID;
	}
	public long getDtBaixa() {
		return dtBaixa;
	}
	public void setDtBaixa(long dtBaixa) {
		this.dtBaixa = dtBaixa;
	}
}