set client_min_messages=WARNING;

create sequence hibernate_sequence minvalue 1000000;

create table users (
    id          bigint primary key,
    username    varchar(255) unique not null,
    password    varchar(255) not null,
    enabled     boolean not null default 't',
    first_name  varchar(255),
    last_name   varchar(255),
    email       varchar(255)
);

create unique index lower_username_index on users (lower(username));

insert into users values (1000, 'admin', md5('abcd'),
    't', 'Administrator', 'System', 'admin@localhost.localdomain');
insert into users values (1001, 'ialexan', md5('abcd'),
    't', 'Ishag', 'Alexanian', 'ishag_alexanian@hotmail.com');
insert into users values (1002, 'cysun', md5('abcd'),
    't', 'Chengyu', 'Sun', 'csun@calstatela.edu');

-- ADMIN : system administrators.
-- APPROVER : users who can approve the data submitted by other users.
-- TRUSTED_USER : users whose submitted data is automatically approved.
-- REGULAR_USER : users whose submitted data needs approval.

create table authorities (
    user_id bigint not null references users(id),
    role    varchar(255)
);

insert into authorities values (1000, 'ADMIN');
insert into authorities values (1001, 'APPROVER');
insert into authorities values (1002, 'REGULAR_USER');

-- for remember-me service
create table persistent_logins (
    series      varchar(64) primary key,
    username    varchar(64) not null,
    token       varchar(64) not null,
    last_used   timestamp not null
);

create table species (
    id          bigint primary key,
    name        varchar(255) unique not null,
    description varchar(8000)
);

insert into species values (1, 'Western Gray Squirrel', '');
insert into species values (2, 'Douglas Squirrel', '');
insert into species values (3, 'Eastern Gray Squirrel', '');
insert into species values (4, 'Eastern Fox Squirrel', '');
insert into species values (5, 'Melanistic Squirrel', '');

create unique index lower_name_index on species (lower(name));

create table files (
	id          bigint primary key,
    name        varchar(255) not null,
    type        varchar(255),
    size        bigint,
    date        timestamp not null default current_timestamp,
    owner_id    bigint not null references users(id),
    deleted     boolean not null default 'f'
);

create table sitings (
    id              bigint primary key,
    species         bigint not null references species(id),
    longitude       double precision not null,
    latitude        double precision not null,
    timestamp       timestamp not null,
    rejected        boolean not null default 'f',
    submitted_by    bigint references users(id),
    verified_by     bigint references users(id),
    image_id        bigint references files(id)
);


