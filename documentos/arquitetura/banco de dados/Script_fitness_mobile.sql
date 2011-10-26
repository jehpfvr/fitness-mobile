-- -----------------------------------------------------
-- Table exercicio
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS exercicio (
  `exe_id` INT NOT NULL PRIMARY KEY,
  `exe_nome` VARCHAR(45) NOT NULL ,
  `exe_descricao` VARCHAR(45) NOT NULL ,
  `exe_situacao` CHAR(1) NOT NULL ,
  `exe_tipo` CHAR(1) NOT NULL ,
  `exe_indice_calorico` DECIMAL(5,2) NULL
);


-- -----------------------------------------------------
-- Table programa
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS programa (
  `pro_id` INT NOT NULL PRIMARY KEY,
  `pro_nome` VARCHAR(50) NOT NULL ,
  `pro_data_inicial` INT  NOT NULL ,
  `pro_data_final` INT  NOT NULL
);


-- -----------------------------------------------------
-- Table pessoa
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS pessoa (
  `pes_id` INT NOT NULL PRIMARY KEY ,
  `pes_nome` VARCHAR(45) NOT NULL ,
  `pes_data_nasc` INT  NOT NULL ,
  `pes_peso` VARCHAR(45) NULL ,
  `pes_altura` VARCHAR(45) NULL ,
  `pes_sexo` VARCHAR(1) NOT NULL
);


-- -----------------------------------------------------
-- Table medida
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS medida (
  `med_id` INT NOT NULL ,
  `med_data_cadastro` INT  NOT NULL ,
  `med_pes_id` INT NOT NULL ,
  `med_musculo` INT NOT NULL ,
  PRIMARY KEY (`med_id`, `med_pes_id`) ,
  CONSTRAINT `fk_medida_pessoa`
    FOREIGN KEY (`med_pes_id` )
    REFERENCES pessoa (`pes_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table configuracao
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS configuracao (
  `con_id` INT NOT NULL ,
  `con_metragem` VARCHAR(45) NOT NULL ,
  `con_kilometragem` VARCHAR(45) NOT NULL ,
  `con_pesagem` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`con_id`) );


-- -----------------------------------------------------
-- Table etapa
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS etapa (
  `eta_id` INT NOT NULL ,
  `eta_nome` VARCHAR(50) NOT NULL ,
  `eta_pro_id` INT NOT NULL ,
  PRIMARY KEY (`eta_id`) ,
  CONSTRAINT `fk_etapa_programa`
    FOREIGN KEY (`eta_pro_id` )
    REFERENCES programa (`pro_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table etapa_exercicio
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS etapa_exercicio (
  `ete_id` INT NOT NULL ,
  `ete_eta_id` INT NOT NULL ,
  `ete_exe_id` INT NOT NULL ,
  PRIMARY KEY (`ete_id`) ,
  CONSTRAINT `fk_ete_eta`
    FOREIGN KEY (`ete_eta_id` )
    REFERENCES etapa (`eta_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ete_exe`
    FOREIGN KEY (`ete_exe_id` )
    REFERENCES exercicio (`exe_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table anaerobico
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS anaerobico (
  `ana_id` INT NOT NULL ,
  `ana_repeticoes` INT NOT NULL ,
  `ana_peso` DECIMAL(5,2) NOT NULL ,
  `ana_serie` INT NOT NULL ,
  `ana_ete_id` INT NOT NULL ,
  PRIMARY KEY (`ana_id`) ,
  CONSTRAINT `fk_ana_ete`
    FOREIGN KEY (`ana_ete_id` )
    REFERENCES etapa_exercicio (`ete_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table exercicio_realizado
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS exercicio_realizado (
  `exr_id` INT NOT NULL ,
  `exr_data_realizacao` INT NOT NULL ,
  `exr_ete_id` INT NOT NULL ,
  PRIMARY KEY (`exr_id`) ,
  CONSTRAINT `fk_exr_ete`
    FOREIGN KEY (`exr_ete_id` )
    REFERENCES etapa_exercicio (`ete_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table exercicio_aerobico_realizado
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS exercicio_aerobico_realizado (
  `ear_id` INT NOT NULL ,
  `ear_duracao` INT NOT NULL ,
  `ear_distancia` DECIMAL(5,2) NOT NULL ,
  `ear_velocidade` DECIMAL(5,2) NOT NULL ,
  `ear_exr_id` INT NOT NULL ,
  PRIMARY KEY (`ear_id`) ,
  CONSTRAINT `fk_Exercicio_Aerobico_Realizado_Exercicio_realizado1`
    FOREIGN KEY (`ear_exr_id` )
    REFERENCES exercicio_realizado (`exr_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
