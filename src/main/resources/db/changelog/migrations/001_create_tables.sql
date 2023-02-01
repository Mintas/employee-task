--liquibase formatted sql

--changeset dkovalev:001.1
--comment create employee table
CREATE TABLE employee
(
    employee_id           INTEGER NOT NULL PRIMARY KEY,
    supervisor_id         INTEGER NOT NULL,
    full_name             VARCHAR(255) NOT NULL
);