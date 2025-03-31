INSERT INTO cargo (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt, description,
                   quantity, shipment_date, status, volume, warehouse_arrival_date, weight, client_id)
VALUES (nextval('cargo_SEQ'), '2025-03-23 01:26:04.082359', null, null, '2025-03-23 01:56:44.276428', 9999, 'AUTH',
        null, 'Очень важный груз', 23, '2025-03-01 01:24:00.000000', 'ASSEMBLY_AT_FACTORY', 6,
        '2025-03-30 01:24:00.000000', 32, null),
       (nextval('cargo_SEQ'), '2025-03-23 01:46:26.998228', null, null, '2025-03-23 01:58:08.763689', 9999, 'AUTH',
        null, 'Груз с чаем', 50, '2025-02-28 01:46:00.000000', 'IN_TRANSIT_TO_CITY', 45, '2025-04-25 01:46:00.000000',
        120, null);


INSERT INTO client (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt,
                    additional_info, client_code, email, full_name, phone_number, whatsapp_number)
VALUES (nextval('client_SEQ'), '2025-03-23 01:54:50.653695', 9999, 'AUTH', '2025-03-23 01:54:50.653695', 9999, 'AUTH',
        null, 'дополнительная информация', 'client_01', 'kasymovargen996@gmail.com', 'Касымов Арген Айбекович', '0708654585',
        '0708654585');


INSERT INTO truck (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt,
                   additional_information, departure_warehouse, driver_phone, registration_country, service_fee, arrival_date_actual,
                   arrival_date_planned, arrival_warehouse, departure_date_actual, departure_date_planned,
                   driver_fullname, volume_available_m3, volume_occupied_m3, volume_total_m3)
VALUES (nextval('truck_SEQ'), '2025-03-22 21:08:39.726900', null, null, '2025-03-23 02:01:26.106978', 9999, 'AUTH',
        null, 'Вроде норм водила', 'Склад 1', '89250121324', 'Россия', 15, '2025-04-30 21:08:39.000000',
        '2025-04-30 21:08:39.000000', 'Склад 2', '2025-03-26 21:08:39.000000', '2025-03-26 21:08:39.000000',
        'Шамуродов Мурад', 65, 55, 120);


INSERT INTO country_dict (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt,
                          description, name)
VALUES (nextval('country_dict_SEQ'), '2025-03-19 00:11:22.478062', 9999, 'AUTH', '2025-03-19 00:11:22.478062', 9999,
        'AUTH', null, '996', 'Кыргызстан'),
       (nextval('country_dict_SEQ'), '2025-03-19 00:24:20.654361', 9999, 'AUTH', '2025-03-23 02:22:24.519164', 9999,
        'AUTH', null, '77-79', 'Россия');


INSERT INTO city_dict (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt,
                       description, name, country_dict_id)
VALUES (nextval('city_dict_SEQ'), '2025-03-22 19:01:32.098779', 9999, 'AUTH', '2025-03-22 19:01:32.098779', 9999,
        'AUTH', null, 'столица', 'Бишкек', (SELECT id FROM country_dict WHERE name = 'Кыргызстан')),
       (nextval('city_dict_SEQ'), '2025-03-19 23:58:59.187770', 9999, 'AUTH', '2025-03-22 19:01:51.215963', 9999,
        'AUTH', null, 'столица', 'Москва', (SELECT id FROM country_dict WHERE name = 'Россия')),
       (nextval('city_dict_SEQ'), '2025-03-22 19:05:15.496220', 9999, 'AUTH', '2025-03-22 19:05:15.496220', 9999,
        'AUTH', null, 'южная столица', 'Ош', (SELECT id FROM country_dict WHERE name = 'Кыргызстан'));


INSERT INTO warehouse (id, cdt, created_by_id, created_by_table, mdt, modified_by_id, modified_by_table, rdt, address,
                       name, phone_number, volume_m3, city_id, is_local)
VALUES (nextval('warehouse_SEQ'), '2025-03-22 19:21:56.074980', null, null, '2025-03-23 02:02:18.012848', 9999, 'AUTH',
        null,
        'Проспект Чуй', 'Склад 1', '0312512321', 5000.00, (SELECT id FROM city_dict WHERE name = 'Бишкек'), true),

       (nextval('warehouse_SEQ'), '2025-03-22 19:44:01.457017', null, null, '2025-03-23 02:02:52.296628', 9999, 'AUTH',
        null,
        'проспект Манаса', 'Склад 3', '0312432123', 16000.00, (SELECT id FROM city_dict WHERE name = 'Бишкек'), true);
