CREATE TABLE IF NOT EXISTS mesas (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    numero_mesa INT UNIQUE NOT NULL,
    capacidade INT NOT NULL,
    status VARCHAR(255) NOT NULL CHECK (status IN ('DISPONIVEL', 'RESERVADA', 'INDISPONIVEL'))
);

INSERT INTO mesas (numero_mesa, capacidade, status)
SELECT
  serie AS numero_mesa,
  FLOOR(RANDOM() * 7 + 2) AS capacidade,
  'DISPONIVEL' AS status
FROM generate_series(1, 20) AS serie;