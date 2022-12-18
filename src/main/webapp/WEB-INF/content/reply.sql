show tables;

create table reply(
	ridx int not null auto_increment,
	boardIdx varchar(50) not null,
	content text,
	rating double,
	writeDay datetime default now(),
	mid varchar(20) not null,
	primary key(ridx),
	FOREIGN KEY(mid) REFERENCES user(mid) 
	ON UPDATE CASCADE
);

desc reply;

select * from reply;

select * from reply where boardIdx = 'c10' order by writeDay desc;


insert into reply values(default,?,?,?,default,?)














drop table reply;