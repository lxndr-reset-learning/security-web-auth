-- creating tables with this schema is necessary, because if it's not, we would have to manually manager SQL queries

CREATE TABLE users
(
    username varchar(15) PRIMARY KEY ,
    password varchar(100),
    enabled  boolean
);

CREATE TABLE authorities
(
    username  varchar(15),
    authority varchar(25),
    foreign key (username) references users (username)
);

INSERT INTO users (username, password, enabled)
VALUES ('user1', '{noop}user1', true),
       ('user2', '{noop}user2', true),
       ('user3', '{noop}user3', true);

INSERT INTO authorities (username, authority)
VALUES ('user1', 'ROLE_EMPLOYEE'),
       ('user2', 'ROLE_HR'),
       ('user3', 'ROLE_HR'),
       ('user3', 'ROLE_MANAGER');
