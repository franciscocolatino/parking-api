CREATE TABLE vacancies (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    vacancy_type INT NOT NULL,
    priority_type INT NOT NULL,
    parking_id TEXT NOT NULL,
    CONSTRAINT fk_parking FOREIGN KEY (parking_id) REFERENCES parkings(id)
);
