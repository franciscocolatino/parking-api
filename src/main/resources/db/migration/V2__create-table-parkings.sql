CREATE TABLE parkings (
     id TEXT PRIMARY KEY UNIQUE NOT NULL,
     name VARCHAR(100) NOT NULL,
     address VARCHAR(255) NOT NULL,
     total_capacity INT NOT NULL,
     vacancies_available INT NOT NULL
);
