create table if not exists districts (
    id int generated always as identity,
    population int,
    district varchar(32),
    status varchar(8),
    date timestamp,
    primary key (id)
);

create table if not exists invasions (
    id int generated always as identity,
    district varchar(32),
    cog varchar(32),
    total_cogs int,
    date timestamp,
    primary key (id)
);

create table if not exists population (
    id int generated always as identity,
    population int,
    date timestamp,
    primary key (id)
);