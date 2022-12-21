show tables;


create table report(
	replyIdx int not null,
	replyMid varchar(20) not null,
	replyContent text,
	replyWriteDay datetime,
	reportMid varchar(20) not null,
	reportWriteDay datetime default now(),
	FOREIGN KEY(replyMid) REFERENCES user(mid) 
	ON UPDATE CASCADE,
	FOREIGN KEY(reportMid) REFERENCES user(mid) 
	ON UPDATE CASCADE
);

alter table report add FOREIGN KEY(replyMid) REFERENCES user(mid) ON UPDATE CASCADE;

select * from report;

select * from information_schema.table_constraints where table_name = 'report';

alter table report drop foreign key report_ibfk_1;



delete from reply where ridx = 3;
