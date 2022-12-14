show tables;

create table user (
	uidx int not null auto_increment,
	mid varchar(20) not null,
	pwd varchar(100) not null,
	email varchar(100) not null,
	birth datetime not null,
	tel varchar(18) not null,
	createDay datetime default now(),
	userLevel varchar(8) default '일반',
	primary key(uidx),
	unique key(mid)
);

select * from user;

create table company (
	cidx int not null auto_increment,
	name varchar(10) not null,
	cpName varchar(50) not null,
	cpAddr varchar(100) not null,
	cpImg varchar(100) not null,
	cpHomePage varchar(30),
	cpIntro text,
	cpIntroImg text,
	cpExp varchar(100) not null,
	primary key(cidx),
	unique key(cpName)
);

select * from company;

















drop table user;
drop table company;