SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `fitness_mobile`.`exercicio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`exercicio` (
  `exe_id` INT NOT NULL ,
  `exe_nome` VARCHAR(45) NOT NULL ,
  `exe_descricao` VARCHAR(45) NOT NULL ,
  `exe_situacao` CHAR(1) NOT NULL ,
  `exe_tipo` CHAR(1) NOT NULL ,
  `exe_indice_calorico` DECIMAL(5,2) NULL ,
  `exe_musculo` CHAR(1) NOT NULL ,
  `exe_grupomuscular` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`exe_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`programa`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`programa` (
  `pro_id` INT NOT NULL ,
  `pro_nome` VARCHAR(50) NOT NULL ,
  `pro_data_inicial` INT NOT NULL ,
  `pro_data_final` INT NOT NULL ,
  PRIMARY KEY (`pro_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`pessoa`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`pessoa` (
  `pes_id` INT NOT NULL ,
  `pes_nome` VARCHAR(45) NOT NULL ,
  `pes_data_nasc` MEDIUMTEXT  NOT NULL ,
  `pes_peso` VARCHAR(45) NULL ,
  `pes_altura` VARCHAR(45) NULL ,
  `pes_sexo` VARCHAR(1) NOT NULL ,
  PRIMARY KEY (`pes_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`medida`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`medida` (
  `med_id` INT NOT NULL ,
  `med_data_cadastro` MEDIUMTEXT  NOT NULL ,
  `med_pes_id` INT NOT NULL ,
  `med_musculo` INT NOT NULL ,
  PRIMARY KEY (`med_id`, `med_pes_id`) ,
  INDEX `fk_medida_pessoa` (`med_pes_id` ASC) ,
  CONSTRAINT `fk_medida_pessoa`
    FOREIGN KEY (`med_pes_id` )
    REFERENCES `fitness_mobile`.`pessoa` (`pes_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`configuracao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`configuracao` (
  `con_id` INT NOT NULL ,
  `con_metragem` VARCHAR(45) NOT NULL ,
  `con_kilometragem` VARCHAR(45) NOT NULL ,
  `con_pesagem` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`con_id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`etapa`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`etapa` (
  `eta_id` INT NOT NULL ,
  `eta_nome` VARCHAR(50) NOT NULL ,
  `eta_pro_id` INT NOT NULL ,
  `eta_data_inicial` INT NOT NULL ,
  `eta_data_final` INT NOT NULL ,
  PRIMARY KEY (`eta_id`) ,
  INDEX `fk_etapa_programa` (`eta_pro_id` ASC) ,
  CONSTRAINT `fk_etapa_programa`
    FOREIGN KEY (`eta_pro_id` )
    REFERENCES `fitness_mobile`.`programa` (`pro_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`etapa_exercicio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`etapa_exercicio` (
  `ete_id` INT NOT NULL ,
  `ete_eta_id` INT NOT NULL ,
  `ete_exe_id` INT NOT NULL ,
  `ete_tipo_id` INT NOT NULL ,
  `ete_flag` CHAR(1) NULL ,
  `ete_diaid` INT NOT NULL ,
  `ete_dtbaixa` INT NULL ,
  PRIMARY KEY (`ete_id`) ,
  INDEX `fk_ete_eta` (`ete_eta_id` ASC) ,
  INDEX `fk_ete_exe` (`ete_exe_id` ASC) ,
  CONSTRAINT `fk_ete_eta`
    FOREIGN KEY (`ete_eta_id` )
    REFERENCES `fitness_mobile`.`etapa` (`eta_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ete_exe`
    FOREIGN KEY (`ete_exe_id` )
    REFERENCES `fitness_mobile`.`exercicio` (`exe_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`anaerobico`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`anaerobico` (
  `ana_id` INT NOT NULL ,
  `ana_repeticoes` INT NOT NULL ,
  `ana_peso` DECIMAL(5,2) NOT NULL ,
  `ana_serie` INT NOT NULL ,
  `ana_ete_id` INT NOT NULL ,
  PRIMARY KEY (`ana_id`) ,
  INDEX `fk_ana_ete` (`ana_ete_id` ASC) ,
  CONSTRAINT `fk_ana_ete`
    FOREIGN KEY (`ana_ete_id` )
    REFERENCES `fitness_mobile`.`etapa_exercicio` (`ete_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`exercicio_realizado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`exercicio_realizado` (
  `exr_id` INT NOT NULL ,
  `exr_data_realizacao` INT NOT NULL ,
  `exr_ete_id` INT NOT NULL ,
  PRIMARY KEY (`exr_id`) ,
  INDEX `fk_exr_ete` (`exr_ete_id` ASC) ,
  CONSTRAINT `fk_exr_ete`
    FOREIGN KEY (`exr_ete_id` )
    REFERENCES `fitness_mobile`.`etapa_exercicio` (`ete_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fitness_mobile`.`exercicio_aerobico_realizado`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fitness_mobile`.`exercicio_aerobico_realizado` (
  `ear_id` INT NOT NULL ,
  `ear_duracao` MEDIUMTEXT  NULL ,
  `ear_distancia` DECIMAL(5,2) NULL ,
  `ear_velocidade` DECIMAL(5,2) NULL ,
  `ear_exr_id` INT NOT NULL ,
  PRIMARY KEY (`ear_id`) ,
  INDEX `fk_ear_exr` (`ear_exr_id` ASC) ,
  CONSTRAINT `fk_Exercicio_Aerobico_Realizado_Exercicio_realizado1`
    FOREIGN KEY (`ear_exr_id` )
    REFERENCES `fitness_mobile`.`exercicio_realizado` (`exr_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
