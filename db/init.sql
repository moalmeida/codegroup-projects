
CREATE TABLE pessoa
(
    id BIGSERIAL NOT NULL,
    nome CHARACTER VARYING(100) NOT NULL,
    datanascimento DATE,
    cpf CHARACTER VARYING(14),
    funcionario BOOLEAN,
    gerente BOOLEAN,
    CONSTRAINT pk_pessoa PRIMARY KEY (id)
);

CREATE TABLE projeto
(
    id BIGSERIAL NOT NULL,
    nome VARCHAR(200) NOT NULL,
    data_inicio DATE,
    data_previsao_fim DATE,
    data_fim DATE,
    descricao VARCHAR(5000),
    status VARCHAR(45),
    orcamento FLOAT,
    risco VARCHAR(45),
    idgerente BIGINT NOT NULL,
    CONSTRAINT pk_projeto PRIMARY KEY (id),
    CONSTRAINT fk_gerente FOREIGN KEY (idgerente)
        REFERENCES pessoa (id)
        MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- CREATE TABLE membros
-- (
--     idprojeto BIGINT NOT NULL,
--     idpessoa BIGINT NOT NULL,
--     CONSTRAINT pk_membros_projeto PRIMARY KEY (idprojeto),
--     CONSTRAINT fk_membros_pessoa FOREIGN KEY (idprojeto)
--         REFERENCES projeto (id)
--         MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION,
--     CONSTRAINT fk_pessoa FOREIGN KEY (idpessoa)
--         REFERENCES pessoa (id)
--         MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION
-- );


CREATE TABLE membros
( idprojeto bigint NOT NULL,
  idpessoa bigint NOT NULL,
  CONSTRAINT pk_membros PRIMARY KEY (idprojeto, idpessoa), -- composite primary key
  CONSTRAINT fk_membros_projeto FOREIGN KEY (idprojeto) --foreign key to project
      REFERENCES projeto (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_membros_pessoa FOREIGN KEY (idpessoa) --foreign key to person
      REFERENCES pessoa (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


INSERT INTO pessoa (
    nome,
    datanascimento,
    cpf,
    funcionario,
    gerente
)
VALUES
    ('Marisa Monte', '1980-05-10', '123.456.789-99', FALSE, TRUE),
    ('Kelly Key', '1980-05-10', '234.567.890-00', FALSE, TRUE),
    ('Maria Betânia', '1975-07-20', '345.678.901-11', TRUE, FALSE),
    ('Mc Marcinho', '1990-02-15', '456.789.012-22', TRUE, FALSE),
    ('Anitta', '1990-02-15', '567.890.123-33', TRUE, FALSE),
    ('Aline Barros', '1990-02-15', '678.901.234-44', TRUE, FALSE),
    ('Fabio Junior', '1990-02-15', '789.012.345-55', TRUE, FALSE),
    ('Ludimila', '1990-02-15', '890.123.456-66', TRUE, FALSE),
    ('Compadre Washington', '1990-02-15', '901.234.567-77', TRUE, FALSE),
    ('Fagner', '1990-02-15', '012.345.678-88', TRUE, FALSE);



INSERT INTO projeto (
    nome,
    data_inicio,
    data_previsao_fim,
    data_fim,
    descricao,
    status,
    orcamento,
    risco,
    idgerente
)
VALUES
    ('site responsivo pousadas', '2023-07-01', '2023-10-01', null, 'projeto para melhoria no site.', 'em andamento', 80000, 'médio risco', 1),
    ('site responsivo hoteis', '2023-07-01', '2023-10-01', '2024-01-01', 'projeto para melhoria no site.', 'encerrado', 60000, 'médio risco', 1),
    ('landing page XPTO', '2023-09-10', '2024-01-10', null, 'projeto para criação de página inicial para o parceiro XPTO.', 'planejado', 10000, 'alto risco', 2),
    ('plugin de pagamento XPTO', '2024-02-01', '2024-04-01', null, 'projeto para integração dos modulos de pagamento do parceiro XPTO.', 'em andamento', 5000, 'alto risco', 2);


INSERT INTO membros (
    idprojeto,
    idpessoa
)
VALUES
    (1, 3),
    (1, 5),
    (2, 4),
    (2, 6),
    (3, 5),
    (3, 7),
    (3, 4),
    (4, 6);
