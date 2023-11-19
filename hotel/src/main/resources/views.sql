CREATE VIEW `upod_otel`.`ReservationView` AS
SELECT
    MAX(r.id) as id,
    r.room_id,
    MAX(r.checkin_date) AS checkin_date,
    MAX(r.checkout_date) AS checkout_date,
    MAX(r.checkedin_time) AS checkedin_time,
    MAX(r.checkedout_time) AS checkedout_time,
    GROUP_CONCAT(c.full_name) AS customer_name
  FROM `Reservation` as r
  INNER JOIN `ReservationCustomer` as rc ON r.id = rc.reservation_id
  INNER JOIN `Customer` as c ON rc.customer_id = c.id
  GROUP BY r.room_id;

