CREATE TABLE vacancies (
    id BIGSERIAL PRIMARY KEY,
    vacancy_type INT NOT NULL,
    priority_type INT NOT NULL,
    parking_id BIGINT NOT NULL,
    CONSTRAINT fk_parking FOREIGN KEY (parking_id) REFERENCES parkings(id)
);
