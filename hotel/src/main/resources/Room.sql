<!-- Odalar bu görünümde oluşturulur ve düzenlenir. Odalara ekstra özellikler -->
<!-- atanabilmelidir. (Oda özelliklerinden tanımlanmış olanlar) -->
CREATE TABLE Room
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    `capacity` INT,
    `price` decimal(8, 2) NOT NULL,
     CONSTRAINT capacity_chk CHECK (capacity > 0)
);

CREATE TABLE Feature
(
	`id`  INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45)
);

CREATE TABLE RoomFeature
(
	`room_id` INT NOT NULL,
    `feature_id` INT NOT NULL
);

<!-- Müşterilere sağlanan hizmetler (oda servisi, spa vs gibi) burada tanımlanır. -->
CREATE TABLE Service
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45)
);

<!-- Oda Özellikleri-->
CREATE TABLE Reservation
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `room_id` INT NOT NULL,
    `checkin_date` DATETIME,
    `checkout_date` DATETIME,
    `checkedin_time` DATETIME,
    CONSTRAINT `room_id_fk` FOREIGN KEY(`room_id`) REFERENCES Room(`id`)
);

CREATE TABLE ReservationService
(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `reservation_id` INT NOT NULL,
    `service_id` INT NOT NULL,
    `unit_price` decimal(8, 2) NOT NULL,
    `quantity` INT
);

<!-- Müşterilerin oluşturulduğu ve düzenlendiği ekran. -->
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

INSERT INTO Feature VALUES(1, 'TV');
INSERT INTO Feature VALUES(2, 'BANYO');
INSERT INTO Feature VALUES(3, 'HAVLU');


