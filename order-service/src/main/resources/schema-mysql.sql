DROP TABLE IF EXISTS ORDERS;

CREATE TABLE ORDERS
(
  id              BIGINT(20) PRIMARY KEY NOT NULL,
  created_at      BIGINT(20),
  last_modified   BIGINT(20),
  order_number  VARCHAR(255),
  username         VARCHAR(255)
);
