-- liquibase formatted sql

-- changeSet see1rg:1
CREATE TABLE account (
                         id BIGINT AUTO_INCREMENT,
                         name VARCHAR(35) NOT NULL,
                         pin VARCHAR(65) NOT NULL,
                         balance NUMERIC
);

CREATE TABLE log_entry (
                           id BIGINT AUTO_INCREMENT,
                           timestamp TIMESTAMP,
                           log_level VARCHAR(255),
                           message TEXT
);

