CREATE TABLE historico (
	id BIGINT NOT NULL AUTO_INCREMENT,
	municipio VARCHAR(255) NOT NULL,
    data_coleta DATETIME NOT NULL,
    valor_venda DECIMAL(10,2),
    valor_compra DECIMAL(10,2) NOT NULL,
    unidade_medida VARCHAR(10) NOT NULL,
    produto_id BIGINT NOT NULL,
    regiao_estado_id BIGINT NOT NULL,
    empresa_id BIGINT NOT NULL,
	PRIMARY KEY(id)
);

ALTER TABLE historico ADD CONSTRAINT fk_historico_produto
FOREIGN KEY (produto_id) REFERENCES produto (id); 

ALTER TABLE historico ADD CONSTRAINT fk_historico_regiao_estado
FOREIGN KEY (regiao_estado_id) REFERENCES regiao_estado (id); 

ALTER TABLE historico ADD CONSTRAINT fk_historico_empresa
FOREIGN KEY (empresa_id) REFERENCES empresa (id);