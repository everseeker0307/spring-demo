create database if not exists spring_demo default character set utf8;
use spring_demo;
show engines;

create table if not exists user(id varchar(32) primary key not null, username varchar(32) not null, password varchar(32), email varchar(32),
    reg_date bigint, state int, unique(username)) engine=InnoDB;