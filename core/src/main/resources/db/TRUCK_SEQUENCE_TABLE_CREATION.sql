CREATE SEQUENCE logistic.TRUCK_SEQ
    INCREMENT BY 1
    START WITH 1;

-- Correct CREATE TABLE statement (if BaseEntity and Truck were fixed)
CREATE TABLE logistic.TRUCK (
                                id BIGINT PRIMARY KEY DEFAULT nextval('TRUCK_SEQ'),
                                created_by_id BIGINT,
                                created_by_table VARCHAR(255),
                                modified_by_id BIGINT,
                                modified_by_table VARCHAR(255),
                                cdt TIMESTAMP,
                                mdt TIMESTAMP,
                                rdt TIMESTAMP,
                                registration_country VARCHAR(255) NOT NULL,
                                volume_m3 DOUBLE PRECISION NOT NULL,
                                departure_warehouse VARCHAR(255) NOT NULL,
                                delivery_warehouse VARCHAR(255) NOT NULL,
                                driver_phone VARCHAR(255) NOT NULL,
                                additional_information VARCHAR(255)
);