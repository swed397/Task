create table movie_type (
    id bigint auto_increment primary key,
    name varchar not null
);

insert into movie_type (name) values ('полнометражный'), ('короткометражный'), ('сериал');