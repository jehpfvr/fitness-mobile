package br.com.fitnessmobile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Dao {
	
	protected static final String CATEGORIA = "fitness";

	// Script para fazer drop na tabela
	private static final String[] SCRIPT_DATABASE_DELETE = new String[] { 
		"DROP TABLE IF EXISTS exercicio_aerobico_realizado",
		"DROP TABLE IF EXISTS exercicio_realizado",
		"DROP TABLE IF EXISTS anaerobico",
		"DROP TABLE IF EXISTS etapa_exercicio",
		"DROP TABLE IF EXISTS etapa",
		"DROP TABLE IF EXISTS configuracao",
		"DROP TABLE IF EXISTS medida",
		"DROP TABLE IF EXISTS pessoa",
		"DROP TABLE IF EXISTS programa",
		"DROP TABLE IF EXISTS exercicio"
	};

	// Cria a tabela com o "_id" sequencial
	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"CREATE TABLE IF NOT EXISTS exercicio ('exe_id' INTEGER PRIMARY KEY, 'exe_nome' VARCHAR(45) NOT NULL, 'exe_descricao' VARCHAR(45) NULL, 'exe_situacao' CHAR(1) NOT NULL, 'exe_tipo' CHAR(1) NOT NULL, 'exe_indice_calorico' DECIMAL(5,2) NULL, 'exe_musculo' CHAR(1) NOT NULL, 'exe_grupomuscular' VARCHAR(45) NOT NULL);",
		"CREATE TABLE IF NOT EXISTS programa ('pro_id' INTEGER PRIMARY KEY, 'pro_nome' VARCHAR(50) NOT NULL, 'pro_data_inicial' INTEGER  NOT NULL, 'pro_data_final' INTEGER  NOT NULL);",
		"CREATE TABLE IF NOT EXISTS pessoa ('pes_id' INTEGER PRIMARY KEY, 'pes_nome' VARCHAR(45) NOT NULL, 'pes_data_nasc' INTEGER  NOT NULL, 'pes_peso' VARCHAR(45) NULL, 'pes_altura' VARCHAR(45) NULL, 'pes_sexo' VARCHAR(1) NOT NULL);",
		"CREATE TABLE IF NOT EXISTS medida ('med_id' INTEGER, 'med_data_cadastro' INTEGER  NOT NULL, 'med_pes_id' INTEGER NOT NULL, 'med_musculo' INTEGER NOT NULL, PRIMARY KEY ('med_id', 'med_pes_id'), CONSTRAINT 'fk_medida_pessoa' FOREIGN KEY ('med_pes_id' ) REFERENCES pessoa ('pes_id'));",
		"CREATE TABLE IF NOT EXISTS configuracao ('con_id' INTEGER, 'con_metragem' VARCHAR(45) NOT NULL, 'con_kilometragem' VARCHAR(45) NOT NULL, 'con_pesagem' VARCHAR(45) NOT NULL, PRIMARY KEY ('con_id') );",
		"CREATE TABLE IF NOT EXISTS etapa ('eta_id' INTEGER, 'eta_nome' VARCHAR(50) NOT NULL, 'eta_pro_id' INTEGER NOT NULL,'eta_data_inicial' INTEGER NOT NULL, 'eta_data_final' INTEGER NOT NULL, PRIMARY KEY ('eta_id'),  CONSTRAINT 'fk_etapa_programa' FOREIGN KEY ('eta_pro_id' ) REFERENCES programa ('pro_id' ));",
		"CREATE TABLE IF NOT EXISTS etapa_exercicio ('ete_tipo_id' INTEGER NOT NULL,'ete_id' INTEGER, 'ete_eta_id' INTEGER NOT NULL, 'ete_exe_id' INTEGER NOT NULL,'ete_flag' CHAR(1),'ete_diaid' INTEGER NOT NULL,'ete_dtbaixa' INTEGER, PRIMARY KEY ('ete_id'), CONSTRAINT 'fk_ete_eta' FOREIGN KEY ('ete_eta_id' ) REFERENCES etapa ('eta_id' ), CONSTRAINT 'fk_ete_exe' FOREIGN KEY ('ete_exe_id' ) REFERENCES exercicio ('exe_id'));",
		"CREATE TABLE IF NOT EXISTS anaerobico ('ana_id' INTEGER, 'ana_repeticoes' INTEGER NOT NULL, 'ana_peso' DECIMAL(5,2) NOT NULL, 'ana_serie' INTEGER NOT NULL, 'ana_ete_id' INTEGER NOT NULL, PRIMARY KEY ('ana_id'), CONSTRAINT 'fk_ana_ete' FOREIGN KEY ('ana_ete_id' ) REFERENCES etapa_exercicio ('ete_id'));",
		"CREATE TABLE IF NOT EXISTS exercicio_realizado ('exr_id' INTEGER, 'exr_data_realizacao' INTEGER NOT NULL, 'exr_ete_id' INTEGER NOT NULL, PRIMARY KEY ('exr_id'), CONSTRAINT 'fk_exr_ete' FOREIGN KEY ('exr_ete_id' ) REFERENCES etapa_exercicio ('ete_id'));",
		"CREATE TABLE IF NOT EXISTS exercicio_aerobico_realizado ('ear_id' INTEGER, 'ear_duracao' INTEGER NOT NULL, 'ear_distancia' DECIMAL(5,2) NOT NULL, 'ear_velocidade' DECIMAL(5,2) NOT NULL, 'ear_exr_id' INTEGER NOT NULL, PRIMARY KEY ('ear_id'), CONSTRAINT 'fk_Exercicio_Aerobico_Realizado_Exercicio_realizado1' FOREIGN KEY ('ear_exr_id' ) REFERENCES exercicio_realizado ('exr_id'));",
		"insert into programa(pro_nome,pro_data_inicial,pro_data_final) values('Ganhar massa',1320125230800,1320125230800);",
		"insert into programa(pro_nome,pro_data_inicial,pro_data_final) values('Perder peso',1320125230800,1320125230800);",
		
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Basico',1,1320125230800,1320125230800);",
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Moderado',1,1320125230800,1320125230800)",
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Avançado',1,1320125230800,1320125230800)",
		
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Basico',2,1320125230800,1320125230800);",
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Moderado',2,1320125230800,1320125230800)",
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Avançado',2,1320125230800,1320125230800)",
		
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Supino','Exercicio supino','A','N',4.4,1,'1,2')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Corrida','Corrida Curta','A','A',4.0,10,'12,13')",
		
		
	};	
	

	// Nome do banco
	private static final String NOME_BANCO = "fitness_mobile";

	// Controle de versão
	private static final int VERSAO_BANCO = 1;

	protected SQLiteDatabase db;

	// Classe utilitária para abrir, criar, e atualizar o banco de dados
	SQLiteHelper dbHelper;
	
	public Dao(Context ctx) {
		dbHelper = new SQLiteHelper(ctx, Dao.NOME_BANCO, Dao.VERSAO_BANCO,
				Dao.SCRIPT_DATABASE_CREATE, Dao.SCRIPT_DATABASE_DELETE);

	}
	
}
