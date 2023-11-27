create table if not exists rev_noticias_aud(
    id BIGINT NOT NULL,
    rev BIGINT NOT NULL,
    revtype SMALLINT NOT NULL,
    chapeu VARCHAR(20) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    linha_fina VARCHAR(150) NOT NULL,
    lide TEXT(500) NOT NULL,
    corpo TEXT(3000) NOT NULL,
    nome_autor VARCHAR(50),
    fonte VARCHAR(250),
    data_hora_criacao TIMESTAMP NOT NULL,
    data_hora_edicao TIMESTAMP,
    version INTEGER,
    CONSTRAINT pk_noticias PRIMARY KEY(id, rev),
    CONSTRAINT fk_noticias_revinfo FOREIGN KEY (rev) REFERENCES revinfo(id)
);

