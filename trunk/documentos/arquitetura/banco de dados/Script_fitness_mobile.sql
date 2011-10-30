-- -----------------------------------------------------
-- Table exercicio
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS exercicio (
  'exe_id' INTEGER PRIMARY KEY,
  'exe_nome' VARCHAR(45) NOT NULL ,
  'exe_descricao' VARCHAR(45) NOT NULL ,
  'exe_situacao' CHAR(1) NOT NULL ,
  'exe_tipo' CHAR(1) NOT NULL ,
  'exe_indice_calorico' DECIMAL(5,2) NULL
);


-- -----------------------------------------------------
-- Table programa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS programa (
  'pro_id' INTEGER PRIMARY KEY,
  'pro_nome' VARCHAR(50) NOT NULL ,
  'pro_data_inicial' INTEGER  NOT NULL ,
  'pro_data_final' INTEGER  NOT NULL
);


-- -----------------------------------------------------
-- Table pessoa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pessoa (
  'pes_id' INTEGER PRIMARY KEY,
  'pes_nome' VARCHAR(45) NOT NULL ,
  'pes_data_nasc' INTEGER  NOT NULL ,
  'pes_peso' VARCHAR(45) NULL ,
  'pes_altura' VARCHAR(45) NULL ,
  'pes_sexo' VARCHAR(1) NOT NULL
);


-- -----------------------------------------------------
-- Table medida
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS medida (
  'med_id' INTEGER,
  'med_data_cadastro' INTEGER  NOT NULL ,
  'med_pes_id' INTEGER NOT NULL ,
  'med_musculo' INTEGER NOT NULL ,
  PRIMARY KEY ('med_id', 'med_pes_id') ,
  CONSTRAINT 'fk_medida_pessoa'
    FOREIGN KEY ('med_pes_id' )
    REFERENCES pessoa ('pes_id' ));


-- -----------------------------------------------------
-- Table configuracao
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS configuracao (
  'con_id' INTEGER,
  'con_metragem' VARCHAR(45) NOT NULL ,
  'con_kilometragem' VARCHAR(45) NOT NULL ,
  'con_pesagem' VARCHAR(45) NOT NULL ,
  PRIMARY KEY ('con_id') );


-- -----------------------------------------------------
-- Table etapa
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS etapa (
  'eta_id' INTEGER,
  'eta_nome' VARCHAR(50) NOT NULL ,
  'eta_pro_id' INTEGER NOT NULL ,
  PRIMARY KEY ('eta_id'),
  CONSTRAINT 'fk_etapa_programa'
    FOREIGN KEY ('eta_pro_id' )
    REFERENCES programa ('pro_id' ));


-- -----------------------------------------------------
-- Table etapa_exercicio
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS etapa_exercicio (
  'ete_id' INTEGER,
  'ete_eta_id' INTEGER NOT NULL ,
  'ete_exe_id' INTEGER NOT NULL ,
  PRIMARY KEY ('ete_id') ,
  CONSTRAINT 'fk_ete_eta'
    FOREIGN KEY ('ete_eta_id' )
    REFERENCES etapa ('eta_id' ),
  CONSTRAINT 'fk_ete_exe'
    FOREIGN KEY ('ete_exe_id' )
    REFERENCES exercicio ('exe_id' ));


-- -----------------------------------------------------
-- Table anaerobico
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS anaerobico (
  'ana_id' INTEGER,
  'ana_repeticoes' INTEGER NOT NULL ,
  'ana_peso' DECIMAL(5,2) NOT NULL ,
  'ana_serie' INTEGER NOT NULL ,
  'ana_ete_id' INTEGER NOT NULL ,
  PRIMARY KEY ('ana_id') ,
  CONSTRAINT 'fk_ana_ete'
    FOREIGN KEY ('ana_ete_id' )
    REFERENCES etapa_exercicio ('ete_id' ));


-- -----------------------------------------------------
-- Table exercicio_realizado
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS exercicio_realizado (
  'exr_id' INTEGER,
  'exr_data_realizacao' INTEGER NOT NULL ,
  'exr_ete_id' INTEGER NOT NULL ,
  PRIMARY KEY ('exr_id') ,
  CONSTRAINT 'fk_exr_ete'
    FOREIGN KEY ('exr_ete_id' )
    REFERENCES etapa_exercicio ('ete_id' ));


-- -----------------------------------------------------
-- Table exercicio_aerobico_realizado
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS exercicio_aerobico_realizado (
  'ear_id' INTEGER,
  'ear_duracao' INTEGER NOT NULL ,
  'ear_distancia' DECIMAL(5,2) NOT NULL ,
  'ear_velocidade' DECIMAL(5,2) NOT NULL ,
  'ear_exr_id' INTEGER NOT NULL ,
  PRIMARY KEY ('ear_id') ,
  CONSTRAINT 'fk_Exercicio_Aerobico_Realizado_Exercicio_realizado1'
    FOREIGN KEY ('ear_exr_id' )
    REFERENCES exercicio_realizado ('exr_id' ));
