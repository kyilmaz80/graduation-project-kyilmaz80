CREATE TABLE Room
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_name` VARCHAR(45),
    `capacity` INT,
    `price` decimal(8, 2) NOT NULL
);

CREATE TABLE Feature
(
	`id`  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `feature_name` VARCHAR(45)
);

CREATE TABLE RoomFeature
(
	`room_id` INT NOT NULL,
    `feature_id` INT NOT NULL
);

CREATE TABLE Service
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `service_name` VARCHAR(45)
);

CREATE TABLE Reservation
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_id` INT NOT NULL,
    `checkin_date` DATETIME,
    `checkout_date` DATETIME,
    `checkedin_time` DATETIME
);

CREATE TABLE ReservationService
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `reservation_id` INT NOT NULL,
    `service_id` INT NOT NULL,
    `unit_price` decimal(8, 2) NOT NULL,
    `quantity` INT
);

CREATE TABLE Customer
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `full_name` VARCHAR(100) NOT NULL,
    `identity_number` BIGINT NOT NULL,
    `phone_number`VARCHAR(15),
    `birth_date` DATE,
    `description` VARCHAR(250)
);

CREATE TABLE ReservationCustomer
(
	`reservation_id` INT NOT NULL,
    `customer_id` INT NOT NULL
);


