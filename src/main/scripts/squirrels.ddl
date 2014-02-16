
    create table authorities (
        user_id int8 not null,
        role varchar(255)
    );

    create table files (
        id int8 not null,
        date timestamp not null,
        deleted boolean not null,
        name varchar(255) not null,
        size int8,
        type varchar(255),
        owner_id int8 not null,
        primary key (id)
    );

    create table sitings (
        id int8 not null,
        latitude float8 not null,
        longitude float8 not null,
        rejected boolean not null,
        timestamp timestamp not null,
        image_id int8,
        species int8 not null,
        submitted_by int8,
        verified_by int8,
        primary key (id)
    );

    create table species (
        id int8 not null,
        description varchar(255),
        name varchar(255) not null,
        primary key (id)
    );

    create table users (
        id int8 not null,
        email varchar(255),
        enabled boolean not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    );

    alter table species 
        add constraint UK_29ixq8ot8e88rk6v7jpkisgr3 unique (name);

    alter table users 
        add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);

    alter table authorities 
        add constraint FK_s21btj9mlob1djhm3amivbe5e 
        foreign key (user_id) 
        references users;

    alter table files 
        add constraint FK_5ok1awgnfwcf01537ylbycyq1 
        foreign key (owner_id) 
        references users;

    alter table sitings 
        add constraint FK_ahm31vcyh32bnfnwca2ctdrvb 
        foreign key (image_id) 
        references files;

    alter table sitings 
        add constraint FK_opdf79n9i2n3b72rr44ekxuw6 
        foreign key (species) 
        references species;

    alter table sitings 
        add constraint FK_9cf8v7lu83jn34h8915jiwykw 
        foreign key (submitted_by) 
        references users;

    alter table sitings 
        add constraint FK_22qrm6a7xop64iqmh3dyhn31n 
        foreign key (verified_by) 
        references users;

    create sequence hibernate_sequence;
