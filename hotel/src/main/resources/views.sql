CREATE VIEW `upod_otel`.`reservation_view` AS
SELECT r.id, r.room_id, r.checkin_date, r.checkout_date,
  r.checkedin_time, r.checkedout_time, c.full_name as customer_name
  FROM `Reservation` as r
  INNER JOIN `ReservationCustomer` as rc ON r.id = rc.reservation_id
  INNER JOIN `Customer` as c ON rc.customer_id = c.id;