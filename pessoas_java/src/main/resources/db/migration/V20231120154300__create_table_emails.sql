CREATE TABLE IF NOT EXISTS emails (
    id SERIAL NOT NULL,
    email VARCHAR(150) NOT NULL CHECK (TRIM(email) <> '' AND email !~ '^\\s*$'),
    pessoa_id INTEGER NOT NULL,
    CONSTRAINT pk_emails PRIMARY KEY (id),
    CONSTRAINT fk_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES pessoas(id)
);

