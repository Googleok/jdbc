drop sequence seq_author;
create sequence seq_author start 1;

-- 저자
CREATE TABLE "author"
(
	"no"   integer      NOT NULL default nextval('seq_author'), -- 번호
	"name" VARCHAR(200) NOT NULL  -- 이름
);

insert into author values( nextval('seq_author'), 'plato2');

select * from pet;
select * from author;



