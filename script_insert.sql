
---INSERT tabla user

insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('tobias', 'b7c4c45ff55aded9e43389c5868e383b', 'canabarro', 'tobiascanabarro@gmail.com', 'tobi', '1998-05-14')


insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('pablo', '2e6edb52fa0ea8167a5a621d31937c41', 'forcinito', 'pablo.forcinito@hotmail.com ', 'forci', '1990-05-10');


insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('roberto', '5c4aeb3b45a6c275c7ee941606a3556d', 'zacarello', 'roberto@gmail.com ', 'robert', '1989-10-13');


insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('carlos', 'bba020785cd36a52df715bb06cf30071', 'capozzuca', 'carlos@gmail.com ', 'negro', '1989-11-29');



insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('ignacio', '0b42914ad4e27903fd89ac0d86724222', 'ortiz', 'ignacio@gmail.com ', 'nacho', '1992-04-11');


insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('mariano', '2212b12a41c766b0e240b6baeb7350f0', 'kaimakamian', 'mariano@gmail.com ', 'marian', '1980-01-01');


insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('alejandro', '2d3c007ae60e9cda73bb26435f2bf5b0', 'mostovoi', 'alejandro@gmail.com ', 'ale', '1980-01-01');


insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('maria', '372e49d242e450b924196e30818b8c1e', 'diaz', 'maria@gmail.com ', 'maria', '1980-01-01');


insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('marcela', 'efb3a49389ccb980f484ce42a585f923', 'perez', 'marcela@gmail.com ', 'marce', '1980-01-01');


insert into lab.user (name, password, surname, email, nickname, birthday) 
values ('carla', 'e4c041bbbf08c4012fea60a9ee79816e', 'gomez', 'carla@gmail.com ', 'carla', '1980-01-01');


--INSERT tabla user_post

insert into lab.user_post (id_user, date_publication, post) 
values (1, '2020-11-30', 'hola mundo!');


insert into lab.user_post (id_user, date_publication, post) 
values (1, '2020-11-30', ':D');


insert into lab.user_post (id_user, date_publication, post) 
values (2, '2020-11-30', ':C');


insert into lab.user_post (id_user, date_publication, post) 
values (2, '2020-11-30', 'xD');


insert into lab.user_post (id_user, date_publication, post) 
values (3, '2020-11-30', 'Si, messi');


insert into lab.user_post (id_user, date_publication, post) 
values (3, '2020-11-30', 'messirve');


insert into lab.user_post (id_user, date_publication, post) 
values (4, '2020-11-30', 'que paso ?');


insert into lab.user_post (id_user, date_publication, post) 
values (4, '2020-11-30', 'ya se!');


--INSERT tabla request_relationship

insert into lab.user_post (id_user_receive, id_user_send, state) 
values (1, 2, true);



insert into lab.user_post (id_user_receive, id_user_send, state) 
values (1, 3, true);


insert into lab.user_post (id_user_receive, id_user_send, state) 
values (1, 4, false);


