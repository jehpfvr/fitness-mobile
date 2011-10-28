package br.com.fitnessmobile.adapter.enums;

import java.util.EnumSet;

import android.util.Log;
import br.com.fitnessmobile.R;

public enum Musculo {
	
	// Declaracao das Constantes (Sintaxe: Id do Musculo fixo, Nome do Musculo, int Icone)
	
	
	//Área alta
	TRAPEZIO (1, "Trapezio", R.drawable.trapezio),
	DELTOIDE (2, "Deltoide", R.drawable.deltoide),
	BICEPS (3, "Biceps", R.drawable.biceps),
	TRICEPS (4, "Triceps", R.drawable.triceps),
	ANTEBRACO (5, "Antebraco", R.drawable.antebracos),
	
	//Área central
	PEITORAL (6, "Peitoral", R.drawable.icon),
	ABDOMINAIS (7, "Abdominais", R.drawable.icon),
	ABDOMINAIS_OBLIQUOS(8,"Abdominais_Obliquos",R.drawable.icon),
	DORSAIS(9,"Dorsais",R.drawable.icon),
	LOMBARES(10,"Lombares",R.drawable.icon),
	
	//ÁREA baixa
	GLUTEOS(11,"Gluteos",R.drawable.icon),
	ADUTORES(12,"Adutores",R.drawable.icon),
	QUADRICEPS (13, "Quadriceps", R.drawable.icon),
	ISQUIOTIBIAIS(14,"Isquiotibiais",R.drawable.icon),
	PANTURRILHA (15, "Panturrilha", R.drawable.icon);
	
	// Definicao das Constantes
	private final int musculo_id;
	private final String musculo_nome;
	private final int musculo_icone;
	
	// Metodos para acessar os valores
	public int getMusculoId() {
		return this.musculo_id;
	}
	public String getMusculoNome() {
		return this.musculo_nome;
	}
	public int getMusculoIcone() {
		return this.musculo_icone;
	}
	
	// Metodos que define as constantes
	Musculo(int musculoId, String musculoNome, int musculoIcone) {
		this.musculo_id = musculoId;
		this.musculo_nome = musculoNome;
		this.musculo_icone = musculoIcone;
	}
	public static Musculo getEnumById(String musculo) {
		for (Musculo m : EnumSet.allOf(Musculo.class)) {
			if (m.getMusculoId() == Integer.parseInt(musculo))
				return m;
		}
		Log.i("FitnessMusculoEnum", "Erro ao encontra Musculo com ID " + musculo);
		return null;
	}
	public static Musculo getEnumByNome(String nome) {
		for (Musculo m : EnumSet.allOf(Musculo.class)) {
			if (m.getMusculoNome().equals(nome))
				return m;
		}
		Log.i("FitnessMusculoEnum", "Erro ao encontra Musculo com Nome " + nome);
		return null;
	}
}