insert into role (id, name) values (1, 'ADMINT')
insert into role (id, name) values (2, 'COACH')
insert into role (id, name) values (3, 'CLIENT')

insert into user (id, firstname, lastname, email, password) values (1, 'Elena', 'Krunic', 'lelekrunic1@gmail.com', 'pereCvetka!8')
--insert into user (id, firstname, lastname, email, password) values (2, 'Elena', 'Krunic', 'lelekrunic1@gmail.com', 'pereCvetka!8')
--insert into user (id, firstname, lastname, email, password) values (3, 'Elena', 'Krunic', 'lelekrunic1@gmail.com', 'pereCvetka!8')

insert into role_has_user (user_id, role_id) values (1, 1)
insert into role_has_user (user_id, role_id) values (1, 2)
insert into role_has_user (user_id, role_id) values (1, 3)

