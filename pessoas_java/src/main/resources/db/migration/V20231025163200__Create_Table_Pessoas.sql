CREATE TABLE IF NOT EXISTS pessoas (
  id SERIAL NOT NULL,
  chave uuid UNIQUE NOT NULL,
  nome VARCHAR(30) NOT NULL,
  sobrenome VARCHAR(50) NOT NULL,
  cpf VARCHAR(11) UNIQUE NOT NULL,
  data_nascimento VARCHAR(10) NOT NULL,
  sexo VARCHAR(10) NOT NULL,
  genero VARCHAR(10) NOT NULL,
  nivel_educacional VARCHAR(40) NOT NULL,
  nacionalidade VARCHAR(40) NOT NULL,
  PRIMARY KEY (id)
);

