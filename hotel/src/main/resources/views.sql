use `upod_otel`;
CREATE VIEW `upod_otel`.`reservation_view` AS
SELECT r.id, r.room_id, r.checkin_date, r.checkout_date,
  r.checkedin_time, r.checkedout_time  FROM `Reservation` as r
  INNER JOIN `ReservationCustomer` as rc ON r.id = rc.reservation_id;