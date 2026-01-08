insert into logistic_permissions (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, description, name)
values (nextval('logistic_permissions_seq'), current_timestamp, 1, 'DEVELOPER', current_timestamp,
        1,  'DEVELOPER' , 'Перевозчики', 'CARRIER');

insert into logistic_role_permissions (permission_access, logistic_permission_id, logistic_role_id) values (15, (select id from logistic_permissions where name = 'CARRIER'),  1);