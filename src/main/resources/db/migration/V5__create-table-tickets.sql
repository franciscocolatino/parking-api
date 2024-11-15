CREATE TABLE tickets (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    code VARCHAR(50) NOT NULL,
    payment_total DECIMAL(10, 2) NOT NULL,
    date_start TIMESTAMP NOT NULL,
    date_end TIMESTAMP NOT NULL,
    priority_type INT NOT NULL,
    vacancy_id TEXT NOT NULL,
    vehicle_id TEXT NOT NULL,
    user_id TEXT NOT NULL,
    CONSTRAINT fk_vacancy FOREIGN KEY (vacancy_id) REFERENCES vacancies(id),
    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT unique_code UNIQUE (code)
);
