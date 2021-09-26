CREATE TABLE IF NOT EXISTS fatura
(id_fatura INT AUTO_INCREMENT PRIMARY KEY,
conta_id INT,
mes VARCHAR(10),
ano VARCHAR(4),
valor_fatura DECIMAL,
status_fatura VARCHAR(100),
CONSTRAINT uc_fatura UNIQUE (conta_id , mes, ano)
);

INSERT IGNORE INTO fatura (conta_id, mes, ano, valor_fatura, status_fatura) VALUES (1500603806, 'setembro', '2021', 5000.00, 'fechada');
INSERT IGNORE INTO fatura (conta_id, mes, ano, valor_fatura, status_fatura) VALUES (1500603806, 'outubro', '2021', 3000.00, 'fechada');
INSERT IGNORE INTO fatura (conta_id, mes, ano, valor_fatura, status_fatura) VALUES (1500603806, 'novembro', '2021', 2000.00, 'fechada');
INSERT IGNORE INTO fatura (conta_id, mes, ano, valor_fatura, status_fatura) VALUES (1500603806, 'dezembro', '2021', 1000.00, 'fechada');
INSERT IGNORE INTO fatura (conta_id, mes, ano, valor_fatura, status_fatura) VALUES (1500603806, 'janeiro', '2022', 2000.00, 'em aberto');