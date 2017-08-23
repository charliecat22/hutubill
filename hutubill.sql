CREATE DATABASE hutubill;

CREATE TABLE config(
  id int AUTO_INCREMENT,
  key_ varchar(255),
  value varchar(255),
  PRIMARY KEY(id)
) DEFAULT CHARSET = utf8;

CREATE TBALE category(
  id int AUTO_INCREMENT,
  name varchar(255),
  PRIMARY KEY(id)
) DEFAULT CHARSET = utf8;

CREATE TABLE record(
  id int AUTO_INCREMENT,
  spend int,
  cid int,
  comment varchar(255),
  date DATE,
  PRIMARY KEY(id),
  CONSTRAINT 'fk_record_category' FOREIGN KEY('cid') REFERENCES category('id')
) DEFAULT CHARSET =utf8;

CREATE TABLE record (
  id int AUTO_INCREMENT,
  spend int,
  cid int,
  comment varchar(255) ,
  date Date,
  PRIMARY KEY (id),
  CONSTRAINT `fk_record_category` FOREIGN KEY (`cid`) REFERENCES `category` (`id`)
)  DEFAULT CHARSET=utf8;
