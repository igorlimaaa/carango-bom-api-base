--Inserts para Marcas
INSERT INTO TB_MARCA (ds_nome) VALUES ('Audi');
INSERT INTO TB_MARCA (ds_nome) VALUES ('For');
INSERT INTO TB_MARCA (ds_nome) VALUES ('HyndaI');

-- Insert para Veículos
INSERT INTO VEICULO(ano, marca_id, modelo, preco, is_vendido) VALUES (2020, 3, 'I30', 73000, false);
INSERT INTO VEICULO(ano, marca_id, modelo, preco, is_vendido) VALUES (2018, 2, 'A3', 52000, true);

--Insert para Usuários
INSERT INTO TB_USUARIO (ds_email, ds_nome, ds_senha) VALUES ('almeidalima.igor@gmail.com', 'Igor de Almeida Lima', '$2a$10$Gn03nA57itFW1RqrQCOZPON5b2L296pX0HBcVwpuHiyyD7qBpph/C');
INSERT INTO TB_USUARIO (ds_email, ds_nome, ds_senha) VALUES ('sofia@gmail.com', 'Sofia Lima', '$2a$10$Gn03nA57itFW1RqrQCOZPON5b2L296pX0HBcVwpuHiyyD7qBpph/C');
