package br.com.fitnessmobile.controller;

import android.content.Context;
import android.util.Log;
import br.com.fitnessmobile.dao.MedidasDao;
import br.com.fitnessmobile.model.MusculoGrafico;
import br.com.fitnessmobile.model.Usuario;

public class GraficoEvolutivoController {

	private MedidasDao usuarioDao;

	/**
	 * Musculos do gráfico frontal
	 */
	private MusculoGrafico trapezioInicial;
	private MusculoGrafico ombroEsquerdoInicial;
	private MusculoGrafico ombroDireitoInicial;
	private MusculoGrafico bicepsEsquerdoInicial;
	private MusculoGrafico bicepsDireitoInicial;
	private MusculoGrafico peitoralInicial;
	private MusculoGrafico abdomenInicial;
	private MusculoGrafico coxaEsquerdaInicial;
	private MusculoGrafico coxaDireitaInicial;
	private MusculoGrafico tricepsEsquerdoInicial;
	private MusculoGrafico tricepsDireitoInicial;
	private MusculoGrafico panturrilhaEsquerdaInicial;
	private MusculoGrafico panturrilhaDireitaInicial;
	/**
	 * Músculos da parte de trás do gráfico
	 */
	private MusculoGrafico trapezioFinal;
	private MusculoGrafico ombroEsquerdoFinal;
	private MusculoGrafico ombroDireitoFinal;
	private MusculoGrafico bicepsEsquerdoFinal;
	private MusculoGrafico bicepsDireitoFinal;
	private MusculoGrafico peitoralFinal;
	private MusculoGrafico abdomenFinal;
	private MusculoGrafico coxaEsquerdaFinal;
	private MusculoGrafico coxaDireitaFinal;
	private MusculoGrafico tricepsEsquerdoFinal;
	private MusculoGrafico tricepsDireitoFinal;
	private MusculoGrafico panturrilhaEsquerdaFinal;
	private MusculoGrafico panturrilhaDireitaFinal;

	/**
	 * Instancia os músculos com valores diretos.
	 * 
	 * Isto é temporário. Existe apenas para suprir a necessidade de um DAO,
	 * até então, não criado.
	 */
	public GraficoEvolutivoController(Context context) {
		usuarioDao = new MedidasDao(context);
	}

	public void geraRelatorio(String dataDeInicio, String dataDeFim) {

		/**
		 * Objeto representando as medidas da primeira data escolhida
		 */
		Usuario usuarioMedidaInicial = new Usuario();

		try {
			usuarioMedidaInicial = usuarioDao.getUsuarioByDate(dataDeInicio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.v("Teste", "Pegou!");
		

		if ((Float) usuarioMedidaInicial.getBicepsEsquerdo() != null) {
			this.bicepsEsquerdoInicial = new MusculoGrafico("Biceps Esquerdo",
					92, 122, usuarioMedidaInicial
							.getBicepsEsquerdo());
		}

		if ((Float) usuarioMedidaInicial.getBicepsDireito() != null) {
			this.bicepsDireitoInicial = new MusculoGrafico("Biceps Direito",
					60, 122, usuarioMedidaInicial
							.getBicepsDireito());
		}

		if ((Float) usuarioMedidaInicial.getPeitoral() != null) {
			this.peitoralInicial = new MusculoGrafico("Peitoral", 68, 117,
					usuarioMedidaInicial.getPeitoral());
		}

		if ((Float) usuarioMedidaInicial.getCintura() != null) {
			this.abdomenInicial = new MusculoGrafico("Abdomen", 70, 125,
					usuarioMedidaInicial.getCintura());
		}

		if ((Float) usuarioMedidaInicial.getCoxaEsquerda() != null) {
			this.coxaEsquerdaInicial = new MusculoGrafico("Coxa Esquerda", 68,
					148,usuarioMedidaInicial
							.getCoxaEsquerda());
		}

		if ((Float) usuarioMedidaInicial.getCoxaDireita() != null) {
			this.coxaDireitaInicial = new MusculoGrafico("Coxa Direita", 83,
					148,
					usuarioMedidaInicial.getCoxaDireita());
		}

		if ((Float) usuarioMedidaInicial.getTricepsEsquerdo() != null) {
			this.tricepsEsquerdoInicial = new MusculoGrafico(
					"Triceps Esquerdo", 127, 120,
					usuarioMedidaInicial.getTricepsEsquerdo());
		}

		if ((Float) usuarioMedidaInicial.getTricepsDireito() != null) {
			this.tricepsDireitoInicial = new MusculoGrafico("Triceps Direito",
					163, 120, usuarioMedidaInicial
							.getTricepsDireito());
		}

		if ((Float) usuarioMedidaInicial.getPanturrilhaEsquerda() != null) {
			this.panturrilhaEsquerdaInicial = new MusculoGrafico(
					"Panturrilha Esquerda", 154, 182,
					usuarioMedidaInicial
							.getPanturrilhaEsquerda());
		}

		if ((Float) usuarioMedidaInicial.getPanturrilhaDireita() != null) {
			this.panturrilhaDireitaInicial = new MusculoGrafico(
					"Panturrilha Direita", 133, 182,
					usuarioMedidaInicial
							.getPanturrilhaDireita());
		}

		/**
		 * Objeto representando as medidas da segunda data escolhida
		 */
		Usuario usuarioMedidaFinal = new Usuario();

		try {
			usuarioMedidaFinal = usuarioDao.getUsuarioByDate(dataDeFim);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Log.v("medida do user", "" + usuarioMedidaFinal.getBicepsDireito());
		Log.v("data_de_fim", dataDeFim);

		if ((Float) usuarioMedidaFinal.getBicepsEsquerdo() != null) {
			this.bicepsEsquerdoFinal = new MusculoGrafico("Bíceps Esquerdo",
					92, 122, usuarioMedidaFinal
							.getBicepsEsquerdo());
		}

		if ((Float) usuarioMedidaFinal.getBicepsDireito() != null) {
			this.bicepsDireitoFinal = new MusculoGrafico("Bíceps Direito", 60,
					122,
					usuarioMedidaFinal.getBicepsDireito());
		}

		if ((Float) usuarioMedidaFinal.getPeitoral() != null) {
			this.peitoralFinal = new MusculoGrafico("Peitoral", 68, 117,
					usuarioMedidaFinal.getPeitoral());
		}

		if ((Float) usuarioMedidaFinal.getCintura() != null) {
			this.abdomenFinal = new MusculoGrafico("Abdomen", 70, 125,
					usuarioMedidaFinal.getCintura());
		}

		if ((Float) usuarioMedidaFinal.getCoxaEsquerda() != null) {
			this.coxaEsquerdaFinal = new MusculoGrafico("Coxa Esquerda", 68,
					148, usuarioMedidaFinal.getCoxaEsquerda());
		}

		if ((Float) usuarioMedidaFinal.getCoxaDireita() != null) {
			this.coxaDireitaFinal = new MusculoGrafico("Coxa Direita", 83, 148,
					usuarioMedidaFinal.getCoxaDireita());
		}

		if ((Float) usuarioMedidaFinal.getTricepsEsquerdo() != null) {
			this.tricepsEsquerdoFinal = new MusculoGrafico("Triceps Esquerdo",
					127, 120, usuarioMedidaFinal
							.getTricepsEsquerdo());
		}

		if ((Float) usuarioMedidaFinal.getTricepsDireito() != null) {
			this.tricepsDireitoFinal = new MusculoGrafico("Triceps Direito",
					163, 120, usuarioMedidaFinal
							.getTricepsDireito());
		}

		if ((Float) usuarioMedidaFinal.getPanturrilhaEsquerda() != null) {
			this.panturrilhaEsquerdaFinal = new MusculoGrafico(
					"Panturrilha Esquerda", 154, 182,
					usuarioMedidaFinal
							.getPanturrilhaEsquerda());
		}

		if ((Float) usuarioMedidaFinal.getPanturrilhaDireita() != null) {
			this.panturrilhaDireitaFinal = new MusculoGrafico(
					"Panturrilha Direita",
					133, 182,
					usuarioMedidaFinal.getPanturrilhaDireita());
		}

		usuarioDao.Fechar();
	}

	public MusculoGrafico getTrapezioInicial() {
		return trapezioInicial;
	}

	public void setTrapezioInicial(MusculoGrafico trapezioInicial) {
		this.trapezioInicial = trapezioInicial;
	}

	public MusculoGrafico getOmbroEsquerdoInicial() {
		return ombroEsquerdoInicial;
	}

	public void setOmbroEsquerdoInicial(MusculoGrafico ombroEsquerdoInicial) {
		this.ombroEsquerdoInicial = ombroEsquerdoInicial;
	}

	public MusculoGrafico getOmbroDireitoInicial() {
		return ombroDireitoInicial;
	}

	public void setOmbroDireitoInicial(MusculoGrafico ombroDireitoInicial) {
		this.ombroDireitoInicial = ombroDireitoInicial;
	}

	public MusculoGrafico getBicepsEsquerdoInicial() {
		return bicepsEsquerdoInicial;
	}

	public void setBicepsEsquerdoInicial(MusculoGrafico bicepsEsquerdoInicial) {
		this.bicepsEsquerdoInicial = bicepsEsquerdoInicial;
	}

	public MusculoGrafico getBicepsDireitoInicial() {
		return bicepsDireitoInicial;
	}

	public void setBicepsDireitoInicial(MusculoGrafico bicepsDireitoInicial) {
		this.bicepsDireitoInicial = bicepsDireitoInicial;
	}

	public MusculoGrafico getPeitoralInicial() {
		return peitoralInicial;
	}

	public void setPeitoralInicial(MusculoGrafico peitoralInicial) {
		this.peitoralInicial = peitoralInicial;
	}

	public MusculoGrafico getAbdomenInicial() {
		return abdomenInicial;
	}

	public void setAbdomenInicial(MusculoGrafico abdomenInicial) {
		this.abdomenInicial = abdomenInicial;
	}

	public MusculoGrafico getCoxaEsquerdaInicial() {
		return coxaEsquerdaInicial;
	}

	public void setCoxaEsquerdaInicial(MusculoGrafico coxaEsquerdaInicial) {
		this.coxaEsquerdaInicial = coxaEsquerdaInicial;
	}

	public MusculoGrafico getCoxaDireitaInicial() {
		return coxaDireitaInicial;
	}

	public void setCoxaDireitaInicial(MusculoGrafico coxaDireitaInicial) {
		this.coxaDireitaInicial = coxaDireitaInicial;
	}

	public MusculoGrafico getTricepsEsquerdoInicial() {
		return tricepsEsquerdoInicial;
	}

	public void setTricepsEsquerdoInicial(MusculoGrafico tricepsEsquerdoInicial) {
		this.tricepsEsquerdoInicial = tricepsEsquerdoInicial;
	}

	public MusculoGrafico getTricepsDireitoInicial() {
		return tricepsDireitoInicial;
	}

	public void setTricepsDireitoInicial(MusculoGrafico tricepsDireitoInicial) {
		this.tricepsDireitoInicial = tricepsDireitoInicial;
	}

	public MusculoGrafico getPanturrilhaEsquerdaInicial() {
		return panturrilhaEsquerdaInicial;
	}

	public void setPanturrilhaEsquerdaInicial(
			MusculoGrafico panturrilhaEsquerdaInicial) {
		this.panturrilhaEsquerdaInicial = panturrilhaEsquerdaInicial;
	}

	public MusculoGrafico getPanturrilhaDireitaInicial() {
		return panturrilhaDireitaInicial;
	}

	public void setPanturilhaDireitaInicial(
			MusculoGrafico panturilhaDireitaInicial) {
		this.panturrilhaDireitaInicial = panturilhaDireitaInicial;
	}

	public MusculoGrafico getTrapezioFinal() {
		return trapezioFinal;
	}

	public void setTrapezioFinal(MusculoGrafico trapezioFinal) {
		this.trapezioFinal = trapezioFinal;
	}

	public MusculoGrafico getOmbroEsquerdoFinal() {
		return ombroEsquerdoFinal;
	}

	public void setOmbroEsquerdoFinal(MusculoGrafico ombroEsquerdoFinal) {
		this.ombroEsquerdoFinal = ombroEsquerdoFinal;
	}

	public MusculoGrafico getOmbroDireitoFinal() {
		return ombroDireitoFinal;
	}

	public void setOmbroDireitoFinal(MusculoGrafico ombroDireitoFinal) {
		this.ombroDireitoFinal = ombroDireitoFinal;
	}

	public MusculoGrafico getBicepsEsquerdoFinal() {
		return bicepsEsquerdoFinal;
	}

	public void setBicepsEsquerdoFinal(MusculoGrafico bicepsEsquerdoFinal) {
		this.bicepsEsquerdoFinal = bicepsEsquerdoFinal;
	}

	public MusculoGrafico getBicepsDireitoFinal() {
		return bicepsDireitoFinal;
	}

	public void setBicepsDireitoFinal(MusculoGrafico bicepsDireitoFinal) {
		this.bicepsDireitoFinal = bicepsDireitoFinal;
	}

	public MusculoGrafico getPeitoralFinal() {
		return peitoralFinal;
	}

	public void setPeitoralFinal(MusculoGrafico peitoralFinal) {
		this.peitoralFinal = peitoralFinal;
	}

	public MusculoGrafico getAbdomenFinal() {
		return abdomenFinal;
	}

	public void setAbdomenFinal(MusculoGrafico abdomenFinal) {
		this.abdomenFinal = abdomenFinal;
	}

	public MusculoGrafico getCoxaEsquerdaFinal() {
		return coxaEsquerdaFinal;
	}

	public void setCoxaEsquerdaFinal(MusculoGrafico coxaEsquerdaFinal) {
		this.coxaEsquerdaFinal = coxaEsquerdaFinal;
	}

	public MusculoGrafico getCoxaDireitaFinal() {
		return coxaDireitaFinal;
	}

	public void setCoxaDireitaFinal(MusculoGrafico coxaDireitaFinal) {
		this.coxaDireitaFinal = coxaDireitaFinal;
	}

	public MusculoGrafico getTricepsEsquerdoFinal() {
		return tricepsEsquerdoFinal;
	}

	public void setTricepsEsquerdoFinal(MusculoGrafico tricepsEsquerdoFinal) {
		this.tricepsEsquerdoFinal = tricepsEsquerdoFinal;
	}

	public MusculoGrafico getTricepsDireitoFinal() {
		return tricepsDireitoFinal;
	}

	public void setTricepsDireitoFinal(MusculoGrafico tricepsDireitoFinal) {
		this.tricepsDireitoFinal = tricepsDireitoFinal;
	}

	public MusculoGrafico getPanturrilhaEsquerdaFinal() {
		return panturrilhaEsquerdaFinal;
	}

	public void setPanturrilhaEsquerdaFinal(
			MusculoGrafico panturrilhaEsquerdaFinal) {
		this.panturrilhaEsquerdaFinal = panturrilhaEsquerdaFinal;
	}

	public MusculoGrafico getPanturrilhaDireitaFinal() {
		return panturrilhaDireitaFinal;
	}

	public void setPanturrilhaDireitaFinal(
			MusculoGrafico panturrilhaDireitaFinal) {
		this.panturrilhaDireitaFinal = panturrilhaDireitaFinal;
	}

}