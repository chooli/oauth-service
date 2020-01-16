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

DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
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