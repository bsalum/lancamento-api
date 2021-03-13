CREATE TABLE lancamento(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
    data_vencimento DATE NOT NULL,
    data_pagamento DATE NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(100),
    tipo VARCHAR(20) NOT NULL,
    codigo_categoria BIGINT(20) NOT NULL,
    codigo_pessoa BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
)ENGINE=InnoDB DEFAULT CHARSET= utf8;


INSERT INTO lancamento(descricao, data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa)  values ('Energia', '2020-01-03', '2020-01-03', 325.65, 'pagamento em dia', 'DESPESA', 1,1);
INSERT INTO lancamento(descricao, data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa)  values ('Internet', '2020-02-23', '2020-02-23', 200.15, null, 'DESPESA',3,2);
INSERT INTO lancamento(descricao, data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa)  values ('Boleto', '2019-12-03', '2019-12-03', 251.5, null, 'DESPESA',2,3);
INSERT INTO lancamento(descricao, data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa)  values ('Fornecedor', '2020-02-03', '2020-02-03', 300.25, 'teste', 'DESPESA',3,5);
INSERT INTO lancamento(descricao, data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa)  values ('Cartão', '2019-05-15', '2019-05-15', 1222.65, 'receita e vendas', 'RECEITA',4,6);
INSERT INTO lancamento(descricao, data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa)  values ('Mercado', '2019-05-15', '2019-05-15', 222.15, 'receita e vendas', 'RECEITA',5,8);