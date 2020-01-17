DROP TABLE IF EXISTS users;
CREATE TABLE users (
    username varchar(50) not null primary key,
    password varchar(120) not null,
    email varchar(255),
    active boolean not null
);
CREATE UNIQUE INDEX unique_key_email ON users(email(255));

DROP TABLE IF EXISTS authorities;
CREATE TABLE authorities(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    username varchar(50) not null,
    role varchar(50) not null,
    foreign key (username) references users (username)
);
CREATE INDEX unique_key_username ON authorities(username(50));

DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    client_id VARCHAR(256),
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);
CREATE UNIQUE INDEX unique_key_client_id ON oauth_client_details(client_id(256));