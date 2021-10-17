-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema danger_info
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `danger_info` ;

-- -----------------------------------------------------
-- Schema danger_info
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `danger_info` DEFAULT CHARACTER SET utf8 ;
USE `danger_info` ;

-- -----------------------------------------------------
-- Table `danger_info`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `danger_info`.`user` ;

CREATE TABLE IF NOT EXISTS `danger_info`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(20) NOT NULL,
  `lastName` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `status` ENUM('active', 'inactive', 'blocked', 'suspended') NOT NULL DEFAULT 'inactive',
  `country` VARCHAR(45) NULL,
  `region` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `avatar` VARCHAR(100) NULL,
  `createdAt` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `notificationApp` TINYINT NULL DEFAULT 0,
  `notificationEmail` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `danger_info`.`post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `danger_info`.`post` ;

CREATE TABLE IF NOT EXISTS `danger_info`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `author` INT NOT NULL,
  `createdAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` TEXT NULL,
  `url` VARCHAR(255) NULL,
  `type` ENUM('image', 'video', 'link', 'youtube', 'text', 'warning') NULL DEFAULT 'text',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`author`)
    REFERENCES `danger_info`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `danger_info`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `danger_info`.`category` ;

CREATE TABLE IF NOT EXISTS `danger_info`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `danger_info`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `danger_info`.`comment` ;

CREATE TABLE IF NOT EXISTS `danger_info`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `postId` INT NOT NULL,
  `text` TEXT NULL,
  `createdAt` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` INT NOT NULL,
  `url` VARCHAR(255) NULL,
  PRIMARY KEY (`id`, `postId`),
  CONSTRAINT `fk_comment_post1`
    FOREIGN KEY (`postId`)
    REFERENCES `danger_info`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `danger_info`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `danger_info`.`warning`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `danger_info`.`warning` ;

CREATE TABLE IF NOT EXISTS `danger_info`.`warning` (
  `id` INT NOT NULL,
  `lat` FLOAT(10,6) NULL,
  `lon` FLOAT(10,6) NULL,
  `urgent` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_warning_post1`
    FOREIGN KEY (`id`)
    REFERENCES `danger_info`.`post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `danger_info`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `danger_info`.`admin` ;

CREATE TABLE IF NOT EXISTS `danger_info`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `danger_info`.`login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `danger_info`.`login` ;

CREATE TABLE IF NOT EXISTS `danger_info`.`login` (
  `userId` INT NOT NULL,
  `loginTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `logoutTime` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`, `loginTime`),
  CONSTRAINT `fk_login_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `danger_info`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `danger_info`.`warning_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `danger_info`.`warning_category` ;

CREATE TABLE IF NOT EXISTS `danger_info`.`warning_category` (
  `warningId` INT NOT NULL,
  `categoryId` INT NOT NULL,
  PRIMARY KEY (`warningId`, `categoryId`),
  CONSTRAINT `fk_warning_has_danger_category_warning1`
    FOREIGN KEY (`warningId`)
    REFERENCES `danger_info`.`warning` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_warning_has_danger_category_danger_category1`
    FOREIGN KEY (`categoryId`)
    REFERENCES `danger_info`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
