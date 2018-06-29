insert into resource (path) values ('/r1');
insert into resource (path) values ('/r2');
insert into resource (path) values ('/r3');
insert into resource (path) values ('/r4');

insert into user_profile (name, password) values ('admin', 'adminPassword');
insert into user_profile (name, password) values ('basic', 'basicPassword');

insert into user_profile_2_resource (user_profile_id, resource_id) values(5,11);
insert into user_profile_2_resource (user_profile_id, resource_id) values(5,12);
insert into user_profile_2_resource (user_profile_id, resource_id) values(5,13);
insert into user_profile_2_resource (user_profile_id, resource_id) values(5,14);
insert into user_profile_2_resource (user_profile_id, resource_id) values(6,11);

select * from user_profile_2_resource;