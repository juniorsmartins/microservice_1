CREATE TABLE IF NOT EXISTS pessoas (
  id SERIAL NOT NULL,
  chave uuid NOT NULL UNIQUE,
  nome varchar(30) NOT NULL,
  sobrenome varchar(50) NOT NULL,
  cpf varchar(11) NOT NULL UNIQUE,
  data_nascimento varchar(10) NOT NULL,
  sexo varchar(10) NOT NULL,
  genero varchar(10) NOT NULL,
  nivel_educacional varchar(40) NOT NULL,
  nacionalidade varchar(40) NOT NULL,
  PRIMARY KEY (id)
);

