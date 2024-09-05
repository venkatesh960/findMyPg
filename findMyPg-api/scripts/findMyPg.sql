drop scheme findmypg

create schema findmypg

-- Drop table if it exists
-- DROP TABLE IF EXISTS building;

CREATE TABLE findmypg.building (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by VARCHAR(255),
    createdtimestamp VARCHAR(255),
    updatetimestamp VARCHAR(255),
    building_image LONGBLOB,
    location VARCHAR(255),
    num_of_floors INT,
    pg_name VARCHAR(255),
    pg_type VARCHAR(255),
    owner_id BIGINT
);

-- Drop table if it exists
-- DROP TABLE IF EXISTS employee;

CREATE TABLE findmypg.employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by VARCHAR(255),
    createdtimestamp VARCHAR(255),
    updatetimestamp VARCHAR(255),
    emp_email_id VARCHAR(255),
    emp_firstname VARCHAR(255),
    emp_lastname VARCHAR(255),
    emp_middlename VARCHAR(255),
    emp_mobile_num VARCHAR(255),
    emp_username VARCHAR(255),
    owner_id BIGINT
);

-- Drop table if it exists
-- DROP TABLE IF EXISTS floor;

CREATE TABLE findmypg.floor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by VARCHAR(255),
    createdtimestamp VARCHAR(255),
    updatetimestamp VARCHAR(255),
    floor_number INT,
    number_of_rooms INT,
    building_id BIGINT
);

-- Drop table if it exists
-- DROP TABLE IF EXISTS owner;

CREATE TABLE findmypg.owner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by VARCHAR(255),
    createdtimestamp VARCHAR(255),
    updatetimestamp VARCHAR(255),
    email_id VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    middle_name VARCHAR(255),
    mobile_num VARCHAR(255),
    password VARCHAR(255),
    user_name VARCHAR(255),
    user_type VARCHAR(255)
);

-- Drop table if it exists
-- DROP TABLE IF EXISTS payments;

CREATE TABLE findmypg.payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by VARCHAR(255),
    createdtimestamp VARCHAR(255),
    updatetimestamp VARCHAR(255),
    amount INT,
    currency VARCHAR(255),
    mobile_number VARCHAR(255),
    order_id VARCHAR(255),
    payment_date VARCHAR(255),
    receipt VARCHAR(255),
    status VARCHAR(255),
    user_id BIGINT
);

-- Drop table if it exists
-- DROP TABLE IF EXISTS room;

CREATE TABLE findmypg.room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by VARCHAR(255),
    createdtimestamp VARCHAR(255),
    updatetimestamp VARCHAR(255),
    avilable_room INT,
    building_id BIGINT,
    rates INT,
    room_number INT,
    share_type INT,
    status VARCHAR(255),
    floor_id BIGINT
);

-- Drop table if it exists
-- DROP TABLE IF EXISTS student;

CREATE TABLE findmypg.student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by VARCHAR(255),
    createdtimestamp VARCHAR(255),
    updatetimestamp VARCHAR(255),
    id_number BIGINT,
    id_type VARCHAR(255),
    joining_date DATE,
    email_id VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    mobile_number VARCHAR(255),
    middle_name VARCHAR(255),
    owner_id BIGINT,
    student_room_details_id BIGINT
);

-- Drop table if it exists
-- DROP TABLE IF EXISTS student_room_details;

CREATE TABLE findmypg.student_room_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_by VARCHAR(255),
    createdtimestamp VARCHAR(255),
    updatetimestamp VARCHAR(255),
    building_id BIGINT,
    floor_id BIGINT,
    room_id BIGINT,
    status VARCHAR(255)
);


