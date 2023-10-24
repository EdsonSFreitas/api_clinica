create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,
    account_expiration datetime,
    is_account_locked boolean,
    credentials_expiration datetime,
    is_enabled boolean,
    role int,

    primary key(id)

);