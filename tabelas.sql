-- Tabela de Usuários
CREATE TABLE usuarios (
    id VARCHAR(50) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Endereços
CREATE TABLE enderecos (
    id VARCHAR(50) PRIMARY KEY,
    usuario_id VARCHAR(50),
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(100),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Tabela de Drones
CREATE TABLE drones (
    id VARCHAR(50) PRIMARY KEY,
    modelo VARCHAR(50) NOT NULL,
    capacidade_carga DECIMAL(5,2) NOT NULL,
    bateria INT NOT NULL CHECK (bateria BETWEEN 0 AND 100),
    livre BOOLEAN DEFAULT TRUE,
    latitude DECIMAL(10,6),
    longitude DECIMAL(10,6)
);

-- Tabela de Pedidos
CREATE TABLE pedidos (
    id VARCHAR(50) PRIMARY KEY,
    usuario_id VARCHAR(50) NOT NULL,
    endereco_origem_id VARCHAR(50) NOT NULL,
    endereco_destino_id VARCHAR(50) NOT NULL,
    drone_id VARCHAR(50),
    peso DECIMAL(5,2) NOT NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_entrega TIMESTAMP,
    status VARCHAR(20) DEFAULT 'RECEBIDO',
    entregue BOOLEAN DEFAULT FALSE,
    custo DECIMAL(8,2),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (endereco_origem_id) REFERENCES enderecos(id),
    FOREIGN KEY (endereco_destino_id) REFERENCES enderecos(id),
    FOREIGN KEY (drone_id) REFERENCES drones(id)
);

-- Tabela de Histórico de Entregas
CREATE TABLE historico_entregas (
    id VARCHAR(50) PRIMARY KEY,
    drone_id VARCHAR(50) NOT NULL,
    pedido_id VARCHAR(50) NOT NULL,
    data_entrega TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (drone_id) REFERENCES drones(id),
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id)
);