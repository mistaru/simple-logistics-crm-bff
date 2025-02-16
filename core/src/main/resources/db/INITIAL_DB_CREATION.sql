-- 1. Create the database, owned by the new user
CREATE DATABASE crm_bff WITH OWNER = postgres;
GRANT ALL PRIVILEGES ON DATABASE crm_bff TO postgres;

COMMIT;