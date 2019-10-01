drop table if exists phone_book;
create table if not exists phone_book (
    id int unsigned not null auto_increment primary key,
    number int(7) unsigned not null,
    last_name varchar(64) not null,
    first_name varchar(32) not null,
    middle_name varchar(32),
    address varchar(32) not null
);
insert into phone_book (number, last_name, first_name, middle_name, address) 
values 
('111111', 'Алексеев', 'Алексей', 'Алексеевич', 'Фокина ул., д. 1'),
('111111', 'Алексеев', 'Андрей', 'Алексеевич', 'Фокина ул., д. 1'),
('222222', 'Алексеев', 'Борис', 'Борисович', 'Кутузова ул., д. 2'),
('333333', 'Бобров', 'Богдан', 'Бонифитьевич', 'Фокина ул., д. 10'),
('333333', 'Владимирова', 'Анна', 'Александровна', 'Фокина ул., д. 10'),
('444444', 'Гусев', 'Григорий', 'Григорьевич', 'Кутузова пер., д. 5');