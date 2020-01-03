create table temperature
    (
        id bigint auto_increment,
        constraint temperatures_pk
        primary key (id),
        date TEXT not null,
        temperature double null
    );

create table position
(
    id bigint auto_increment,
    constraint positions_pk
        primary key (id),
    date TEXT not null,
    position int null
);

create table respiration
(
    id bigint auto_increment,
    constraint respiration_pk
        primary key (id),
    date TEXT not null,
    respiration  double null
);