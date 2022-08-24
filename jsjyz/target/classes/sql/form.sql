-- auto-generated definition
DROP TABLE IF EXISTS `form`;
create table form
(
    name         varchar(30)  null,
    id           bigint(50) auto_increment
        primary key,
    title        varchar(30)  null,
    question     varchar(255) null,
    create_time  bigint       not null,
    answer       varchar(255) null,
    college      varchar(30)  null,
    reserve_time bigint       null,
    image        varchar(255) null,
    status       varchar(30)  null,
    is_archived varchar(30) not null default 0

);

