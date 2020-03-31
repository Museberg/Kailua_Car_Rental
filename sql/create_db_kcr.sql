DROP DATABASE IF EXISTS kcr;
CREATE DATABASE kcr; 

USE kcr;

DROP TABLE IF EXISTS brands;
CREATE TABLE brands
(
	id			INT				NOT NULL	PRIMARY KEY		AUTO_INCREMENT,
    brand_name	VARCHAR(20)		NOT NULL
);

DROP TABLE IF EXISTS models;
CREATE TABLE models
(
    model		VARCHAR(20)		NOT NULL	PRIMARY KEY		UNIQUE,
    brand_id	INT				NOT NULL,
    FOREIGN KEY (brand_id)
		REFERENCES brands (id)
);

/*DROP TABLE IF EXISTS car_specs;
CREATE TABLE car_specs
(
	id				INT									NOT NULL	PRIMARY KEY		AUTO_INCREMENT, 
    type_name		ENUM('luxury', 'family', 'sport')	NOT NULL, 
    gear_type		ENUM('manual', 'automatic')			NOT NULL, 
    air_condition	TINYINT								NOT NULL, 
    ccm				INT, 	-- Tesla cars do not contain an internal combustion engine (ICE)
    hp				INT									NOT NULL, 
    seat_type		VARCHAR(20)							NOT NULL, 
    seat_number		INT									NOT NULL, 
    cruise_control	TINYINT								NOT NULL
);*/

DROP TABLE IF EXISTS cars;
CREATE TABLE cars 
(
	id					INT									NOT NULL	PRIMARY KEY 	AUTO_INCREMENT,
    registration_number	VARCHAR(10)							NOT NULL	UNIQUE,
    first_registration	DATE								NOT NULL,
    fuel_type			VARCHAR(45)							NOT NULL,
    odometer			INT									NOT NULL,
    model				VARCHAR(20)							NOT NULL,
    type_name			ENUM('luxury', 'family', 'sport')	NOT NULL, 
    gear_type			ENUM('manual', 'automatic')			NOT NULL, 
    air_condition		TINYINT								NOT NULL, 
    ccm					INT, 	-- Tesla cars do not contain an internal combustion engine (ICE)
    hp					INT									NOT NULL, 
    seat_type			VARCHAR(20)							NOT NULL, 
    seat_number			INT									NOT NULL, 
    cruise_control		TINYINT								NOT NULL,
	FOREIGN KEY (model_id)
		REFERENCES models (model)
);

DROP TABLE IF EXISTS zip_codes;
CREATE TABLE zip_codes
(
	zip				INT				NOT NULL		PRIMARY KEY,
    city			VARCHAR(85)		NOT NULL,
    country			VARCHAR(50)		NOT NULL
);

DROP TABLE IF EXISTS addresses;
CREATE TABLE addresses
(
	id					INT			NOT NULL		PRIMARY KEY		AUTO_INCREMENT,
    street_name			VARCHAR(45)	NOT NULL,
    street_number		INT			NOT NULL,
    apartment_number	VARCHAR(6),
    zip_code			INT			NOT NULL,
    FOREIGN KEY (zip_code)
		REFERENCES zip_codes (zip)
);

DROP TABLE IF EXISTS renters;
CREATE TABLE renters
(
	id				INT				NOT NULL		PRIMARY KEY		AUTO_INCREMENT,
    first_name		VARCHAR(25)		NOT NULL,
    last_name		VARCHAR(25)		NOT NULL,
    telephone		BIGINT			NOT NULL,
    email			VARCHAR(45)		NOT NULL,
    driver_license	VARCHAR(20)		NOT NULL,
	driver_since	DATE			NOT NULL,
    address_id		INT				NOT NULL,
    FOREIGN KEY (address_id)
		REFERENCES addresses (id)
);

DROP TABLE IF EXISTS contracts;
CREATE TABLE contracts 
(
	id				INT			NOT NULL		PRIMARY KEY		AUTO_INCREMENT,
	car_id			INT			NOT NULL, 
    renter_id		INT			NOT NULL, 
    start_date		DATETIME	NOT NULL, 
    end_date		DATETIME	NOT NULL, 
    max_km			INT, 
    start_km		INT			NOT NULL,
    FOREIGN KEY (car_id)
		REFERENCES cars (id),
    FOREIGN KEY (renter_id)
		REFERENCES renters (id)
);

INSERT INTO brands VALUES 
(1, 'Volkswagen'),
(2, 'Audi'),
(3, 'Mercedes-Benz'),
(4, 'BMW'),
(5, 'Tesla'),
(6, 'Toyota'),
(7, 'Kia'),
(8, 'El-Trans'),
(9, 'Aston Martin'),
(10, 'Koenigsegg'),
(11, 'Hyundai'),
(12, 'Saab'),
(13, 'Ford'),
(14, 'Honda');

INSERT INTO models VALUES
('AE86', '6'),
('Corolla', '6'),
('Model Y', '5'),
('Model 3', '5'),
('Model S', '5'),
('Golf GTI', '1'),
('Polo', '1'),
('Up!', '1'),
('Focus', '13'),
('Fiesta', '13'),
('Mustang 5.0 V8 GT', '13'),
('A8', '2'),
('770', '3'),
('320i', '4'),
('Carnival', '7'),
('City-El', '8'),
('DB11', '9'),
('Regera', '10'),
('i30', '11'),
('JAS 39 Gripen', '12'),
('Civic', '14');

INSERT INTO cars VALUES 
(0, )






