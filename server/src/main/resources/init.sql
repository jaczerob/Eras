create table if not exists district_population (
    id int generated always as identity,
    population int,
    district varchar(32),
    date timestamp,
    primary key (id)
);

create table if not exists total_population (
    id int generated always as identity,
    population int,
    date timestamp,
    primary key (id)
);