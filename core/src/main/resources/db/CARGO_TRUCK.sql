insert into logistic_permissions (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt, description, name)
VALUES (nextval('logistic_permissions_seq'), now(), 1, 'DEVELOPER', now(), 1, 'DEVELOPER', null, 'Фуры и Грузы', 'CARGO_TRUCK');

insert into logistic_role_permissions (permission_access, logistic_permission_id, logistic_role_id)
values (15, 10, 1);