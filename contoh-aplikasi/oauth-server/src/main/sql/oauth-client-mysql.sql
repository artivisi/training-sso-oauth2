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
  autoapprove BOOLEAN
);

insert into oauth_client_details 
(client_id, client_secret, resource_ids, 
scope, authorized_grant_types, authorities) values 
('clientauthcode', '123456', 'belajar', 'read,write', 'authorization_code, refresh_token', 'CLIENT');

insert into oauth_client_details 
(client_id, client_secret, resource_ids, 
scope, authorized_grant_types) values 
('clientcred', '123456', 'belajar', 'trust', 'client_credentials');

insert into oauth_client_details 
(client_id, client_secret, resource_ids, 
scope, authorized_grant_types) values 
('clientapp', '123456', 'belajar', 'read,write', 'password');

insert into oauth_client_details 
(client_id, client_secret, resource_ids, 
scope, authorized_grant_types, web_server_redirect_uri, autoapprove) values 
('jsclient', 'jspasswd', 'belajar', 'read,write', 'implicit', 'http://localhost:7070/implicit/implicit-client.html', true);
