DROP TABLE IF EXISTS users;
CREATE TABLE users (
    username varchar(50) not null primary key,
    password varchar(120) not null,
    email varchar(255),
    active boolean not null
);

DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities(
  username varchar(50) not null,
  role varchar(50) not null,
  foreign key (username) references users (username)
);
