package controller;

import android.app.Activity;
import br.com.fitnessmobile.R;
import br.com.fitnessmobile.model.ModoVisualizacao;

public class ControladorModoVisualizacao extends Activity {

	private ModoVisualizacao modoVisualizacao = ModoVisualizacao.getInstance();
	
	/**
	 * Pega o modo de vizualização atual
	 * @return
	 */
	public String getModoVisualizacaoAtual() {
		this.modoVisualizacao.setTema(modoVisualizacao.getTema());
		return this.modoVisualizacao.getTema();
	}
	/**
	 * Efetua a mudança do modo de vizualização
	 * @param tema
	 */
	public void setModoVisualizacao(String tema) {
		if (tema.equals("Noturno")) {
			try {
				if (this.getModoVisualizacaoAtual() == "Noturno") {
					this.setContentView(R.string.Modo_de_visualizacao_ja_em_uso);
				} else if (this.getModoVisualizacaoAtual() == "Diurno") {
					this.setContentView(R.layout.modo_visualizacao_diurno);
					this.setContentView(R.string.Modo_de_visualizacao_alterado);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (tema.equals("Diurno")) {
			try {
				if (this.getModoVisualizacaoAtual() == "Diurno") {
					this.setContentView(R.string.Modo_de_visualizacao_ja_em_uso);
				} else if (this.getModoVisualizacaoAtual() == "Noturno") {
					this.setContentView(R.layout.modo_visualizacao_noturno);
					this.setContentView(R.string.Modo_de_visualizacao_alterado);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
