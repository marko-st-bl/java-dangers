-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dangers_aid
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dangers_aid` ;

-- -----------------------------------------------------
-- Schema dangers_aid
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dangers_aid` DEFAULT CHARACTER SET utf8 ;
USE `dangers_aid` ;

-- -----------------------------------------------------
-- Table `dangers_aid`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dangers_aid`.`category` ;

CREATE TABLE IF NOT EXISTS `dangers_aid`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dangers_aid`.`aid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dangers_aid`.`aid` ;

CREATE TABLE IF NOT EXISTS `dangers_aid`.`aid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `image` VARCHAR(255) NULL,
  `location` VARCHAR(45) NULL,
  `status` ENUM('valid', 'blocked') NULL DEFAULT 'valid',
  `createdAt` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `categoryId` INT NOT NULL,
  `reportedAsFalse` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_aid_category`
    FOREIGN KEY (`categoryId`)
    REFERENCES `dangers_aid`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
