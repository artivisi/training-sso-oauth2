drop table if exists s_users;

create table s_users (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    username VARCHAR(100) UNIQUE NOT NULL, 
    password VARCHAR(255) NOT NULL,
    active BOOLEAN
) Engine=InnoDB;

insert into s_users (username, password, active)
values ('endy', '123', true);
insert into s_users (username, password, active)
values ('adi', '123', true);

create table s_permissions (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    id_user VARCHAR(100) NOT NULL REFERENCES s_users(id),
    user_role VARCHAR(255) NOT NULL
) Engine=InnoDB;

insert into s_permissions (id_user, user_role)
values (1, 'ROLE_SUPERVISOR');

insert into s_permissions (id_user, user_role)
values (1, 'ROLE_OPERATOR');

insert into s_permissions (id_user, user_role)
values (2, 'ROLE_OPERATOR');