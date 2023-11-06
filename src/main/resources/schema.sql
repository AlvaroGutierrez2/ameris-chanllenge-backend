DROP TABLE IF EXISTS employee ;
CREATE TABLE employee(
   id           BIGINT AUTO_INCREMENT PRIMARY KEY,
   id_number    INT,
   first_name   VARCHAR(100),
   last_name    VARCHAR(100),
   phone_number VARCHAR(50),
   salary       DECIMAL(10, 2)
);