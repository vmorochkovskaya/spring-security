INSERT INTO users (username, password, enabled)
values ('user', '{bcrypt}$2a$12$GLCL3xItDih.1VH.Ks/q1e8Xg03DayxAHgKGp/rT/wSdY.kb6SWRW', true);
INSERT INTO users (username, password, enabled)
values ('admin', '{bcrypt}$2a$12$GLCL3xItDih.1VH.Ks/q1e8Xg03DayxAHgKGp/rT/wSdY.kb6SWRW', true);
INSERT INTO users (username, password, enabled)
values ('full', '{bcrypt}$2a$12$GLCL3xItDih.1VH.Ks/q1e8Xg03DayxAHgKGp/rT/wSdY.kb6SWRW', true);

INSERT INTO authorities (username, authority)
values ('user', 'VIEW_INFO');
INSERT INTO authorities (username, authority)
values ('admin', 'VIEW_ADMIN');
INSERT INTO authorities (username, authority)
values ('full', 'VIEW_INFO');
INSERT INTO authorities (username, authority)
values ('full', 'VIEW_ADMIN');