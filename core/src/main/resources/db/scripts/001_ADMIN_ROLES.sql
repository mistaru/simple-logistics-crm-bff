INSERT INTO logistic_auth (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt,
                           blocked, last_activity, password, password_expire_date, username)
VALUES (nextval('logistic_auth_SEQ'), '2025-02-15 07:47:49.439297', null, null, '2025-02-15 07:47:49.439297', null,
        null, null, null, '2025-02-15 07:47:49.439297',
        '$s0$41010$Feo6IYP0vwGh98tKc0PuVw==$caiCX8dvVgFlRBL/vEw0zj6rEN+2bA5u9hp2yvtbmB4=', null, 'admin'),
       (nextval('logistic_auth_SEQ'), '2025-03-16 23:43:51.423239', 9999, 'AUTH', '2025-03-23 02:04:13.501261', 9999,
        'AUTH', null, null, '2025-03-23 02:04:13.501261',
        '$s0$41010$IaU30kq0z56/7VzDPzOgWg==$xo2l2qHa3V+0Wf4qlT98OdqI50bus1GcHpIMd7JfTRI=', '2025-04-22 02:04:13.479580',
        'manager');

INSERT INTO logistic_roles (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt,
                            description, name)
VALUES (nextval('logistic_roles_SEQ'), null, null, null, '2025-03-17 22:03:58.845724', 9999, 'AUTH', null,
        'Отвечает за управление системой, настройку доступа и обеспечение ее стабильной работы', 'admin'),
       (nextval('logistic_roles_SEQ'), '2025-03-16 22:04:15.447190', 9999, 'AUTH', '2025-03-23 02:09:36.165717', 9999,
        'AUTH', null, 'Рядовый сотрудник', 'manager');

INSERT INTO logistic_auth_roles (active, logistic_auth_id, logistic_role_id)
VALUES (true, (SELECT id FROM logistic_auth WHERE username = 'admin'),
        (SELECT id FROM logistic_roles WHERE name = 'admin')),
       (true, (SELECT id FROM logistic_auth WHERE username = 'manager'),
        (SELECT id FROM logistic_roles WHERE name = 'manager'));


-- пароль от admin: qwerty123
-- пароль от manager: Qwerty123!