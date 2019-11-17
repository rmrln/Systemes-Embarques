create table patients
(
    id bigint auto_increment,
    constraint patients_pk
        primary key (id),
    id_medecin bigint null,
    first_name TEXT not null,
    last_name TEXT not null,
    age int null
);


create table medecins
(
    id bigint auto_increment,
    constraint medecins_pk
        primary key (id),
    first_name TEXT not null,
    last_name TEXT not null
);

create table temperatures
    (
        id bigint auto_increment,
        constraint temperatures_pk
        primary key (id),
        date TEXT not null,
        id_patient bigint null,
        temperature double null
    );

create table positions
(
    id bigint auto_increment,
    constraint positions_pk
        primary key (id),
    date TEXT not null,
    id_patient bigint null,
    position int null
);

create table respiration
(
    id bigint auto_increment,
    constraint respiration_pk
        primary key (id),
    date TEXT not null,
    id_patient bigint null,
    airflow  double null
);