drop table movies;

create table movies
(
    id      bigint auto_increment primary key,
    name    varchar not null,
    comment varchar not null,
    movie_type_id bigint not null,
    genre   varchar not null,
    date    date    not null,
    foreign key (movie_type_id) references movie_type(id)
);

