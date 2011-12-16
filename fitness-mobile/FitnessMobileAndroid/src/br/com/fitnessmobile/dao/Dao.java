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
		"CREATE TABLE IF NOT EXISTS exercicio ('exe_id' INTEGER PRIMARY KEY, 'exe_nome' VARCHAR(45) NOT NULL, 'exe_descricao' VARCHAR(200) NULL, 'exe_situacao' CHAR(1) NOT NULL, 'exe_tipo' CHAR(1) NOT NULL, 'exe_indice_calorico' DECIMAL(5,2) NULL, 'exe_musculo' CHAR(1) NOT NULL, 'exe_grupomuscular' VARCHAR(45) NOT NULL);",
		"CREATE TABLE IF NOT EXISTS programa ('pro_id' INTEGER PRIMARY KEY, 'pro_nome' VARCHAR(50) NOT NULL, 'pro_data_inicial' INTEGER  NOT NULL, 'pro_data_final' INTEGER  NOT NULL);",
		"CREATE TABLE IF NOT EXISTS pessoa ('pes_id' INTEGER PRIMARY KEY, 'pes_nome' VARCHAR(45) NOT NULL, 'pes_data_nasc' INTEGER  NOT NULL, 'pes_peso' VARCHAR(45) NULL, 'pes_altura' VARCHAR(45) NULL, 'pes_sexo' VARCHAR(1) NOT NULL);",
		//, 'med_pes_id'), CONSTRAINT 'fk_medida_pessoa' FOREIGN KEY ('med_pes_id' ) REFERENCES pessoa ('pes_id')
		"CREATE TABLE IF NOT EXISTS medida ('med_id' INTEGER, 'med_peso' DECIMAL(5,2) NOT NULL, 'med_biceps_esquerdo' DECIMAL(5,2) NOT NULL, 'med_biceps_direito' DECIMAL(5,2) NOT NULL, 'med_triceps_esquerdo' DECIMAL(5,2) NOT NULL, 'med_triceps_direito' DECIMAL(5,2) NOT NULL, 'med_cintura' DECIMAL(5,2) NOT NULL, 'med_peitoral' DECIMAL(5,2) NOT NULL, 'med_coxa_esquerda' DECIMAL(5,2) NOT NULL, 'med_coxa_direita' DECIMAL(5,2) NOT NULL, 'med_panturrilha_direita' DECIMAL(5,2) NOT NULL,'med_quadril' DECIMAL(5,2) NOT NULL, 'med_panturrilha_esquerda' DECIMAL(5,2) NOT NULL, 'med_data_cadastro' VARCHAR(45)  NOT NULL, PRIMARY KEY ('med_id'));",
		"CREATE TABLE IF NOT EXISTS configuracao ('con_id' INTEGER, 'con_metragem' VARCHAR(45) NOT NULL, 'con_kilometragem' VARCHAR(45) NOT NULL, 'con_pesagem' VARCHAR(45) NOT NULL, PRIMARY KEY ('con_id') );",
		"CREATE TABLE IF NOT EXISTS etapa ('eta_id' INTEGER, 'eta_nome' VARCHAR(50) NOT NULL, 'eta_pro_id' INTEGER NOT NULL,'eta_data_inicial' INTEGER NOT NULL, 'eta_data_final' INTEGER NOT NULL, PRIMARY KEY ('eta_id'),  CONSTRAINT 'fk_etapa_programa' FOREIGN KEY ('eta_pro_id' ) REFERENCES programa ('pro_id' ));",
		"CREATE TABLE IF NOT EXISTS etapa_exercicio ('ete_tipo_id' INTEGER NOT NULL,'ete_id' INTEGER, 'ete_eta_id' INTEGER NOT NULL, 'ete_exe_id' INTEGER NOT NULL,'ete_flag' CHAR(1),'ete_diaid' INTEGER NOT NULL,'ete_dtbaixa' INTEGER, PRIMARY KEY ('ete_id'), CONSTRAINT 'fk_ete_eta' FOREIGN KEY ('ete_eta_id' ) REFERENCES etapa ('eta_id' ), CONSTRAINT 'fk_ete_exe' FOREIGN KEY ('ete_exe_id' ) REFERENCES exercicio ('exe_id'));",
		"CREATE TABLE IF NOT EXISTS anaerobico ('ana_id' INTEGER, 'ana_repeticoes' INTEGER NOT NULL, 'ana_peso' DECIMAL(5,2) NOT NULL, 'ana_serie' INTEGER NOT NULL, 'ana_ete_id' INTEGER NOT NULL, PRIMARY KEY ('ana_id'), CONSTRAINT 'fk_ana_ete' FOREIGN KEY ('ana_ete_id' ) REFERENCES etapa_exercicio ('ete_id'));",
		//"CREATE TABLE IF NOT EXISTS exercicio_realizado ('exr_id' INTEGER, 'exr_data_realizacao' INTEGER NOT NULL, 'exr_ete_id' INTEGER NOT NULL, PRIMARY KEY ('exr_id'), CONSTRAINT 'fk_exr_ete' FOREIGN KEY ('exr_ete_id' ) REFERENCES etapa_exercicio ('ete_id'));",
		"CREATE TABLE IF NOT EXISTS exercicio_aerobico_realizado ('ear_id' INTEGER, 'ear_duracao' INTEGER NOT NULL, 'ear_distancia' DECIMAL(5,2) NOT NULL, 'ear_velocidade' DECIMAL(5,2) NOT NULL, 'ear_exr_id' INTEGER NOT NULL, PRIMARY KEY ('ear_id'), CONSTRAINT 'fk_Exercicio_Aerobico_Realizado_Exercicio_realizado1' FOREIGN KEY ('ear_exr_id' ) REFERENCES etapa_exercicio ('ete_id'));",

		//Programas de exercicio
		"insert into programa(pro_nome,pro_data_inicial,pro_data_final) values('Ganhar massa',1323891573000,1324064373000);",
		"insert into programa(pro_nome,pro_data_inicial,pro_data_final) values('Perder peso',1323891573000,1324064373000);",

		//Etapas
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Basico',1,1320125230800,1320125230800);",
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Moderado',1,1320125230800,1320125230800)",
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Avançado',1,1320125230800,1320125230800)",

		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Basico',2,1320125230800,1320125230800);",
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Moderado',2,1320125230800,1320125230800)",
		"insert into etapa(eta_nome,eta_pro_id,eta_data_inicial,eta_data_final) values('Avançado',2,1320125230800,1320125230800)",

		//Trapézio
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Barbell Upright Row','Barbell Upright Row','A','N',4.0,1,'1')",

		//Deltoide
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Elevação frontal com Halteres','Um excelente exercício para treinar, a frente do músculo deltóide.','A','N',4.0,2,'2,1')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Rotacao Cubana','Exercícios para fortalecer ou reabilitação do manguito rotador.','A','N',4.0,2,'2')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Elevações sobre cabeça','Pernas flexionadas e afastadas na largura dos ombros. Braços abertos, e ligeiramente flexionados, de modo que os halteres fiquem alinhados com os ombros.','A','N',4.0,2,'2,3')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Elevação lateral com halteres','Exercício fácil de base a realizar, adequado para iniciantes e profissionais que querem trabalhar os músculos deltóide.','A','N',4.0,2,'2,1')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Elevação lateral tronco inclinado','Exercício específico para trabalhar a musculatura posterior do deltóide.','A','N',4.0,2,'2,1')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Elevação lateral tronco inclinado sobre um banco','Exercício específico para trabalhar a musculatura posterior do deltóide.','A','N',4.0,2,'2,1')",

		//Bíceps
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Rosca martelo alternada','Rosca martelo alternada: exercício utilizado particularmente em treinamentos que se destinam à formação de massa muscular, serve para estimular de forma bastante eficaz ambos os tendões do músculo biceps.','A','N',4.0,3,'3,5')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Rosca concentrada com halteres','Sente-se em um banco. Dobre o tronco ligeiramente para frente e apoiando o cotovelo no joelho. O braço fica totalmente estendido. Flexione o antebraço o mais próximo do bíceps.','A','N',4.0,3,'3')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Alternating Curls','Alternating Curls ','A','N',4.0,3,'3,5')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Barbell Curls','Barbell Curls','A','N',4.0,3,'3,10')",

		//Tríceps
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Extensão antebraços tronco inclinado ','Este exercício é ótimo para estimular todos os ares do músculo tríceps. Você pode realizar este exercício para triceps em casa ou na academia, você só precisa de dois halteres.','A','N',4.0,4,'4,3')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Flexão Hindu','Flexão, Hindu è um exercício usado em artes marciais Hindu para treinar peito e tríceps ombros.','A','N',4.0,4,'1,2,4,6')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Repulsão entre dois bancos','Exercicío ideal para treinar seu tríceps em qualquer lugar','A','N',4.0,4,'4,6')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Extensão tríceps com haltere ','Extensão tríceps com haltere é um excelente exercício para treinar ambas as extremidades do músculo tríceps','A','N',4.0,4,'4')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Flexão como espartano','Exercício avançado utilizados pelos protagonistas do filme 300 para treinar os músculos do peito.','A','N',4.0,4,'2,4,6')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Burpees ','Exercício muito avançado que envolve mais grupos musculares ao mesmo tempo: os músculos do ombro dos braços, os músculos peitorais e os músculos da perna.','A','N',4.0,4,'2,4,6,13')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Flexão de braços','Tronco perfeitamente reto. Pernas ligeiramente afastadas. Coloque as mãos em uma linha vertical em relação aos ombros. ','A','N',4.0,4,'2,4,6,8')",

		//Antebraco
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Flexão dos antebraços com barra','Flexão dos antebraços com barra','A','N',4.0,5,'5,3')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Ondas de pulso com barbell','Flexão dos antebraços com barra, mãos em pronação é uma variação do clássico curls para trabalhar os músculos bíceps.','A','N',4.0,5,'5,5')",

		//Peitoral
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Supino Plano','Exercicío clássico para desenvolver os músculos peitorais.','A','N',4.0,6,'6,8,2,3')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Flexões - joelho sobre prancha','Flexões - joelho sobre prancha.','A','N',4.0,6,'6,2,3')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Flexão com salto','Flexão com salto.','A','N',4.0,6,'6,2,3')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Flexões com joelhos apoiados','Flexões com joelhos apoiados.','A','N',4.0,6,'6,2,3')",

		//Abdominais
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Compressão cotovelo ao joelho - alternado','Este é um exercício de base para treinar os músculos oblíquos da área abdominal.','A','N',4.0,7,'7,8,13')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Elevação pernas em 4 tempos','Elevação pernas em 4 tempos.','A','N',4.0,7,'7')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Impulso para o alto com haltere','Bom exercício para treinar os músculos abdominais utilizando um peso.','A','N',4.0,7,'7,8')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Toque dos tornozelos - pernas na vertical','Este exercício é particularmente apropriado para a área de trabalho de alta dos músculos abdominais.','A','N',4.0,7,'7,8,10,13')",

		//Abdominais Oblíquos
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Flexão pé à pé','Este é um dos melhores exercícios para treinar os músculos oblíquos abdominais do corpo livre.','A','N',4.0,8,'7,8')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Dupla torção','é um exercício avançado, é necessário manter o equilíbrio contra o abdômen.','A','N',4.0,8,'7,8')",

		//Dorsal
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Tração na barra fixa','O melhor exercício para fortalecer os músculos das costas (especialmente o músculo dorsal).','A','N',4.0,9,'9,10,3')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Dumbbell Row Exercise ','Dumbbell Row Exercise.','A','N',4.0,9,'9')",

		//Lombar
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Extensão do tronco','Este exercício é fundamental para fortalecer os músculos da região lombar, os músculos lombares. ','A','N',4.0,10,'10,11,14')",

		//Gluteos
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Extensão de quadril','Este exercício para os glúteos se tornou muito famoso graças à sua facilidade de realização e à sua eficácia real para tonificar os glúteos sem precisar usar equipamentos.','A','N',4.0,11,'11,14')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Afundo','Este exercício é ótimo para a coordenação neuromuscular e para o fortalecimento geral.','A','N',4.0,11,'11,13,14')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Extensão quadril joelho dobrado','Similar ao Extensão de quadril.','A','N',4.0,11,'11,14')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Extensão do quadril concentrada','Este é um exercício para trabalhar os glúteos de nível avançado.','A','N',4.0,11,'11,14')",

		//Adutores
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Pushes on swiss ball','Pushes na bola Suíça.','A','N',4.0,12,'11,12')",

		//Quadriceps
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Squat','Squat.','A','N',4.0,13,'11,13')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Barbell Static Lunge','Barbell Static Lunge.','A','N',4.0,13,'11,13,14,10')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Lateral Lunge','Lunge lateral.','A','N',4.0,13,'11,13')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Cat Walk','Caminhada de Gato para tonificar as coxas.','A','N',4.0,13,'11,13')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Corrida','Corrida Curta','A','A',4.0,13,'11,13,14,10')",

		//Isquiotibiais
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Leg Curl Machine','Exercício para gluteos na máquina.','A','N',4.0,14,'15,11,14')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Ponte','Exercício ponte para tonificar o bumbum.','A','N',4.0,14,'11,14')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Reverse Lunge','Lunge reverso, para tonificar o bumbum.','A','N',4.0,14,'11,13,14')",

		//Panturrilha
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Extensão dos pés com barra sentado','Extensão dos pés com barra sentado.','A','N',4.0,15,'15')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Calf Raises','Calf Raises.','A','N',4.0,15,'15')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Extensão dos Pés sentado','Extensão dos Pés sentado.','A','N',4.0,15,'15')",
		"insert into exercicio(exe_nome,exe_descricao,exe_situacao,exe_tipo,exe_indice_calorico,exe_musculo,exe_grupomuscular) values('Ankle strengthening','Ankle strengthening.','A','N',4.0,15,'15')",

		//Medida
				"insert into medida(med_id,med_peso,med_biceps_esquerdo,med_biceps_direito,med_triceps_esquerdo,med_triceps_direito,med_cintura,med_peitoral,med_coxa_esquerda,med_coxa_direita,med_panturrilha_direita,med_quadril,med_panturrilha_esquerda,med_data_cadastro) values('1','1','1','1','1','1','1','1','1','1','1','1','1','15-12-2011')",
				"insert into medida(med_id,med_peso,med_biceps_esquerdo,med_biceps_direito,med_triceps_esquerdo,med_triceps_direito,med_cintura,med_peitoral,med_coxa_esquerda,med_coxa_direita,med_panturrilha_direita,med_quadril,med_panturrilha_esquerda,med_data_cadastro) values('2','1','1','3','4','1','2','1','51','1','10','1','2','16-12-2011')",

		//Pessoa
		"insert into pessoa(pes_id,pes_nome,pes_data_nasc,pes_peso,pes_altura,pes_sexo) values('1','User','1989','70','1,70','M')",
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
