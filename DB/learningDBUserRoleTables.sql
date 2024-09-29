-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema learning
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema learning
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `learning` DEFAULT CHARACTER SET utf8 ;
USE `learning` ;

-- -----------------------------------------------------
-- Table `learning`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `learning`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(15) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `last_name_UNIQUE` (`last_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `learning`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `learning`.`role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `permissions` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `learning`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `learning`.`user_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_role_role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_role_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `learning`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `learning`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
