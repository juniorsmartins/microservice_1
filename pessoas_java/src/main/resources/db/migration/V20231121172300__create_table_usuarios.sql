CREATE TABLE IF NOT EXISTS usuarios(
    id SERIAL NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    pessoa_id INTEGER,
    CONSTRAINT uk_usuarios UNIQUE (username),
    CONSTRAINT pk_usuarios PRIMARY KEY (id),
    CONSTRAINT fk_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES pessoas(id)
);

