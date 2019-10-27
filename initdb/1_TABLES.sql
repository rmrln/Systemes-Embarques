create table users
(
    id bigint auto_increment,
    constraint users_pk
        primary key (id),
    picture TEXT not null,
    cv_title TEXT not null,
    first_name TEXT not null,
    last_name TEXT not null,
    age int null,
    phone_number int null,
    address TEXT not null,
    linkedin TEXT not null,
    mail TEXT not null,
    presentation TEXT not null,
    skill TEXT not null,
    hobby TEXT not null,
    language TEXT not null,
    formations TEXT not null,
    experiences TEXT not null
);


