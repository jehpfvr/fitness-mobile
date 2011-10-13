package br.com.fitnessmobile.adapter.enums;

import java.util.EnumSet;

import android.util.Log;
import br.com.fitnessmobile.R;

public enum Musculo {
	
	// Declaracao das Constantes (Sintaxe: Id do Musculo fixo, Nome do Musculo, int Icone)
	BICEPS (1, "Biceps", R.drawable.icon),
	TRICEPS (2, "Triceps", R.drawable.icon),
	COSTA (3, "Costa", R.drawable.icon),
	PEITORAL (4, "Peitoral", R.drawable.icon),
	ANTEBRACO (5, "Antebraco", R.drawable.icon),
	ABDOME (6, "Abdome", R.drawable.icon),
	COXA (7, "Coxa", R.drawable.icon),
	PANTURRILHA (8, "Panturrilha", R.drawable.icon);
	
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