show tables;


create table report(
	replyIdx int not null,
	replyMid varchar(20) not null,
	replyContent text,
	replyWriteDay datetime,
	reportMid varchar(20) not null,
	reportWriteDay datetime default now(),
	FOREIGN KEY(replyIdx) REFERENCES reply(ridx) 
	ON UPDATE CASCADE,
	FOREIGN KEY(replyMid) REFERENCES user(mid) 
	ON UPDATE CASCADE,
	FOREIGN KEY(reportMid) REFERENCES user(mid) 
	ON UPDATE CASCADE
);

select * from report;

