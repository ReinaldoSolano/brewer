
CREATE TABLE estilo (
    bw_codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    bw_nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cerveja (
    bw_codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    bw_sku VARCHAR(50) NOT NULL,
    bw_nome VARCHAR(80) NOT NULL,
    bw_descricao TEXT NOT NULL,
    bw_valor DECIMAL(10, 2) NOT NULL,
    bw_teor_alcoolico DECIMAL(10, 2) NOT NULL,
    bw_comissao DECIMAL(10, 2) NOT NULL,
    bw_sabor VARCHAR(50) NOT NULL,
    bw_origem VARCHAR(50) NOT NULL,
    bw_codigo_estilo BIGINT(20) NOT NULL,
    FOREIGN KEY (bw_codigo_estilo) REFERENCES estilo(bw_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO estilo VALUES (0, 'Amber Lager');
INSERT INTO estilo VALUES (0, 'Dark Lager');
INSERT INTO estilo VALUES (0, 'Pale Lager');
INSERT INTO estilo VALUES (0, 'Pilsner');