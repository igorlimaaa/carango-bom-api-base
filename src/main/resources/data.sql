INSERT INTO TB_MARCA (ds_nome) VALUES ('Audi');
INSERT INTO TB_MARCA (ds_nome) VALUES ('For');
INSERT INTO TB_MARCA (ds_nome) VALUES ('HyndaI');


INSERT INTO VEICULO(ano, marca_id, modelo, preco, is_vendido) VALUES (2020, (select id_marca from TB_MARCA where ds_nome = 'HyndaI'), 'I30', 73000, true);