CREATE DATABASE IF NOT EXISTS ManengerDB;

USE ManengerDB;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100),
    email VARCHAR(100),
    usuario VARCHAR(20) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(11)
);

CREATE TABLE IF NOT EXISTS cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(100),
    cnpj VARCHAR(14) UNIQUE,
    representante VARCHAR(100),
    telefone VARCHAR(11)
);

CREATE TABLE IF NOT EXISTS carro (
    id_carro INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    ano INT NOT NULL,
    cor VARCHAR(20),
    valor FLOAT
);

CREATE TABLE IF NOT EXISTS pedido (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_cliente INT NOT NULL,
    valor_total DECIMAL(10, 2),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

CREATE TABLE IF NOT EXISTS pedido_carro (
    id_pedido INT NOT NULL,
    id_carro INT NOT NULL,
    PRIMARY KEY (id_pedido, id_carro),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_carro) REFERENCES carro(id_carro)
);
