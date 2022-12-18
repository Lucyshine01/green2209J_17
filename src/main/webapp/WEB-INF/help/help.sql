show tables;

create table help(
	hidx int not null auto_increment,
	title varchar(50) not null,
	content text not null,
	conf char(5) default 'off',
	answer text,
	mid varchar(20) not null,
	primary key(hidx),
	FOREIGN KEY(mid) REFERENCES user(mid) 
	ON UPDATE CASCADE
);

select * from help;

