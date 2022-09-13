-- auto-generated definition
DROP TABLE IF EXISTS `user`;
create table user
(
    user_id bigint(20) primary key auto_increment,
    user_name varchar(20) null ,
    email varchar(50) null ,
    permissions varchar(20) default 'user'  not null,
    password varchar(50) not null,
    deleted int(11)  default 0 not null
)
    comment '联系表';
