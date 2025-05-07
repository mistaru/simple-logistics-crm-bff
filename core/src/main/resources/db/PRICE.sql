INSERT INTO public.logistic_permissions (id, cdt, created_by_id, created_by_table, mdt, modified_by_id,
                                         modified_by_table, rdt, description, name)
VALUES (nextval('LOGISTIC_PERMISSIONS_SEQ'), '2025-02-15 08:03:55.018933', null, null, '2025-02-15 08:03:55.018933',
        null, null, null, 'Цены', 'PRICE');

INSERT INTO logistic_role_permissions (permission_access, logistic_permission_id, logistic_role_id)
VALUES (15, 10, (SELECT id FROM logistic_roles WHERE name = 'admin'));