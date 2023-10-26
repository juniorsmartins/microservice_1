CREATE TABLE IF NOT EXISTS user_permissao (
    id_user SERIAL NOT NULL,
    id_permissao SERIAL NOT NULL,
    PRIMARY KEY(id_user, id_permissao),
    CONSTRAINT fk_user_permissao_users FOREIGN KEY (id_user) REFERENCES users(id),
    CONSTRAINT fk_user_permissao_permissoes FOREIGN KEY (id_permissao) permissoes(id)
)

