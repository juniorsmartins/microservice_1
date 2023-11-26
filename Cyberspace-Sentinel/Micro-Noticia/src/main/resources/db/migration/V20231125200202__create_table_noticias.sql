create table if not exists noticias(
    id SERIAL NOT NULL,
    chapeu VARCHAR(20) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    linha_fina VARCHAR(150) NOT NULL,
    lide TEXT(500) NOT NULL,
    corpo TEXT NOT NULL,
    nome_autor VARCHAR(50),
    fonte VARCHAR(250),
    data_hora_criacao TIMESTAMP NOT NULL,
    data_hora_edicao TIMESTAMP,
    CONSTRAINT pk_textos PRIMARY KEY(id)
);

