show tables;

create table pds (
	pIdx int not null auto_increment,
	fOriName varchar(100) not null,
	fSysName varchar(110) not null,
	fSize int not null,
	mid varchar(20) not null,
	primary key(pIdx),
	FOREIGN KEY(mid) REFERENCES user(mid) 
	ON UPDATE CASCADE
);
FOREIGN KEY(mid) REFERENCES user(code) 
	ON UPDATE CASCADE
	
select * from pds;

select * from user;

drop table pds;