-- auto-generated definition
DROP TABLE IF EXISTS `contact`;
create table contact
(
    contact_id      bigint auto_increment
        primary key,
    contact_name    varchar(10) null,
    contact_message varchar(50) null,
    contact_email   varchar(25) null,
    deleted int(11)  default 0 not null
)
    comment '联系表';

