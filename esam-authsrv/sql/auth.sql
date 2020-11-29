-- create table
--drop table oauth_client_details;
create table oauth_client_details (
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

INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('clientId-01', '$2a$10$.WQ1tQge3b6XwiiC7zGhue5xVdDLLVE4/LZXDDBkJOmjGLgC99X2y', 'all',
    'authorization_code,refresh_token,password', null, null, 3600, 36000, null, true);

INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('esam', '$2a$10$lsuYeZ6SwJDWu1LfHci6LONw..HIktWmk50OsxQvcQxvzAoI3NckO', 'all',
    'authorization_code,refresh_token,password', null, null, 3600, 36000, null, true);

INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('clientId-02', '$2a$10$cPxH.yTk/lqDEVv6xCknRuPGQcXeDRYtSHaH.1gvquSuawCxIk/lW', 'all',
    'authorization_code,refresh_token', 'http://localhost:8083', null, 3600, 36000, null, true);
