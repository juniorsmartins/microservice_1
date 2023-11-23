CREATE TABLE IF NOT EXISTS usuarios(
    id SERIAL NOT NULL,
    username VARCHAR(50) NOT NULL CHECK (LENGTH(username) >= 5),
    password VARCHAR(50) NOT NULL CHECK (LENGTH(username) >= 5),
    pessoa_id INTEGER,
    CONSTRAINT uk_usuarios UNIQUE (username),
    CONSTRAINT pk_usuarios PRIMARY KEY (id),
    CONSTRAINT fk_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES pessoas(id)
);

