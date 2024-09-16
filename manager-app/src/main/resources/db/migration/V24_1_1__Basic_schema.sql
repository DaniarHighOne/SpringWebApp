create schema if not exists user_managment;

create table user_managment.t_user(
    id serial primary key,
    c_username varchar not null
)