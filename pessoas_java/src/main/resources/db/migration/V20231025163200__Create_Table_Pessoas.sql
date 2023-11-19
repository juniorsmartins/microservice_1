CREATE TABLE IF NOT EXISTS pessoas (
  id SERIAL NOT NULL,
  chave uuid UNIQUE NOT NULL,
  nome VARCHAR(30) NOT NULL CHECK (TRIM(nome) <> ''),
  sobrenome VARCHAR(50) NOT NULL CHECK (TRIM(sobrenome) <> ''),
  cpf VARCHAR(11) UNIQUE NOT NULL CHECK (TRIM(cpf) <> ''),
  data_nascimento DATE NOT NULL,
  sexo VARCHAR(10) NOT NULL,
  genero VARCHAR(10),
  nivel_educacional VARCHAR(40) NOT NULL,
  nacionalidade VARCHAR(40) NOT NULL CHECK (TRIM(nacionalidade) <> ''),
  estado_civil VARCHAR(20) NOT NULL,
  endereco_id INTEGER,
  CONSTRAINT pk_pessoas PRIMARY KEY (id),
  CONSTRAINT fk_endereco_id FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);

