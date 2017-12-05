-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema redescritores
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema redescritores
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `redescritores` DEFAULT CHARACTER SET utf8 ;
USE `redescritores` ;

-- -----------------------------------------------------
-- Table `redescritores`.`tipousuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`tipousuario` (
  `pkTipoUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`pkTipoUsuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `redescritores`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`usuario` (
  `pkUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `contrasena` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(100) NULL DEFAULT NULL,
  `ubicacion` VARCHAR(100) NULL DEFAULT NULL,
  `foto` VARCHAR(45) NULL DEFAULT NULL,
  `TipoUsuario_pkTipoUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`pkUsuario`, `TipoUsuario_pkTipoUsuario`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_Usuario_TipoUsuario1_idx` (`TipoUsuario_pkTipoUsuario` ASC),
  CONSTRAINT `fk_Usuario_TipoUsuario1`
    FOREIGN KEY (`TipoUsuario_pkTipoUsuario`)
    REFERENCES `redescritores`.`tipousuario` (`pkTipoUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `redescritores`.`relatos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`relatos` (
  `pkRelatos` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NULL DEFAULT NULL,
  `nPaginas` INT(11) NULL DEFAULT NULL,
  `puntuacion` FLOAT NULL DEFAULT NULL,
  `archivo` VARCHAR(45) NULL DEFAULT NULL,
  `fechaCreacion` DATE NULL DEFAULT NULL,
  `ultimaEdicion` DATE NULL DEFAULT NULL,
  `Usuario_pkUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`pkRelatos`, `Usuario_pkUsuario`),
  UNIQUE INDEX `pkRelatos_UNIQUE` (`pkRelatos` ASC),
  INDEX `fk_Relatos_Usuario1_idx` (`Usuario_pkUsuario` ASC),
  CONSTRAINT `fk_Relatos_Usuario1`
    FOREIGN KEY (`Usuario_pkUsuario`)
    REFERENCES `redescritores`.`usuario` (`pkUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `redescritores`.`capitulos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`capitulos` (
  `pkCapitulo` INT(11) NOT NULL AUTO_INCREMENT,
  `nPaginas` INT(11) NULL DEFAULT NULL,
  `puntuacion` FLOAT NULL DEFAULT NULL,
  `listaCap` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NULL DEFAULT NULL,
  `ultimaEdicion` DATE NULL DEFAULT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `Relatos_pkRelatos` INT(11) NOT NULL,
  `Relatos_Usuario_pkUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`pkCapitulo`, `Relatos_pkRelatos`, `Relatos_Usuario_pkUsuario`),
  INDEX `fk_Capitulos_Relatos1_idx` (`Relatos_pkRelatos` ASC, `Relatos_Usuario_pkUsuario` ASC),
  CONSTRAINT `fk_Capitulos_Relatos1`
    FOREIGN KEY (`Relatos_pkRelatos` , `Relatos_Usuario_pkUsuario`)
    REFERENCES `redescritores`.`relatos` (`pkRelatos` , `Usuario_pkUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `redescritores`.`mensajes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`mensajes` (
  `pkMensajes` INT(11) NOT NULL AUTO_INCREMENT,
  `texto` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NOT NULL,
  `Usuario_pkUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`pkMensajes`),
  INDEX `fk_Mensajes_Usuario1_idx` (`Usuario_pkUsuario` ASC),
  CONSTRAINT `fk_Mensajes_Usuario1`
    FOREIGN KEY (`Usuario_pkUsuario`)
    REFERENCES `redescritores`.`usuario` (`pkUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `redescritores`.`comentarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`comentarios` (
  `pkComentarios` INT(11) NOT NULL AUTO_INCREMENT,
  `ultimaEdicion` DATE NOT NULL,
  `Mensajes_pkMensajes` INT(11) NOT NULL,
  `Relatos_pkRelatos` INT(11) NOT NULL,
  `Capitulos_pkCapitulo` INT(11) NOT NULL,
  `Comentarios_pkComentarios` INT(11) NOT NULL,
  PRIMARY KEY (`pkComentarios`, `Mensajes_pkMensajes`, `Relatos_pkRelatos`, `Capitulos_pkCapitulo`, `Comentarios_pkComentarios`),
  INDEX `fk_Comentarios_Mensajes1_idx` (`Mensajes_pkMensajes` ASC),
  INDEX `fk_Comentarios_Relatos1_idx` (`Relatos_pkRelatos` ASC),
  INDEX `fk_Comentarios_Capitulos1_idx` (`Capitulos_pkCapitulo` ASC),
  INDEX `fk_Comentarios_Comentarios1_idx` (`Comentarios_pkComentarios` ASC),
  CONSTRAINT `fk_Comentarios_Capitulos1`
    FOREIGN KEY (`Capitulos_pkCapitulo`)
    REFERENCES `redescritores`.`capitulos` (`pkCapitulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comentarios_Comentarios1`
    FOREIGN KEY (`Comentarios_pkComentarios`)
    REFERENCES `redescritores`.`comentarios` (`pkComentarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comentarios_Mensajes1`
    FOREIGN KEY (`Mensajes_pkMensajes`)
    REFERENCES `redescritores`.`mensajes` (`pkMensajes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comentarios_Relatos1`
    FOREIGN KEY (`Relatos_pkRelatos`)
    REFERENCES `redescritores`.`relatos` (`pkRelatos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `redescritores`.`mensprivados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`mensprivados` (
  `pkPrivados` INT(11) NOT NULL AUTO_INCREMENT,
  `pkDestinatario` INT(11) NOT NULL,
  `texto` VARCHAR(45) NOT NULL,
  `Mensajes_pkMensajes` INT(11) NOT NULL,
  PRIMARY KEY (`pkPrivados`, `Mensajes_pkMensajes`),
  INDEX `fk_MensPrivados_Mensajes1_idx` (`Mensajes_pkMensajes` ASC),
  CONSTRAINT `fk_MensPrivados_Mensajes1`
    FOREIGN KEY (`Mensajes_pkMensajes`)
    REFERENCES `redescritores`.`mensajes` (`pkMensajes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `redescritores`.`relatos_has_enganchados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`relatos_has_enganchados` (
  `Relatos_pkRelatos` INT(11) NOT NULL,
  `usuario_pkEnganchado` INT(11) NOT NULL,
  PRIMARY KEY (`Relatos_pkRelatos`, `usuario_pkEnganchado`),
  INDEX `fk_Relatos_has_Enganchados_Relatos1_idx` (`Relatos_pkRelatos` ASC),
  INDEX `fk_relatos_has_enganchados_usuario1_idx` (`usuario_pkEnganchado` ASC),
  CONSTRAINT `fk_Relatos_has_Enganchados_Relatos1`
    FOREIGN KEY (`Relatos_pkRelatos`)
    REFERENCES `redescritores`.`relatos` (`pkRelatos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relatos_has_enganchados_usuario1`
    FOREIGN KEY (`usuario_pkEnganchado`)
    REFERENCES `redescritores`.`usuario` (`pkUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `redescritores`.`usuario_has_seguidores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `redescritores`.`usuario_has_seguidores` (
  `usuario_pkUsuario` INT(11) NOT NULL,
  `usuario_pkSeguidor` INT(11) NOT NULL,
  PRIMARY KEY (`usuario_pkUsuario`, `usuario_pkSeguidor`),
  INDEX `fk_usuario_has_usuario_usuario2_idx` (`usuario_pkSeguidor` ASC),
  INDEX `fk_usuario_has_usuario_usuario1_idx` (`usuario_pkUsuario` ASC),
  CONSTRAINT `fk_usuario_has_usuario_usuario1`
    FOREIGN KEY (`usuario_pkUsuario`)
    REFERENCES `redescritores`.`usuario` (`pkUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_usuario_usuario2`
    FOREIGN KEY (`usuario_pkSeguidor`)
    REFERENCES `redescritores`.`usuario` (`pkUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
