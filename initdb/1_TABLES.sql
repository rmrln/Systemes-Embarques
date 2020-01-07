
create table respiration
(
    id bigint auto_increment,
    constraint respiration_pk
        primary key (id),
    date TEXT not null,
    airflow  double null
);