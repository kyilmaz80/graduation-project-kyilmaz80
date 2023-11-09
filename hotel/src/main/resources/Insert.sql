USE `upod_otel`;
ALTER TABLE Feature AUTO_INCREMENT=1;
INSERT INTO Feature (name) VALUES('Banyo');
INSERT INTO Feature (name) VALUES('Minibar');
INSERT INTO Feature (name, price) VALUES('Deniz Manzaralı', 200);
INSERT INTO Feature (name) VALUES('Duş');
INSERT INTO Feature (name) VALUES('Elektrikli Su Isıtıcısı');
INSERT INTO Feature (name, price) VALUES('JAKUZI', 200);
INSERT INTO Feature (name) VALUES('KILITLI KASA');
INSERT INTO Feature (name) VALUES('Klima');
INSERT INTO Feature (name) VALUES('Oturma Alanı');
INSERT INTO Feature (name) VALUES('Saç Kurutma Makinesi');
INSERT INTO Feature (name) VALUES('LCD TV');
INSERT INTO Feature (name) VALUES('Telefon', 50);
INSERT INTO Feature (name) VALUES('Tuvalet');
INSERT INTO Feature (name) VALUES('Kablosuz Internet');

INSERT INTO RoomType (tname) VALUES("Ekonomik-Oda");
INSERT INTO RoomType (tname) VALUES("Standart-Oda");
INSERT INTO RoomType (tname) VALUES("Suit-Oda");


INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Eko-Oda-001", 1, 1000, 1);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Eko-Oda-002", 1, 1000, 1);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Eko-Oda-003", 1, 1000, 1);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Std-Oda-001", 1, 1400, 2);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Std-Oda-002", 1, 1400, 2);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Std-Oda-003", 1, 1400, 2);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Std-Oda-003", 2, 1500, 2);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Suit-Oda-001", 1, 1800, 3);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Suit-Oda-002", 1, 1800, 3);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Suit-Oda-003", 1, 1800, 3);
INSERT INTO Room (name, capacity, price, room_type_id) VALUES("Suit-Oda-004", 2, 1900, 3);
