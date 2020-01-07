create table temperatures
    (
        id bigint auto_increment,
        constraint temperatures_pk
        primary key (id),
        date TEXT not null,
        temperature double null
    );

create table positions
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
    airflow  double null
);