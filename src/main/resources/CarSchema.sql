SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS owner; 
DROP TABLE IF EXISTS car; 
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE owner
(id BIGINT NOT NULL AUTO_INCREMENT 
, firstname VARCHAR(100) NOT NULL
, lastname VARCHAR(100) NOT NULL
, city VARCHAR(50) 
, ssn VARCHAR(15) NOT NULL
, yob BIGINT
,PRIMARY KEY (id)
);


INSERT INTO owner (firstname, lastname, city, ssn, yob) 
VALUES ("Maria", "Marison", "Manse", "150574-113I", 1974)
, ("Minnie", "Minison", "Turku", "250170-111M",1970);



CREATE TABLE car (
id BIGINT NOT NULL AUTO_INCREMENT
, brand VARCHAR(50) NOT NULL
, model VARCHAR(50) NOT NULL
, color VARCHAR(50) NOT NULL
, register_number VARCHAR(50) NOT NULL
, production_year INT
, price DECIMAL
, ownerid BIGINT
, PRIMARY KEY (id)
, FOREIGN KEY (ownerid) REFERENCES owner(id));

INSERT INTO car (brand, model, color, register_number, production_year, price, ownerid) 
VALUES ('Ford', 'Taunus', 'blue', 'xxx-111', 1979, 12000, 1), 
('Volkswagen','Golf', 'red', 'yyy-222', 1990, 7000, 1);

