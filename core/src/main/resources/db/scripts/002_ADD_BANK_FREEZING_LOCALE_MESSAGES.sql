INSERT INTO BANK_ADAPTER_LOCALE_MESSAGE(ID, CDT, CREATED_BY_ID, CREATED_BY_TABLE, MDT, MODIFIED_BY_ID,
                                        MODIFIED_BY_TABLE, RDT, MESSAGE_CODE, TEXT_EN, TEXT_KY, TEXT_RU)
VALUES (BANK_ADAPTER_LOCALE_MESSAGE_SEQ.nextval, sysdate, 1, 'MANAGER', sysdate, 1, 'MANAGER', null,
        'FREEZING_INFO_TEXT',
        'Loan will be issued in {0} hours.',
        N'Кредит {0} сааттан кийин чегерилет',
        'Кредит будет зачислен через {0} часа.');


INSERT INTO BANK_ADAPTER_LOCALE_MESSAGE (ID, CDT, CREATED_BY_ID, CREATED_BY_TABLE, MDT, MODIFIED_BY_ID,
                                         MODIFIED_BY_TABLE, RDT, MESSAGE_CODE, TEXT_EN, TEXT_KY, TEXT_RU)
VALUES (BANK_ADAPTER_LOCALE_MESSAGE_SEQ.nextval, sysdate, 1, 'MANAGER', sysdate, 1, 'MANAGER', null,
        'FREEZING_INFO_BUTTON_TEXT', 'Why?', N'Эмне үчүн мындай?', 'Почему так?');


INSERT INTO BANK_ADAPTER_LOCALE_MESSAGE (ID, CDT, CREATED_BY_ID, CREATED_BY_TABLE, MDT, MODIFIED_BY_ID,
                                         MODIFIED_BY_TABLE, RDT, MESSAGE_CODE, TEXT_EN, TEXT_KY, TEXT_RU)
VALUES (BANK_ADAPTER_LOCALE_MESSAGE_SEQ.nextval, sysdate, 1, 'MANAGER', sysdate, 1, 'MANAGER', null,
        'FREEZING_DETAILS_BUTTON_TEXT', 'OK', N'Түшүнүктүү', 'Понятно');


INSERT INTO BANK_ADAPTER_LOCALE_MESSAGE (ID, CDT, CREATED_BY_ID, CREATED_BY_TABLE, MDT, MODIFIED_BY_ID,
                                         MODIFIED_BY_TABLE, RDT, MESSAGE_CODE, TEXT_EN, TEXT_KY, TEXT_RU)
VALUES (BANK_ADAPTER_LOCALE_MESSAGE_SEQ.nextval, sysdate, 1, 'MANAGER', sysdate, 1, 'MANAGER', null,
        'FREEZING_DETAILS_TITLE',
        'We added cooling period as required by NBKR.',
        N'КРУБдун талабы боюнча биз токтотуп туруу мезгилин коштук',
        'По требованию НБКР мы добавили период охлаждения');


INSERT INTO BANK_ADAPTER_LOCALE_MESSAGE (ID, CDT, CREATED_BY_ID, CREATED_BY_TABLE, MDT, MODIFIED_BY_ID,
                                         MODIFIED_BY_TABLE, RDT, MESSAGE_CODE, TEXT_EN, TEXT_KY, TEXT_RU)
VALUES (BANK_ADAPTER_LOCALE_MESSAGE_SEQ.nextval, sysdate, 1, 'MANAGER', sysdate, 1, 'MANAGER', null,
        'FREEZING_DETAILS_DESCRIPTION',
        'In accordance with Decree No. 2024-П-12/63-1-(НПА) dd. 27.11.2024 of the Board of the ' ||
        'National Bank of the Kyrgyz Republic, the following minimum cooling periods have been set for online loans ' ||
        'during which the bank may not credit money to the borrower’s account:
        15,000 to 50,000 KGS – 3 hours;
        50,000 to 100,000 KGS – 6 hours;
        over 100,000 KGS – 24 hours.',
        N'Кыргыз Республикасынын Улуттук банк Башкармасынын 2024-жылдын 27-ноябрындагы №2024-П-12/63-1-(НПА) ' ||
        N'Токтомуна ылайык онлайн кредиттер үчүн минималдуу токтотуп туруу мезгили белгиленет, бул мезгил аралыгында ' ||
        N'банк карыз алуучунун эсебине акча каражаттарын которо албайт:
        15 000 сомдон 50 000 сомго чейин – 3 саат;
        50 000 сомдон 100 000 сомго чейин – 6 саат;
        100 000 сомдон жогору – 24 саат.',
        'Согласно Постановлению Правления Национального банка КР от 27.11.2024 года  № 2024-П-12/63-1-(НПА) ' ||
        'для онлайн кредитов устанавливается минимальный период охлаждения в течении которого банк не может ' ||
        'перечислить денежные средства  на счет заемщика:
        От 15 000 сомов до 50 000 сомов – 3 часа;
        От 50 000 до 100 000 сомом – 6 часов;
        Свыше 100 000 сомов – 24 часа');