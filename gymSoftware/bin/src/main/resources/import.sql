insert into role (id, name) values (1, 'ADMINT')
insert into role (id, name) values (2, 'COACH')
insert into role (id, name) values (3, 'CLIENT')

insert into user (id, firstname, lastname, email, password) values (1, 'Elena', 'Krunic', 'lelekrunic1@gmail.com', 'pereCvetka!8')
insert into user (id, firstname, lastname, email, password) values (2, 'Lelika', 'Cvetic', 'krunicele@gmail.com', 'wetrovi691!')
--insert into user (id, firstname, lastname, email, password) values (3, 'Elena', 'Krunic', 'lelekrunic1@gmail.com', 'pereCvetka!8')

insert into role_has_user (user_id, role_id) values (1, 1)
insert into role_has_user (user_id, role_id) values (2, 2)
insert into role_has_user (user_id, role_id) values (1, 3)

insert into training (id, name, duration, reserved, price, client, coach ) values (1, "Functional trainings", "2021-09-07", true, 300.20 , 1, 1)
insert into training (id, name, duration, reserved, price, client, coach ) values (2, "Core training", "2021-10-10", true, 663.20 , 1, 2)

