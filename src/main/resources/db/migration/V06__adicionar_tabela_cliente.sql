CREATE TABLE cliente (
    bw_codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    bw_nome VARCHAR(80) NOT NULL,
    bw_tipo_pessoa VARCHAR(15) NOT NULL,
    bw_cpf_cnpj VARCHAR(30),
    bw_telefone VARCHAR(20),
    bw_email VARCHAR(50) NOT NULL,
    bw_logradouro VARCHAR(50),
    bw_numero VARCHAR(15),
    bw_complemento VARCHAR(20),
    bw_cep VARCHAR(15),
    bw_codigo_cidade BIGINT(20),
    FOREIGN KEY (bw_codigo_cidade) REFERENCES cidade(bw_codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;