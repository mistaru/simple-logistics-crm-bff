INSERT INTO AUTH (ID,
                  CDT,
                  CREATED_BY_ID,
                  CREATED_BY_TABLE,
                  MDT,
                  MODIFIED_BY_ID,
                  RDT, BLOCKED, LAST_ACTIVITY, PASSWORD, USERNAME)
VALUES (auth_seq.nextval, sysdate, null, null, sysdate, null, null, null, null,
        '$s0$41010$zJLxGht2W30t4YGhAPYVpw==$DkW57bXCOhhg3Xjy1Q0ioSQUMK9LGC5G1j8m8tj/gFg=', 'admin');

INSERT INTO ROLE (ID, CDT, CREATED_BY_ID, CREATED_BY_TABLE, MDT, MODIFIED_BY_ID, RDT, DESCRIPTION, NAME)
VALUES (auth_seq.nextval, sysdate, null, null, sysdate, null, null, null, 'Admin');

INSERT INTO AUTH_ROLE (ACTIVE, AUTH_ID, ROLE_ID)
VALUES (true, (SELECT MAX(ID) FROM AUTH), (SELECT MAX(ID) FROM ROLE));
