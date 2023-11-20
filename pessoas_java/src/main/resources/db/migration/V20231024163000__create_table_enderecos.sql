CREATE TABLE IF NOT EXISTS enderecos (
    id SERIAL NOT NULL,
    pais VARCHAR(40) NOT NULL CHECK (TRIM(pais) <> ''),
    cep VARCHAR(40) NOT NULL CHECK (TRIM(cep) <> ''),
    estado VARCHAR(40) NOT NULL CHECK (TRIM(estado) <> ''),
    cidade VARCHAR(40) NOT NULL CHECK (TRIM(cidade) <> ''),
    bairro VARCHAR(40) NOT NULL CHECK (TRIM(bairro) <> ''),
    logradouro VARCHAR(100) NOT NULL CHECK (TRIM(logradouro) <> ''),
    numero VARCHAR(10) CHECK (TRIM(numero) <> ''),
    complemento VARCHAR(250) CHECK (TRIM(complemento) <> ''),
    CONSTRAINT pk_enderecos PRIMARY KEY (id)
);

