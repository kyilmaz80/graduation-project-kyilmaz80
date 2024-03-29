CREATE VIEW `upod_otel`.`ReservationView` AS
SELECT
    r.id,
    rm.id AS room_id,
    rm.name AS room_name,
    r.checkin_date AS checkin_date,
    r.checkout_date AS checkout_date,
    r.checkedin_time AS checkedin_time,
    r.checkedout_time AS checkedout_time,
    GROUP_CONCAT(c.full_name) AS customer_name
  FROM `Reservation` as r
  INNER JOIN `ReservationCustomer` as rc ON r.id = rc.reservation_id
  INNER JOIN `Customer` as c ON rc.customer_id = c.id
  INNER JOIN `Room` AS rm ON rm.id = r.room_id
  GROUP BY r.room_id, r.id, r.checkin_date, r.checkout_date ;