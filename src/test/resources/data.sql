insert into type (id, name) values
(type_id_seq.nextval, 'Сыр'),
(type_id_seq.nextval, 'Молоко'),
(type_id_seq.nextval, 'Мороженое');

insert into product (id, name, expired_date, price, type_id) values
(product_id_seq.nextval, 'Эскимо_тест' , '2020-03-25', 10, 3),
(product_id_seq.nextval, 'Сыр Пермский_тест', '2020-04-25', 12, 1),
(product_id_seq.nextval, 'Молоко нытвенское_тест', '2020-05-25', 1, 2);
