# oauth-service
a microservice for oauth2 authentication services

# Database Setup 
This is for mysql. Please start mysql service first

- Login MySQL with root

- Create new database

``` CREATE DATABASE jumkid ```

- Create user and permission
``` CREATE USER 'jumkid'@'localhost' IDENTIFIED BY 'password'```;
``` GRANT ALL ON jumkid.* TO 'jumkid'@'localhost' WITH GRANT OPTION```;

