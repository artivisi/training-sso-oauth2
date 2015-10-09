create table oauth_client_details (
  client_id VARCHAR2(255) PRIMARY KEY,
  resource_ids VARCHAR2(255),
  client_secret VARCHAR2(255),
  scope VARCHAR2(255),
  authorized_grant_types VARCHAR2(255),
  web_server_redirect_uri VARCHAR2(255),
  authorities VARCHAR2(255),
  access_token_validity NUMBER,
  refresh_token_validity NUMBER,
  additional_information VARCHAR2(255),
  autoapprove NUMBER(1)
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
('jsclient', 'jspasswd', 'belajar', 'read,write', 'implicit', 'http://localhost:7070/implicit/implicit-client.html', 1);
