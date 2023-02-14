create table movies (
    id bigint auto_increment primary key,
    name varchar not null,
    comment varchar not null,
    type varchar not null,
    genre varchar not null,
    date date not null
)