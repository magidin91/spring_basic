DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS product;

CREATE SEQUENCE type_id_seq START WITH 1 INCREMENT BY 1;
create table type (id serial primary key, name varchar(200) not null);

CREATE SEQUENCE product_id_seq START WITH 1 INCREMENT BY 1;
create table product (id serial primary key, name varchar(200) not null, expired_date date not null,
price int not null, type_id int not null references type(id));