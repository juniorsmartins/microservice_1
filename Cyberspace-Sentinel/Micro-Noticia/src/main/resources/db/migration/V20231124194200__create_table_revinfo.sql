CREATE SEQUENCE revinfo_seq
    START WITH 1 INCREMENT BY 50;;

CREATE TABLE IF NOT EXISTS revinfo(
    id INTEGER NOT NULL,
    timestamp BIGINT NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE revinfo
  ALTER COLUMN id
    SET DEFAULT nextval('revinfo_seq');

