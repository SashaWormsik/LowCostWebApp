create sequence hibernate_sequence start 1 increment 1;
create table airplane
(
    id              int4        not null,
    model           varchar(20) not null,
    number          int4        not null check (number <= 999 AND number >= 100),
    number_of_seats int4        not null check (number_of_seats >= 1 AND number_of_seats <= 300),
    primary key (id)
);
create table credentials
(
    id          int4         not null,
    active      boolean      not null,
    create_date timestamp,
    password    varchar(255) not null,
    user_id     int4,
    primary key (id)
);
create table route
(
    id        int4 not null,
    city_from varchar(30),
    city_to   varchar(30),
    price     numeric(19, 2),
    primary key (id)
);
create table schedule
(
    id               int4      not null,
    arrival          timestamp not null,
    departure        timestamp not null,
    places_available int4,
    price_schedule   numeric(19, 2),
    airplane_id      int4,
    route_id         int4,
    primary key (id)
);
create table ticket
(
    id                    int4         not null,
    luggage               boolean      not null,
    passenger_first_name  varchar(20)  not null,
    passenger_last_name   varchar(20)  not null,
    passport_id           varchar(255) not null,
    priority_boarding     boolean      not null,
    priority_registration boolean      not null,
    purchase_date         timestamp,
    total_price           numeric(19, 2),
    id_user_info          int4,
    schedule_id           int4,
    primary key (id)
);
create table user_info
(
    id         int4         not null,
    email      varchar(255) not null,
    first_name varchar(255),
    last_name  varchar(255),
    login      varchar(255) not null,
    role       int4         not null,
    token      varchar(255),
    primary key (id)
);
alter table airplane
    add constraint UK_n0a1ty5uygax4rj976fj5lr2k unique (number);
alter table user_info
    add constraint UK_gnu0k8vv6ptioedbxbfsnan9g unique (email);
alter table user_info
    add constraint UK_q4yfto14rcleuvnpo5sp55c0s unique (login);
alter table credentials
    add constraint FK8cigkiubhfvjwmrkcv6ik9jq5 foreign key (user_id) references user_info;
alter table schedule
    add constraint FK18q0sc2q2regfbgb0ynu13rwo foreign key (airplane_id) references airplane;
alter table schedule
    add constraint FKnijrqlnbae9vvpgj6pnaqrl0q foreign key (route_id) references route;
alter table ticket
    add constraint FK5n9m0xhcv69in8vu75sjyrc1s foreign key (id_user_info) references user_info;
alter table ticket
    add constraint FKdmmaqgvu0kjjlpsivmgnvurl5 foreign key (schedule_id) references schedule;
