-- MySQL Script generated by MySQL Workbench
-- Wed Nov 15 13:58:28 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema upod_otel
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `upod_otel` ;

-- -----------------------------------------------------
-- Schema upod_otel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `upod_otel` ;
USE `upod_otel` ;

-- -----------------------------------------------------
-- Table `upod_otel`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`Customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(100) NOT NULL,
  `identity_number` BIGINT NOT NULL,
  `phone_number` VARCHAR(15) NULL DEFAULT NULL,
  `birth_date` DATE NULL DEFAULT NULL,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`Feature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`Feature` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `price` DECIMAL(8,2) NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`RoomType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`RoomType` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`tname` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`Room` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `capacity` INT NULL DEFAULT NULL,
  `price` DECIMAL(8,2) NOT NULL,
  `room_type_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Room_1_idx` (`room_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_Room_1`
    FOREIGN KEY (`room_type_id`)
    REFERENCES `upod_otel`.`RoomType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`Reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `room_id` INT NOT NULL,
  `checkin_date` DATETIME NULL DEFAULT NULL,
  `checkout_date` DATETIME NULL DEFAULT NULL,
  `checkedin_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `room_id_fk` (`room_id` ASC) VISIBLE,
  CONSTRAINT `room_id_fk`
    FOREIGN KEY (`room_id`)
    REFERENCES `upod_otel`.`Room` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`ReservationCustomer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`ReservationCustomer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  INDEX `fk_ReservationCustomer_1_idx` (`reservation_id` ASC) VISIBLE,
  INDEX `fk_ReservationCustomer_2_idx` (`customer_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_ReservationCustomer_1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `upod_otel`.`Reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReservationCustomer_2`
    FOREIGN KEY (`customer_id`)
    REFERENCES `upod_otel`.`Customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`Service`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`Service` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`ReservationService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`ReservationService` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `service_id` INT NOT NULL,
  `unit_price` DECIMAL(8,2) NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ReservationService_1_idx` (`reservation_id` ASC) VISIBLE,
  INDEX `fk_ReservationService_2_idx` (`service_id` ASC) VISIBLE,
  CONSTRAINT `fk_ReservationService_1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `upod_otel`.`Reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReservationService_2`
    FOREIGN KEY (`service_id`)
    REFERENCES `upod_otel`.`Service` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`RoomFeature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`RoomFeature` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `room_id` INT NOT NULL,
  `feature_id` INT NOT NULL,
  INDEX `fk_RoomFeature_1_idx` (`room_id` ASC) VISIBLE,
  INDEX `fk_RoomFeature_2_idx` (`feature_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_RoomFeature_1`
    FOREIGN KEY (`room_id`)
    REFERENCES `upod_otel`.`Room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RoomFeature_2`
    FOREIGN KEY (`feature_id`)
    REFERENCES `upod_otel`.`Feature` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
