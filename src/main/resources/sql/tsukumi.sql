create table dic_info
(
    id          int auto_increment
        primary key,
    code        varchar(100)     not null,
    name        varchar(100)     not null,
    type        varchar(100)     not null,
    p_code      varchar(100)     null,
    del_flag    bit default b'0' not null,
    attribute_1 varchar(100)     null,
    attribute_2 varchar(100)     null,
    attribute_3 varchar(100)     null,
    create_time datetime         not null,
    update_time datetime         null,
    constraint dic_info_UN
        unique (code, type, del_flag)
)
    comment '字典表';

create table grumble_info
(
    id          int auto_increment
        primary key,
    user_id     int              not null,
    content     longtext         null,
    title       varchar(100)     null,
    description varchar(300)     null,
    create_time datetime         not null,
    update_time datetime         null,
    del_flag    bit default b'0' not null,
    type        varchar(20)      not null,
    mode        varchar(100)     null comment '心情：dic'
)
    comment '吐槽内容';

create table oauth_access_token
(
    token_id          varchar(255) null,
    token             mediumblob   null,
    authentication_id varchar(255) not null
        primary key,
    user_name         varchar(255) null,
    client_id         varchar(255) null,
    authentication    mediumblob   null,
    refresh_token     varchar(255) null
);

create table oauth_approvals
(
    userid         varchar(255) null,
    clientid       varchar(255) null,
    scope          varchar(255) null,
    status         varchar(10)  null,
    expiresat      timestamp    null,
    lastmodifiedat timestamp    null
);

create table oauth_client_details
(
    client_id               varchar(255)  not null
        primary key,
    resource_ids            varchar(255)  null,
    client_secret           varchar(255)  null,
    scope                   varchar(255)  null,
    authorized_grant_types  varchar(255)  null,
    web_server_redirect_uri varchar(255)  null,
    authorities             varchar(255)  null,
    access_token_validity   int           null,
    refresh_token_validity  int           null,
    additional_information  varchar(4096) null,
    autoapprove             varchar(255)  null
);

create table oauth_client_token
(
    token_id          varchar(255) null,
    token             mediumblob   null,
    authentication_id varchar(255) not null
        primary key,
    user_name         varchar(255) null,
    client_id         varchar(255) null
);

create table oauth_code
(
    code           varchar(255) null,
    authentication mediumblob   null
);

create table oauth_refresh_token
(
    token_id       varchar(255) null,
    token          mediumblob   null,
    authentication mediumblob   null
);

create table tag_info
(
    id   int auto_increment
        primary key,
    name varchar(100) null,
    type varchar(100) null,
    mode varchar(100) null comment '心情：dic_info',
    constraint tag_info_UN
        unique (name, type)
)
    comment '标签表';

create table user_info
(
    id          int auto_increment
        primary key,
    username    varchar(100) null,
    mobile      varchar(100) null,
    create_time datetime     not null,
    update_time datetime     null,
    password    varchar(100) null
)
    comment '用户表';

INSERT INTO tsukumi.dic_info (id, code, name, type, p_code, del_flag, attribute_1, attribute_2, attribute_3, create_time, update_time) VALUES (1, 'anonymous', '匿名', 'grumbleType', '', false, '', '', '', '2021-01-13 03:55:18', '2021-01-13 03:55:18');
INSERT INTO tsukumi.dic_info (id, code, name, type, p_code, del_flag, attribute_1, attribute_2, attribute_3, create_time, update_time) VALUES (2, 'private', '私人', 'grumbleType', '', false, '', '', '', '2021-01-13 03:57:22', '2021-01-13 03:57:22');
INSERT INTO tsukumi.dic_info (id, code, name, type, p_code, del_flag, attribute_1, attribute_2, attribute_3, create_time, update_time) VALUES (3, 'public', '广场', 'grumbleType', '', false, '', '', '', '2021-01-13 03:57:29', '2021-01-13 03:57:29');