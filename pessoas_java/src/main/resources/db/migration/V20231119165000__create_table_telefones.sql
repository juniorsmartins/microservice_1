CREATE TABLE IF NOT EXISTS telefones (
    id SERIAL NOT NULL,
    numero VARCHAR(11) NOT NULL CHECK (TRIM(numero) <> '' AND numero !~ '^\\s*$'),
    pessoa_id INTEGER NOT NULL,
    CONSTRAINT pk_telefones PRIMARY KEY (id),
    CONSTRAINT fk_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES pessoas(id)
);

