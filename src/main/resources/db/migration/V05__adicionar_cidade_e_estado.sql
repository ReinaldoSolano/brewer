CREATE TABLE estado (
    bw_codigo BIGINT(20) PRIMARY KEY,
    bw_nome VARCHAR(50) NOT NULL,
    bw_sigla VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cidade (
    bw_codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    bw_nome VARCHAR(50) NOT NULL,
    bw_codigo_estado BIGINT(20) NOT NULL,
    FOREIGN KEY (bw_codigo_estado) REFERENCES estado(bw_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO estado (bw_codigo, bw_nome, bw_sigla) VALUES (1,'Acre', 'AC');
INSERT INTO estado (bw_codigo, bw_nome, bw_sigla) VALUES (2,'Bahia', 'BA');
INSERT INTO estado (bw_codigo, bw_nome, bw_sigla) VALUES (3,'Goiás', 'GO');
INSERT INTO estado (bw_codigo, bw_nome, bw_sigla) VALUES (4,'Minas Gerais', 'MG');
INSERT INTO estado (bw_codigo, bw_nome, bw_sigla) VALUES (5,'Santa Catarina', 'SC');
INSERT INTO estado (bw_codigo, bw_nome, bw_sigla) VALUES (6,'São Paulo', 'SP');


INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Rio Branco', 1);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Cruzeiro do Sul', 1);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Salvador', 2);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Porto Seguro', 2);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Santana', 2);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Goiânia', 3);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Itumbiara', 3);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Novo Brasil', 3);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Belo Horizonte', 4);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Uberlândia', 4);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Montes Claros', 4);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Florianópolis', 5);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Criciúma', 5);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Camboriú', 5);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Lages', 5);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('São Paulo', 6);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Ribeirão Preto', 6);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Campinas', 6);
INSERT INTO cidade (bw_nome, bw_codigo_estado) VALUES ('Santos', 6);