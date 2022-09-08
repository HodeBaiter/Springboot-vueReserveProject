DROP TABLE IF EXISTS `key_words`;
CREATE TABLE key_words
(
    id bigint(20) not null auto_increment primary key,
    key_words varchar(255) not null,
    article_id bigint(20) not null,
    deleted int(11)  default 0 not null
)