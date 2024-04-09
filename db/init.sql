
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

CREATE TABLE membros
(
    idprojeto BIGINT NOT NULL,
    idpessoa BIGINT NOT NULL,
    CONSTRAINT pk_membros_projeto PRIMARY KEY (idprojeto),
    CONSTRAINT fk_membros_pessoa FOREIGN KEY (idprojeto)
        REFERENCES projeto (id)
        MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_pessoa FOREIGN KEY (idpessoa)
        REFERENCES pessoa (id)
        MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

