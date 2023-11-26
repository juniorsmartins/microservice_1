create table if not exists textos(
    id SERIAL NOT NULL,
    chapeu VARCHAR(20) NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    linha_fina VARCHAR(150) NOT NULL,
    lide TEXT NOT NULL,
    corpo TEXT NOT NULL,
    nome_autor VARCHAR(50),
    fonte VARCHAR(250),
    CONSTRAINT pk_textos PRIMARY KEY(id)
);

