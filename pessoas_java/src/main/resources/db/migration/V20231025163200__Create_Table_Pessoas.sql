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
  usuario_id INT UNIQUE,

  data_criacao TIMESTAMP,
  criado_por VARCHAR(100),
  data_modificacao TIMESTAMP,
  modificado_por VARCHAR(100),

  PRIMARY KEY (id),
  CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

