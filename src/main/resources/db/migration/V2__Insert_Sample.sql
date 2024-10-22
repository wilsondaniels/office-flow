-- Inserir dados na tabela Departamento
INSERT INTO Departamento (UUID, departamento, qtdeFuncionarios)
VALUES 
    ('d2f501bc-7dcd-4d80-91e7-85c9e0d412db', 'Financeiro', 2),
    ('d9a2ec9e-2189-4a5b-8d12-e36e1a17af3b', 'Recursos Humanos', 2),
    ('3c8f4226-c1f3-4050-82a7-3fd2ccf00f45', 'TI', 3),
    ('503a38a3-7196-4d9c-90a9-3ae1a8d9ef7d', 'Marketing', 2),
    ('6552e670-391b-44f8-8980-0c0cf013cf2d', 'Logística', 1);

-- Inserir dados na tabela Funcionario
INSERT INTO Funcionario (UUID, nome, endereco, bairro, cep, telefone, salario, data_contrato, funcao)
VALUES 
    ('9e3a2e53-8d2a-41d4-b913-91e9f28a71b5', 'Ana Silva', 'Rua 1', 'Centro', '12345-678', '1234567890', 5000.00, '2022-01-15', 'Gerente de TI'),
    ('f5f6acbc-6bcb-4af0-a192-5d2b7fe8a1b7', 'João Souza', 'Rua 2', 'Centro', '23456-789', '0987654321', 3000.00, '2021-11-10', 'Analista de RH'),
    ('de55cb8a-913a-4ec1-bc0c-6824999ed9a8', 'Carlos Oliveira', 'Rua 3', 'Bairro Alto', '34567-890', '9876543210', 4500.00, '2020-05-22', 'Desenvolvedor Java'),
    ('847c5e90-bc97-4f09-9e13-6b41e37e5d96', 'Fernanda Lima', 'Rua 4', 'Jardim', '45678-901', '8765432109', 3500.00, '2021-12-01', 'Analista Financeiro'),
    ('8d5d40e5-9f29-41bc-91c6-b41b9d74b5ea', 'Ricardo Alves', 'Rua 5', 'Centro', '56789-012', '7654321098', 5500.00, '2019-08-30', 'Coordenador de Marketing'),
    ('29b73d5e-b2a0-46a4-b5b9-9d82cbac024a', 'Juliana Souza', 'Rua 6', 'Bairro Alto', '67890-123', '6543210987', 4000.00, '2020-06-15', 'Analista de Logística'),
    ('b8d8d0ad-55d4-42d7-8ac6-e03410b684f2', 'Paulo Pereira', 'Rua 7', 'Centro', '78901-234', '5432109876', 3800.00, '2021-03-12', 'Assistente de TI'),
    ('a5e2f0ff-9e5d-4f49-a8c6-2f31f0474345', 'Carla Santos', 'Rua 8', 'Jardim', '89012-345', '4321098765', 3300.00, '2020-11-05', 'Assistente de Marketing'),
    ('d84b4b2b-9149-4fd4-8927-c02e1678bc12', 'Rafael Costa', 'Rua 9', 'Centro', '90123-456', '3210987654', 5200.00, '2021-01-28', 'Gerente de Logística'),
    ('f2a5cf4d-8cf5-4d87-8b7d-c6fd1dc7f534', 'Laura Martins', 'Rua 10', 'Centro', '01234-567', '2109876543', 4700.00, '2022-04-20', 'Desenvolvedora Fullstack');

-- Inserir dados na tabela de relacionamento Funcionario x Departamento
INSERT INTO Funcionario_Departamento (UUID, UUID_Funcionario, UUID_Depto)
VALUES
    ('af12b0c3-2935-4a7c-89ac-2b2a8f7e6fc7', '9e3a2e53-8d2a-41d4-b913-91e9f28a71b5', '3c8f4226-c1f3-4050-82a7-3fd2ccf00f45'),
    ('b1238473-d3e1-40b5-a093-e2d5b8d15347', 'f5f6acbc-6bcb-4af0-a192-5d2b7fe8a1b7', 'd9a2ec9e-2189-4a5b-8d12-e36e1a17af3b'),
    ('d2d53b2b-4f63-4c57-b171-1e8033b1919b', 'de55cb8a-913a-4ec1-bc0c-6824999ed9a8', '3c8f4226-c1f3-4050-82a7-3fd2ccf00f45'),
    ('a3561d3d-0b2c-497e-8fc7-d7b978ba4600', '847c5e90-bc97-4f09-9e13-6b41e37e5d96', 'd2f501bc-7dcd-4d80-91e7-85c9e0d412db'),
    ('ff7b8c55-cd16-4b8a-8d9f-e16090a66d80', '8d5d40e5-9f29-41bc-91c6-b41b9d74b5ea', '503a38a3-7196-4d9c-90a9-3ae1a8d9ef7d'),
    ('b17eb15e-1c1d-4c41-a497-7a6cddb5b6a0', '29b73d5e-b2a0-46a4-b5b9-9d82cbac024a', '6552e670-391b-44f8-8980-0c0cf013cf2d'),
    ('bb9147e2-3783-43ba-b9c4-25c6d11a7f89', 'b8d8d0ad-55d4-42d7-8ac6-e03410b684f2', '3c8f4226-c1f3-4050-82a7-3fd2ccf00f45'),
    ('f7a841a2-f7cf-4080-b67b-c5204c3e7e55', 'a5e2f0ff-9e5d-4f49-a8c6-2f31f0474345', '503a38a3-7196-4d9c-90a9-3ae1a8d9ef7d'),
    ('c9574522-e097-49c4-b1ff-563f0c54a5d4', 'd84b4b2b-9149-4fd4-8927-c02e1678bc12', '6552e670-391b-44f8-8980-0c0cf013cf2d'),
    ('c87ff47e-407f-492b-9d62-3b58216a6f6a', 'f2a5cf4d-8cf5-4d87-8b7d-c6fd1dc7f534', '3c8f4226-c1f3-4050-82a7-3fd2ccf00f45');
