create database if not exists kettlebells;

use kettlebells;

drop table if exists kettlebells;

create table kettlebells (
	serial_no int(11) not null auto_increment,
	weight varchar(30) not null,
	color varchar(20) not null,
	primary key (serial_no)
	
);
