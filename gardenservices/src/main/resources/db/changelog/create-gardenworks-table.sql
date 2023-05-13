create table gardenworks
(
    id bigint not null auto_increment,
    done bit not null,
    description varchar(255),
    answer varchar(255),
    created_at datetime,
    answered_at datetime,
    primary key (id)
);