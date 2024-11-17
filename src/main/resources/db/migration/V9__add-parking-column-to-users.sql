ALTER TABLE users ADD COLUMN parking_id TEXT;

ALTER TABLE users
    ADD CONSTRAINT fk_parking
        FOREIGN KEY (parking_id) REFERENCES parkings(id)
            ON DELETE SET NULL;
