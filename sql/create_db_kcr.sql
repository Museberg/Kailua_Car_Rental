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
    car_type			ENUM('luxury', 'family', 'sport')	NOT NULL, 
    gear_type			ENUM('manual', 'automatic')			NOT NULL, 
    air_conditioning	TINYINT								NOT NULL, 
    ccm					INT, 	-- Tesla cars do not contain an internal combustion engine (ICE)
    hp					INT									NOT NULL, 
    seat_type			VARCHAR(20)							NOT NULL, 
    seat_number			INT									NOT NULL, 
    cruise_control		TINYINT								NOT NULL,
	FOREIGN KEY (model)
		REFERENCES models (model)
);

DROP TABLE IF EXISTS zip_codes;
CREATE TABLE zip_codes
(
	zip				VARCHAR(10)		NOT NULL		PRIMARY KEY,
    city			VARCHAR(85)		NOT NULL,
    country			VARCHAR(50)		NOT NULL
);

DROP TABLE IF EXISTS addresses;
CREATE TABLE addresses
(
	id					INT				NOT NULL		PRIMARY KEY		AUTO_INCREMENT,
    street_name			VARCHAR(45)		NOT NULL,
    street_number		VARCHAR(5)		NOT NULL,
    apartment_number	VARCHAR(6),
    zip_code			VARCHAR(10)		NOT NULL,
    FOREIGN KEY (zip_code)
		REFERENCES zip_codes (zip)
);

DROP TABLE IF EXISTS renters;
CREATE TABLE renters
(
	id				INT				NOT NULL		PRIMARY KEY		AUTO_INCREMENT,
    first_name		VARCHAR(25)		NOT NULL,
    last_name		VARCHAR(25)		NOT NULL,
    telephone		VARCHAR(15)		NOT NULL,
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
    start_km		INT			NOT NULL,
    max_km			INT, 
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
(1, 'UIG-758', '2016-05-20', 'gasoline', 200000, 'Focus', 'sport', 'manual', true, 2000, 201, 'leather', 5, false),
(2, 'AMD-580', '2014-07-13', 'gasoline', 180000, 'Up!', 'sport', 'manual', true, 1000, 250, 'fabric', 5, false),
(3, 'GTX-980', '2010-10-24', 'gasoline', 100000, 'Fiesta', 'luxury', 'automatic', true, 2500, 150, 'leather', 5, true),
(4, 'INI-990', '2013-02-08', 'gasoline', 120000, 'Carnival', 'family', 'manual', true, 1800, 130, 'fabric', 7, true),
(5, 'KEA-737', '2014-01-01', 'gasoline', 250000, 'i30', 'luxury', 'automatic', true, 1200, 150, 'leather', 5, true),
(6, 'LEE-744', '2012-12-12', 'gasoline', 800000, 'Golf GTI', 'luxury', 'automatic', true, 2000, 200, 'leather', 5, true),
(7, 'ASS-455', '2016-02-20', 'gasoline', 230000, 'Mustang 5.0 V8 GT', 'sport', 'manual', true, 5000, 550, 'leather', 2, false),
(8, 'LOG-173', '2018-04-20', 'diesel', 30000, 'DB11', 'luxury', 'automatic', true, 2500, 400, 'leather', 2, true),
(9, 'ARN-070', '2017-09-11', 'jet fuel', 230000, 'JAS 39 Gripen', 'luxury', 'automatic', true, 50000, 15000, 'leather', 5, true),
(10, 'BRE-712', '2019-06-30', 'electric', 15000, 'Model S', 'luxury', 'automatic', true, NULL, 796, 'leather', 5, true);

INSERT INTO zip_codes VALUES
('4180','Sorø','Denmark'),
('2000','Frederiksberg','Denmark'),
('2605','Brøndby','Denmark'),
('3460','Birkerød','Denmark'),
('2880','Bagsværd','Denmark'),
('2100','Østerbro','Denmark'),
('3520','Farum','Denmark'),
('4171','Glumsø','Denmark'),
('0918','Københavns Pakkecenter','Denmark');

INSERT INTO addresses VALUES
(1, 'Falskvej', '13', NULL, '4180'),
(2, 'Storvej', '26', '3. th', '4171'),
(3, 'Mellemvej', '6', '5. tv', '2605'),
(4, 'Frederiksberg Bredegade', '13b', '337', '2000'),
(5, 'Vejgade', '69', NULL, '3460'),
(6, 'Viborggade', '35', NULL, '2100'),
(7, 'Industrivej', '1', NULL, '0918'),
(8, 'Farum Gydevej', '120', NULL, '3520');

INSERT INTO renters VALUES
(1, 'Danny', 'Baker', '420420420', 'bigdaddy@hotmail.com', '7i767r86e786', '2001-05-20', 5),
(2, 'John', 'Wick', '234876192', 'babayaega@outlook.com', '326vigdf7w6e', '2005-10-07', 8),
(3, 'Postmand', 'Per', '01110000', 'paddydaddy69@me.com', 'f7vgw6e978ad', '1981-09-16', 7),
(4, 'Byggemand', 'Bob', '12345678', 'bb@gmail.com', 'bkadsfi76ds', '1983-04-26', 3),
(5, 'Freddy', 'Kreuger', '678123830', '3spooky5you@outspook.com', 'g8uvw7362dg', '1977-11-26', 1),
(6, 'John', 'Smith', '20274527', 'johnsmi@me.com', 'f976faa90hu', '1999-04-23', 2),
(7, 'Carl', 'Jensen', '58584657', 'carlj@outlook.com', 'sd78f02nofd', '2007-01-15', 6),
(8, 'Boris', 'Johnson', '555695833', 'bj@gmail.com', 'ag7g83jkahds', '1990-07-28', 4);

INSERT INTO contracts VALUES
(0, 1, 1, '2020-02-01 09:00:00', '2020-02-02 09:00:00', 200000, 200200),
(0, 2, 2, '2020-02-10 16:00:00', '2020-02-17 16:00:00', 180000, 181400),
(0, 3, 3, '2020-02-08 09:00:00', '2020-02-13 09:00:00', 100000, 101000),
(0, 4, 4, '2020-02-13 09:00:00', '2020-02-20 09:00:00', 120000, 121400),
(0, 9, 5, '2020-02-15 09:00:00', '2020-02-29 09:00:00', 150000, 153000);






