-- MySQL Script generated by MySQL Workbench
-- Sun Nov 19 18:53:47 2023
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

-- -----------------------------------------------------
-- Schema upod_otel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `upod_otel` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `upod_otel` ;

-- -----------------------------------------------------
-- Table `upod_otel`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`Customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `full_name` VARCHAR(100) NOT NULL,
  `identity_number` VARCHAR(11) NOT NULL,
  `phone_number` VARCHAR(15) NULL DEFAULT NULL,
  `birth_date` DATE NULL DEFAULT NULL,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `identity_number_UNIQUE` (`identity_number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`Feature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`Feature` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `price` DECIMAL(8,2) NULL DEFAULT '0.00',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
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
AUTO_INCREMENT = 4
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
  `tid` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Room_1_idx` (`tid` ASC) VISIBLE,
  CONSTRAINT `fk_Room_1`
    FOREIGN KEY (`tid`)
    REFERENCES `upod_otel`.`RoomType` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
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
  `checkedout_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `room_id_fk` (`room_id` ASC) VISIBLE,
  CONSTRAINT `room_id_fk`
    FOREIGN KEY (`room_id`)
    REFERENCES `upod_otel`.`Room` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `upod_otel`.`ReservationCustomer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`ReservationCustomer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservation_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `reservation_customer_uniq_id` (`reservation_id` ASC, `customer_id` ASC) VISIBLE,
  UNIQUE INDEX `customer_id_UNIQUE` (`customer_id` ASC) VISIBLE,
  INDEX `fk_ReservationCustomer_1_idx` (`reservation_id` ASC) VISIBLE,
  INDEX `fk_ReservationCustomer_2_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_ReservationCustomer_1`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `upod_otel`.`Reservation` (`id`),
  CONSTRAINT `fk_ReservationCustomer_2`
    FOREIGN KEY (`customer_id`)
    REFERENCES `upod_otel`.`Customer` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
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
AUTO_INCREMENT = 3
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
    REFERENCES `upod_otel`.`Reservation` (`id`),
  CONSTRAINT `fk_ReservationService_2`
    FOREIGN KEY (`service_id`)
    REFERENCES `upod_otel`.`Service` (`id`))
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
  PRIMARY KEY (`id`),
  UNIQUE INDEX `room_feature_uniq_id` (`room_id` ASC, `feature_id` ASC) VISIBLE,
  INDEX `fk_RoomFeature_1_idx` (`room_id` ASC) VISIBLE,
  INDEX `fk_RoomFeature_2_idx` (`feature_id` ASC) VISIBLE,
  CONSTRAINT `fk_RoomFeature_1`
    FOREIGN KEY (`room_id`)
    REFERENCES `upod_otel`.`Room` (`id`),
  CONSTRAINT `fk_RoomFeature_2`
    FOREIGN KEY (`feature_id`)
    REFERENCES `upod_otel`.`Feature` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `upod_otel` ;

-- -----------------------------------------------------
-- Placeholder table for view `upod_otel`.`ReservationView`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `upod_otel`.`ReservationView` (`id` INT, `room_id` INT, `checkin_date` INT, `checkout_date` INT, `checkedin_time` INT, `checkedout_time` INT, `customer_name` INT);

-- -----------------------------------------------------
-- View `upod_otel`.`ReservationView`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upod_otel`.`ReservationView`;
USE `upod_otel`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `upod_otel`.`ReservationView` AS select max(`r`.`id`) AS `id`,`r`.`room_id` AS `room_id`,max(`r`.`checkin_date`) AS `checkin_date`,max(`r`.`checkout_date`) AS `checkout_date`,max(`r`.`checkedin_time`) AS `checkedin_time`,max(`r`.`checkedout_time`) AS `checkedout_time`,group_concat(`c`.`full_name` separator ',') AS `customer_name` from ((`upod_otel`.`Reservation` `r` join `upod_otel`.`ReservationCustomer` `rc` on((`r`.`id` = `rc`.`reservation_id`))) join `upod_otel`.`Customer` `c` on((`rc`.`customer_id` = `c`.`id`))) group by `r`.`room_id`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
