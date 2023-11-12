DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

create table users(
	username VARCHAR(50)  not null primary key,
	password VARCHAR(100)  not null,
	enabled boolean not null
);

create table authorities (
	username VARCHAR(50)  not null,
	authority VARCHAR(50)  not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index if not exists ix_auth_username on authorities (username,authority);