CREATE DATABASE gameSQL;
USE gameSQL;

CREATE TABLE Team(
   codTime integer(10) PRIMARY KEY,

   nomeTime varchar(50),

   cidade varchar(80)
);

CREATE TABLE Jogador(
   idJogador integer(10) PRIMARY KEY,

   nomeJogador varchar(100),

   dataNasc date,

   altura decimal(4,2),

   peso decimal (4,1),

   TimeCodigo integer(10), FOREIGN KEY(TimeCodigo) REFERENCES Team(codTime)
);

INSERT INTO Team VALUES (101,"Santos","SP");
INSERT INTO Team VALUES (102,"Palmeiras","SP");
INSERT INTO Team VALUES (103,"Santos","SP");