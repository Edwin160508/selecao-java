CREATE TABLE regiao_estado (
	id BIGINT NOT NULL AUTO_INCREMENT,
	regiao VARCHAR(2) NOT NULL,
    sigla_estado VARCHAR(2) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    
	PRIMARY KEY(id)
);