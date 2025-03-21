-- Corrected INSERT for AUTH
INSERT INTO LOGISTIC_AUTH (ID, CDT, MDT, RDT, BLOCKED, LAST_ACTIVITY, PASSWORD, USERNAME)
VALUES (nextval('LOGISTIC_AUTH_SEQ'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, null, null, null,
        '$s0$41010$Feo6IYP0vwGh98tKc0PuVw==$caiCX8dvVgFlRBL/vEw0zj6rEN+2bA5u9hp2yvtbmB4=', 'admin');

-- Corrected INSERT for ROLE
INSERT INTO LOGISTIC_ROLES (ID, CDT, MDT, RDT, DESCRIPTION, NAME)
VALUES (nextval('LOGISTIC_ROLES_SEQ'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, null, null, 'Admin');

-- Corrected INSERT for AUTH_ROLE
INSERT INTO LOGISTIC_AUTH_ROLES (ACTIVE, LOGISTIC_AUTH_ID, LOGISTIC_ROLE_ID)
VALUES (true, (SELECT MAX(ID) FROM LOGISTIC_AUTH), (SELECT MAX(ID) FROM LOGISTIC_ROLES));