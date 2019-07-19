insert into user(firstName, lastName, patronymic,position,login,password,role)
values('Ivan', 'Ivanov', 'Ivan','Ivan','Ivan','Ivan','admin');
insert into category(name, parentId, parentName)
values('source',1,'source');
insert into product(name,price,count)
values('tv', 100, 100);
insert into product_category(productId,categoryId)
values(1,1);