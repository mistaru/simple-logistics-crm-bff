-- ONLY 3 STEPS REQUIRED !!!
-- 1. Create the database (if it doesn't exist)
CREATE DATABASE crm_bff WITH OWNER = postgres
         ENCODING = 'UTF8'
         LC_COLLATE = 'en_US.utf8'
         LC_CTYPE = 'en_US.utf8';

-- 2. Create the user (if it doesn't exist)
CREATE ROLE postgres WITH LOGIN PASSWORD 'postgres';

-- 3. Grant privileges on the database to the user
GRANT ALL PRIVILEGES ON DATABASE crm_bff TO postgres;

-- 4. Connect to the crm_bff database using psql
-- \c crm_bff
--
-- -- 5. Create the logistic schema
-- CREATE SCHEMA IF NOT EXISTS logistic AUTHORIZATION postgres;  -- postgres owns the schema
--
-- -- 6. Grant privileges on the schema to the user (Important!)
-- GRANT ALL PRIVILEGES ON SCHEMA logistic TO postgres;
