CREATE TABLE respostas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensagem VARCHAR(255),
    data TIMESTAMP,
    resolucao BOOLEAN,
    topico_id BIGINT,
    FOREIGN KEY (topico_id) REFERENCES topicos(id)
);