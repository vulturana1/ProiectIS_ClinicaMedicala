Drop database if exists Proiect_IS;
CREATE SCHEMA  Proiect_IS;
USE Proiect_IS;

drop table if exists doctor;
CREATE TABLE IF NOT EXISTS doctor (
    idDoctor INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY ,
	doctorName VARCHAR(45) NOT NULL,
    departament VARCHAR(45) NOT NULL,
    cabinetNumber int NOT NULL,
    phoneNumber varchar(10) 
);

drop table if exists nurse;
CREATE TABLE IF NOT EXISTS nurse (
    idNurse INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
	nurseName VARCHAR(45) NOT NULL,
    departament VARCHAR(45) NOT NULL,
    phoneNumber varchar(10) NOT NULL UNIQUE
);

drop table if exists pacient;
CREATE TABLE IF NOT EXISTS pacient (
    idPacient INT NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
	pacientName VARCHAR(45) NOT NULL,
    phoneNumber varchar(10) NOT NULL UNIQUE,
	adress VARCHAR(45) NOT NULL,
    CNP VARCHAR(45) NOT NULL UNIQUE
);