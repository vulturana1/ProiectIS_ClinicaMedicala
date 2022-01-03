Drop database if exists ClinicaMedicala;
CREATE SCHEMA  ClinicaMedicala;
USE ClinicaMedicala;

drop table if exists user;
CREATE TABLE IF NOT EXISTS user (
    id INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY ,
    firstName VARCHAR(45) NOT NULL,
    lastName VARCHAR(45) NOT NULL,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(45) NOT NULL,
    role VARCHAR(45) NOT NULL,
    enabled  bit(1) DEFAULT b'1',
    phoneNumber varchar(10) 
);

drop table if exists recipe;
CREATE TABLE IF NOT EXISTS recipe (
    id INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY ,
    usernameDoctor VARCHAR(45) NOT NULL,
    usernamePatient VARCHAR(45) NOT NULL,
    listOfDrugs VARCHAR(1000) NOT NULL
);
drop table if exists doctorNotify;
CREATE TABLE IF NOT EXISTS doctorNotify (
    id INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY ,
    usernameDoctor VARCHAR(45) NOT NULL,
    message VARCHAR(10000) NOT NULL
);

drop table if exists appointment;
CREATE TABLE IF NOT EXISTS appointment (
    id INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY ,
	usernameDoctor VARCHAR(45) ,
    usernamePatient VARCHAR(45) NOT NULL,
    date VARCHAR(45) NOT NULL,
    time VARCHAR(45) NOT NULL
);

INSERT INTO clinicamedicala.user (firstName, lastName, username, password, role, enabled, phoneNumber) VALUES ('Ana', 'Vultur', 'ana@v', '12345', 'DOCTOR', b'1', '0741753590');
INSERT INTO clinicamedicala.user (firstName, lastName, username, password, role, enabled, phoneNumber) VALUES ('Constantin', 'Senila', 'senila@c', '11111', 'PATIENT', b'1', '0789786746');
INSERT INTO clinicamedicala.user (firstName, lastName, username, password, role, enabled, phoneNumber) VALUES ('Aura', 'Petricele', 'aura@p', 'parola', 'NURSE', b'1', '0711223423');
