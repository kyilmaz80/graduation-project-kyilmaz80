use `upod_otel`;
CREATE VIEW `upod_otel`.`view_room` AS
SELECT r.name, r.capacity, r.price, rt.tname FROM `Room` as r 
INNER JOIN `RoomType` as rt ON r.room_type_id = rt.id ;