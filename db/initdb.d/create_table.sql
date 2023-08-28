CREATE TABLE user (
    `login_id`     VARCHAR(11)  NOT NULL,
    `username`     VARCHAR(15)  NOT NULL,
    `password`     VARCHAR(120),
    `department`   VARCHAR(50),
    `position`     VARCHAR(25),
    `project`      VARCHAR(30),
    PRIMARY KEY (login_id)
);

CREATE TABLE rental (
    `rental_no`    BIGINT       NOT NULL AUTO_INCREMENT,
    `user_id`      VARCHAR(11)  NOT NULL,
    `seat_no`      INT          NOT NULL,
    `start_date`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `end_date`     TIMESTAMP    NOT NULL,
    PRIMARY KEY (rental_no)
);

CREATE TABLE seat (
    `seat_no`    INT         NOT NULL,
    `seat_row`   VARCHAR(3)  NOT NULL,
    `seat_col`   VARCHAR(3)  NOT NULL,
    `row_len`    INT         NOT NULL,
    `col_len`    INT         NOT NULL,
    `status`     VARCHAR(10)  DEFAULT 'Empty',
    PRIMARY KEY (seat_no)
);

create table token
(
    id         int             not null
        primary key,
    expired    bit             not null,
    revoked    bit             not null,
    token      varchar(255)    null,
    token_type enum ('BEARER') null,
    user_id    varchar(255)    null,
    constraint UK_pddrhgwxnms2aceeku9s2ewy5
        unique (token),
    constraint FKe32ek7ixanakfqsdaokm4q9y2
        foreign key (user_id) references user (login_id)
);

create table token_seq
(
    next_val bigint null
);



