CREATE TABLE parking (
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     address VARCHAR(255) NOT NULL,
     total_capacity INT NOT NULL,
     vacancies_available INT NOT NULL
);
