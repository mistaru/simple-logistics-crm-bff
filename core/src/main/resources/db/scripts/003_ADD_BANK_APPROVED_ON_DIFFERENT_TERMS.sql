insert into notification_dict (id, cdt, mdt, event_type, header_en, header_ky, header_ru, sending_type, template_en,
                               template_ky, template_ru, priority, active)
values (notification_dict_seq.nextval,
        current_timestamp,
        current_timestamp,
        'BANK_ADAPTER_APPROVED_ON_DIFFERENT_TERMS',
        'Кредит одобрен на других условиях',
        'Кредит одобрен на других условиях',
        'Кредит одобрен на других условиях',
        'MANUAL_SEND',
        'Вам доступно {approved_amount} с на {approved_term} мес. вместо {requested_amount} с на {requested_term} мес. Получите прямо в приложении.',
        'Вам доступно {approved_amount} с на {approved_term} мес. вместо {requested_amount} с на {requested_term} мес. Получите прямо в приложении.',
        'Вам доступно {approved_amount} с на {approved_term} мес. вместо {requested_amount} с на {requested_term} мес. Получите прямо в приложении.',
        0,
        true);

insert into notification_template_type (notification_template_id, template_type)
values ((select id FROM notification_dict where event_type = 'BANK_ADAPTER_APPROVED_ON_DIFFERENT_TERMS'), 'PUSH');