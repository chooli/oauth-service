INSERT INTO users(username, password, email, active) VALUES('xeason', '$2a$10$Vpyua2E7/TioxAnIVu06muvok6Yrk96uNsp.zZBpl0Knv87qM2CmS', 'xeason@jumkid.com', true);
INSERT INTO authorities(username, role) VALUES('xeason', 'USER');

INSERT INTO oauth_client_details(client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES ('jumkid', '$2a$10$TUc5f4QQlKBW3l9erhFrvuFdATAZVYuXM3lo6KzApyRsXXE9K93Le', 'read,write', 'password,client_credentials,refresh_token', null, null, 36000, 240000, null, true);