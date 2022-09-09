create table categoria (id  serial not null, icone TEXT, nome varchar(100) not null, primary key (id));
create table cidade (id  serial not null, nome varchar(30) not null, estado_id int4 not null, primary key (id));
create table endereco (id  serial not null, bairro varchar(255) not null, cep numeric(19, 2) not null, complemento varchar(255), numero int4 not null, cidade_id int4 not null, usuario_id int4 not null, primary key (id));
create table estado (id  serial not null, nome varchar(20) not null, sigla varchar(2) not null, primary key (id));
create table imagem (id  serial not null, imagem_url TEXT, produto_id int4, primary key (id));
create table item_pedido (desconto float4, preco_item float4 not null, quantidade int4 not null, pedido_id int4 not null, produto_id int4 not null, primary key (pedido_id, produto_id));
create table pagamento (pedido_id int4 not null, status_pagamento int4 not null, primary key (pedido_id));
create table pagamento_dinheiro (data timestamp, nome_pagador varchar(255), relatorio varchar(255), troco float4 not null, pedido_id int4 not null, primary key (pedido_id));
create table pagamento_pix (chave_pix varchar(255), data date, nome_pagador varchar(255), relatorio varchar(255), pedido_id int4 not null, primary key (pedido_id));
create table papel (id  serial not null, autoridade varchar(15) not null, primary key (id));
create table pedido (id  serial not null, data timestamp not null, cliente_id int4 not null, endereco_entrega_id int4 not null, primary key (id));
create table produto (id  serial not null, nome varchar(100) not null, preco float4 not null, primary key (id));
create table produto_categoria (produto_id int4 not null, categoria_id int4 not null);
create table telefone (usuario_id int4 not null, telefone varchar(15) not null, primary key (usuario_id, telefone));
create table usuario (id  serial not null, cpf varchar(11), email varchar(60) not null, nome varchar(60) not null, senha varchar(255) not null, primary key (id));
create table usuario_papeis (usuario_id int4 not null, papel_id int4 not null);
alter table cidade add constraint UK_lwg97fq9vkwb5vlqo59krrxoa unique (nome);
alter table estado add constraint UK_gfot2y0318rs8hc74ppp0n87p unique (nome);
alter table estado add constraint UK_pw4erk59cah8l9fqtecanxfu6 unique (sigla);
alter table papel add constraint UK_nl38goqnnkn66g6h7uisusqde unique (autoridade);
alter table usuario add constraint UK_692bsnqxa8m9fmx7m1yc6hsui unique (cpf);
alter table usuario add constraint UK_5171l57faosmj8myawaucatdw unique (email);
alter table cidade add constraint CIDADE_ESTADO_FK foreign key (estado_id) references estado;
alter table endereco add constraint ENDERECO_CIDADE_FK foreign key (cidade_id) references cidade;
alter table endereco add constraint ENDERECO_USUARIO_ID foreign key (usuario_id) references usuario;
alter table imagem add constraint IMAGEM_PRODUTO_FK foreign key (produto_id) references produto;
alter table item_pedido add constraint ItemPedido_Pedido_FK foreign key (pedido_id) references pedido;
alter table item_pedido add constraint ItemPedido_Produto_FK foreign key (produto_id) references produto;
alter table pagamento add constraint FKthad9tkw4188hb3qo1lm5ueb0 foreign key (pedido_id) references pedido;
alter table pagamento_dinheiro add constraint FKb5ma55icc6n3rc2h9m7tg628e foreign key (pedido_id) references pagamento;
alter table pagamento_pix add constraint FKmiypj5w2h1nwwskuvbvvs5plp foreign key (pedido_id) references pagamento;
alter table pedido add constraint PEDIDO_CLIENTE_FK foreign key (cliente_id) references usuario;
alter table pedido add constraint PEDIDO_ENDERECO_FK foreign key (endereco_entrega_id) references endereco;
alter table produto_categoria add constraint FKq3g33tp7xk2juh53fbw6y4y57 foreign key (categoria_id) references categoria;
alter table produto_categoria add constraint FK1c0y58d3n6x3m6euv2j3h64vt foreign key (produto_id) references produto;
alter table telefone add constraint FK92q33nmw94rylnpis5mgcy3v foreign key (usuario_id) references usuario;
alter table usuario_papeis add constraint FKrnooo8ecu3rrooc5uvy3bepni foreign key (papel_id) references papel;
alter table usuario_papeis add constraint FKd7vmnhifmy0sg4clpioybj1jo foreign key (usuario_id) references usuario;

INSERT INTO papel (autoridade) VALUES ('ROLE_ADMIN');
INSERT INTO papel (autoridade) VALUES ('ROLE_CLIENTE');
INSERT INTO papel (autoridade) VALUES ('ROLE_VENDEDOR');
INSERT INTO papel (autoridade) VALUES ('ROLE_FORNECEDOR');
INSERT INTO usuario (nome, email, senha) VALUES ('douglas', 'douglas@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO usuario (nome, email, senha) VALUES ('gabriel', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO usuario_papeis (usuario_id, papel_id) VALUES (1, 1);
INSERT INTO usuario_papeis (usuario_id, papel_id) VALUES (2, 2);
INSERT INTO usuario_papeis (usuario_id, papel_id) VALUES (2, 3);
INSERT INTO estado (sigla, nome) VALUES ('DF', 'Distrito Federal');
INSERT INTO cidade (nome, estado_id) VALUES ('Brasília', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Recanto das Emas', 1);
INSERT INTO endereco (bairro, cep, numero, cidade_id, usuario_id) VALUES ('Recanto das Emas', 72600400, 12, 1, 1);
INSERT INTO categoria (nome, icone) VALUES ('Informática', 'computer');
INSERT INTO categoria (nome, icone) VALUES ('Roupas', 'checkroom');
INSERT INTO categoria (nome, icone) VALUES ('Acessórios', 'headphones_battery');
INSERT INTO produto (nome, preco) VALUES ('Xiaomi Redmi 11s', 1475.00);
INSERT INTO produto (nome, preco) VALUES ('Xiaomi POCO M3', 1350.00);
INSERT INTO produto (nome, preco) VALUES ('Pulseira de ouro', 10500.00);
INSERT INTO produto (nome, preco) VALUES ('Brincos', 10500.00);
INSERT INTO produto (nome, preco) VALUES ('Trevesseiro', 50.00);
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (1, 1);
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (2, 1);
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (3, 2);
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (4, 2);
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (5, 3);