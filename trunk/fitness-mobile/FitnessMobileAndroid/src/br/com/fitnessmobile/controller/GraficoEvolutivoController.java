package br.com.fitnessmobile.controller;

import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import br.com.fitnessmobile.dao.UsuarioDao;
import br.com.fitnessmobile.model.MusculoGrafico;
import br.com.fitnessmobile.model.Usuario;

public class GraficoEvolutivoController {
	
	private UsuarioDao usuarioDao;
	
	/**
	 * Músculos do gráfico frontal
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
		usuarioDao = new UsuarioDao(context);
	}
	
	public void geraRelatorio(String dataDeInicio, String dataDeFim) {
		
		/**
		 * Objeto representando as medidas da primeira data escolhida
		 */
		Usuario usuarioMedidaInicial = new Usuario();
		
		usuarioMedidaInicial = usuarioDao.getUsuarioByDate(dataDeInicio);
		Log.v("Teste", "Pegou!");
		
		this.bicepsEsquerdoInicial 			= new MusculoGrafico("Bíceps Esquerdo", 92, 122, Float.parseFloat(usuarioMedidaInicial.getBicepsEsquerdo()));
		
		this.bicepsDireitoInicial 			= new MusculoGrafico("Bíceps Direito", 60, 122, Float.parseFloat(usuarioMedidaInicial.getBicepsDireito()));
		this.peitoralInicial 				= new MusculoGrafico("Peitoral", 68, 117, Float.parseFloat(usuarioMedidaInicial.getPeitoral()));
		this.abdomenInicial 				= new MusculoGrafico("Abdomen", 70, 125, Float.parseFloat(usuarioMedidaInicial.getCintura()));
		this.coxaEsquerdaInicial 			= new MusculoGrafico("Coxa Esquerda", 68, 148, Float.parseFloat(usuarioMedidaInicial.getCoxaEsquerda()));
		this.coxaDireitaInicial 			= new MusculoGrafico("Coxa Direita", 83, 148, Float.parseFloat(usuarioMedidaInicial.getCoxaDireita()));
		this.tricepsEsquerdoInicial 		= new MusculoGrafico("Triceps Esquerdo", 127, 120, Float.parseFloat(usuarioMedidaInicial.getTricepsEsquerdo()));
		this.tricepsDireitoInicial 			= new MusculoGrafico("Triceps Direito", 163, 120, Float.parseFloat(usuarioMedidaInicial.getTricepsDireito()));
		this.panturrilhaEsquerdaInicial 	= new MusculoGrafico("Panturrilha Esquerda", 154, 182, Float.parseFloat(usuarioMedidaInicial.getPanturrilhaEsquerda()));
		this.panturrilhaDireitaInicial 		= new MusculoGrafico("Panturrilha Direita", 133, 182, Float.parseFloat(usuarioMedidaInicial.getPanturrilhaDireita()));

		/**
		 * Objeto representando as medidas da segunda data escolhida
		 */
		Usuario usuarioMedidaFinal = new Usuario();

		usuarioMedidaFinal = usuarioDao.getUsuarioByDate(dataDeFim);
		
		Log.v("data_de_fim", dataDeFim);
		
		this.bicepsEsquerdoFinal 			= new MusculoGrafico("Bíceps Esquerdo", 92, 122, Float.parseFloat(usuarioMedidaFinal.getBicepsEsquerdo()));
		this.bicepsDireitoFinal 			= new MusculoGrafico("Bíceps Direito", 60, 122, Float.parseFloat(usuarioMedidaFinal.getBicepsDireito()));
		this.peitoralFinal 					= new MusculoGrafico("Peitoral", 68, 117, Float.parseFloat(usuarioMedidaFinal.getPeitoral()));
		this.abdomenFinal 					= new MusculoGrafico("Abdomen", 70, 125, Float.parseFloat(usuarioMedidaFinal.getCintura()));
		this.coxaEsquerdaFinal 				= new MusculoGrafico("Coxa Esquerda", 68, 148, Float.parseFloat(usuarioMedidaFinal.getCoxaEsquerda()));
		this.coxaDireitaFinal 				= new MusculoGrafico("Coxa Direita", 83, 148, Float.parseFloat(usuarioMedidaFinal.getCoxaDireita()));
		this.tricepsEsquerdoFinal 			= new MusculoGrafico("Triceps Esquerdo", 127, 120, Float.parseFloat(usuarioMedidaFinal.getTricepsEsquerdo()));
		this.tricepsDireitoFinal 			= new MusculoGrafico("Triceps Direito", 163, 120, Float.parseFloat(usuarioMedidaFinal.getTricepsDireito()));
		this.panturrilhaEsquerdaFinal 		= new MusculoGrafico("Panturrilha Esquerda", 154, 182, Float.parseFloat(usuarioMedidaFinal.getPanturrilhaEsquerda()));
		this.panturrilhaDireitaFinal 		= new MusculoGrafico("Panturrilha Direita", 133, 182, Float.parseFloat(usuarioMedidaFinal.getPanturrilhaDireita()));
		
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

	public void setPanturilhaDireitaInicial(MusculoGrafico panturilhaDireitaInicial) {
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

	public void setPanturrilhaEsquerdaFinal(MusculoGrafico panturrilhaEsquerdaFinal) {
		this.panturrilhaEsquerdaFinal = panturrilhaEsquerdaFinal;
	}

	public MusculoGrafico getPanturrilhaDireitaFinal() {
		return panturrilhaDireitaFinal;
	}

	public void setPanturrilhaDireitaFinal(MusculoGrafico panturrilhaDireitaFinal) {
		this.panturrilhaDireitaFinal = panturrilhaDireitaFinal;
	}

}