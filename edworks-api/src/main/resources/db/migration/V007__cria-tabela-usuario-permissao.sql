CREATE TABLE usuario_permissao(
	usuario_id BIGINT NOT NULL,
	permissao_id BIGINT NOT NULL,
    
    PRIMARY KEY(usuario_id,permissao_id)
);

ALTER TABLE usuario_permissao ADD CONSTRAINT fk_usuario_id
FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE usuario_permissao ADD CONSTRAINT fk_permissao_id
FOREIGN KEY (permissao_id) REFERENCES permissao (id);