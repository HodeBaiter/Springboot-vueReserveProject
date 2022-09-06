DROP TABLE IF EXISTS `announcement`;
-- auto-generated definition
create table announcement
(
    id       bigint auto_increment
        primary key,
    markdown varchar(2000) null,
    title    varchar(50)   null
);

