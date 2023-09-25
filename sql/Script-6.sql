create table client (
 id serial primary key,
 cpf varchar(16) unique not null,
 full_name varchar(100) not null
);

create table account (
 id serial primary key,
 agency int not null,
 number_account varchar(10)not null,
 balance numeric check(balance > 0) not null,
 active int not null,
 id_client int not null references client(id)
);

create table operations (
 id serial primary key,
 type_operation int not null,
 value_operation numeric not null,
 time_operation timestamp not null,
 id_account int not null references account(id)
);

