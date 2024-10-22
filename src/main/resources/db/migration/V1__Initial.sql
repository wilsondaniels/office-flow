-- Criação da tabela Funcionario
CREATE TABLE Funcionario (
    UUID UUID PRIMARY KEY,                -- Chave primária do tipo UUID
    nome VARCHAR(255) NOT NULL,           -- Nome obrigatório
    endereco VARCHAR(255) NOT NULL,       -- Endereço obrigatório
    bairro VARCHAR(255),                  -- Bairro opcional
    cep VARCHAR(255),                     -- CEP opcional
    telefone VARCHAR(10),                 -- Telefone opcional
    salario DECIMAL NOT NULL,             -- Salário obrigatório
    data_contrato TIMESTAMP NOT NULL,     -- Data de contrato obrigatória
    funcao VARCHAR(255) NOT NULL          -- Função obrigatória
);

-- Criação da tabela Departamento
CREATE TABLE Departamento (
    UUID UUID PRIMARY KEY,                -- Chave primária do tipo UUID
    departamento VARCHAR(255) NOT NULL,   -- Nome do departamento obrigatório
    qtdeFuncionarios INTEGER NOT NULL     -- Quantidade de funcionários obrigatória
);

-- Criação da tabela Funcionario x Departamento (associação)
CREATE TABLE Funcionario_Departamento (
    UUID UUID PRIMARY KEY,                -- Chave primária do tipo UUID
    UUID_Funcionario UUID NOT NULL,       -- Chave estrangeira da tabela Funcionario
    UUID_Depto UUID NOT NULL,             -- Chave estrangeira da tabela Departamento
    CONSTRAINT fk_funcionario FOREIGN KEY (UUID_Funcionario) REFERENCES Funcionario(UUID),
    CONSTRAINT fk_departamento FOREIGN KEY (UUID_Depto) REFERENCES Departamento(UUID)
);
