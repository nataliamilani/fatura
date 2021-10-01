CREATE TABLE IF NOT EXISTS fatura
(id_fatura INT AUTO_INCREMENT PRIMARY KEY,
conta_id INT,
cliente_id INT,
mes VARCHAR(10),
ano VARCHAR(4),
valor_fatura DECIMAL,
status_fatura VARCHAR(100),
status_pagamento VARCHAR(100),
CONSTRAINT uc_fatura UNIQUE (conta_id, mes, ano)
);

INSERT IGNORE INTO fatura (conta_id, cliente_id, mes, ano, valor_fatura, status_fatura, status_pagamento) VALUES (1500603806, 1, 'setembro', '2021', 0, 'Paga', 'Pagamento Integral');
INSERT IGNORE INTO fatura (conta_id, cliente_id, mes, ano, valor_fatura, status_fatura, status_pagamento) VALUES (1500603806, 1, 'outubro', '2021', 3000.00, 'Fechada', 'Pagamento pendente');
INSERT IGNORE INTO fatura (conta_id, cliente_id, mes, ano, valor_fatura, status_fatura, status_pagamento) VALUES (1500603806, 1, 'novembro', '2021', 2000.00, 'Em aberto', 'Pagamento pendente');
INSERT IGNORE INTO fatura (conta_id, cliente_id, mes, ano, valor_fatura, status_fatura, status_pagamento) VALUES (1500603806, 1, 'dezembro', '2021', 1000.00, 'Em aberto', 'Pagamento pendente');
INSERT IGNORE INTO fatura (conta_id, cliente_id, mes, ano, valor_fatura, status_fatura, status_pagamento) VALUES (1500603806, 1, 'janeiro', '2022', 2000.00, 'Em aberto', 'Pagamento pendente');