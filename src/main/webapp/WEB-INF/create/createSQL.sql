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
	point int default 10000,
	primary key(uidx),
	unique key(mid)
);

select * from user order by createDay desc;

select * from user order by createDay desc limit 0,20;

select * from user
where userLevel != '관리자' and mid like '%a%' 
order by createDay desc limit 0,5;



ALTER TABLE user ADD COLUMN point int default 10000;

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
	act char(4) default 'off',
	imgSize int not null,
	mid varchar(20) not null,
	createDayCP datetime default now(),
	viewCP int not null default 0,
	primary key(cidx),
	unique key(cpName),
	FOREIGN KEY(mid) REFERENCES user(mid) 
	ON UPDATE CASCADE
);

alter table company add column mid varchar(20) not null;
alter table company add foreign key(mid) REFERENCES user(mid) ON UPDATE CASCADE;
alter table company add column viewCP int not null default 0;

alter table company add column createDayCP datetime default now();

select * from company;

update company set viewCP =  viewCP + 1 where mid = 'epXEqnKZ';

desc company;

select c.cidx,c.cpName,avg(r.rating) as '별점'
		from company c, reply r 
		where r.boardIdx = CONCAT('c',c.cidx) 
		group by c.cidx 
		order by avg(r.rating) desc;

		
select c.*,avg(r.rating) from company c, reply r where r.boardIdx = CONCAT('c',c.cidx) group by c.cidx order by avg(r.rating) desc;
select c.*,avg(r.rating) from company c, reply r where r.boardIdx = CONCAT('c',c.cidx)  group by c.cidx;
select * from company order by viewCP desc;
select * from company order by createDayCP;

select c.*,avg(r.rating) as 'star' from company c, reply r
				 where r.boardIdx = CONCAT('c',c.cidx) and c.cpExp like '%홈 인테리어%'
				 group by c.cidx 
				 order by avg(r.rating) desc limit 0,5;

select *,avg(r.rating) from company c, reply r
				 where r.boardIdx = CONCAT('c',c.cidx) and c.cpExp like '%홈 인테리어%'
				 group by c.cidx 
				 order by avg(r.rating) desc;


select c.cpName,count(r.ridx) from company c,(select * from reply where rating > 0) as r 
where r.boardIdx = CONCAT('c',c.cidx) 
group by c.cpName;
						
				 
				 
				 
select count(*) as 'CPCnt' from company where cpName like '%벤%';

select count(r.ridx) as CPcnt from (select * from reply group by boardidx) as r;

select * from user u, company c where u.mid = c.mid and u.mid = 'JMdFuRdM';

select * from company;
select * from user u, company c where u.mid = c.mid order by createDayCP desc limit 0,5;
select count(*) as cnt from user u, company c where u.mid = c.mid and cpName like '%디%';
select * from user u, company c 
where u.mid = c.mid and cpExp like '%상업 인테리어%' 
order by createDayCP desc;

select * from user u, company c 
where u.mid = c.mid and (cpExp like '%타일시공%' or cpExp like '%도배장판%') 
order by createDayCP desc;



drop table user;
drop table company;