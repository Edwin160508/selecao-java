DROP TABLE usuario_permissao;
DROP TABLE usuario;

CREATE TABLE usuario (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	
	PRIMARY KEY(id)
);

CREATE TABLE usuario_permissao(
	usuario_id BIGINT NOT NULL,
	permissao_id BIGINT NOT NULL,
    
    PRIMARY KEY(usuario_id,permissao_id)
);

ALTER TABLE usuario_permissao ADD CONSTRAINT fk_usuario_id
FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE usuario_permissao ADD CONSTRAINT fk_permissao_id
FOREIGN KEY (permissao_id) REFERENCES permissao (id);

INSERT INTO usuario VALUES
(1,'admin','edwin.limaa@gmail.com','$2a$10$9LGqLlrPJQQmuXoA7l9wtOZdy115CnJA2QV96.CRKe.1FRVwSd8ny');

INSERT INTO usuario_permissao VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6);