-- liquibase formatted sql

-- changeSet see1rg:1
CREATE TABLE account (
                         id INT AUTO_INCREMENT,
                         name VARCHAR(35) NOT NULL,
                         pin VARCHAR(4) NOT NULL,
                         balance BIGINT
);