INSERT INTO logistic_role_permissions (permission_access, logistic_permission_id, logistic_role_id)
VALUES (15, 1, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (15, 2, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (15, 3, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (15, 4, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (15, 5, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (15, 6, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (15, 7, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (15, 8, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (15, 9, (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (4, 4, (SELECT id FROM logistic_roles WHERE name = 'manager')),
       (4, 7, (SELECT id FROM logistic_roles WHERE name = 'manager')),
       (4, 9, (SELECT id FROM logistic_roles WHERE name = 'manager'));
