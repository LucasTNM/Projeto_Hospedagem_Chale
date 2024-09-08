CREATE TABLE IF NOT EXISTS Cliente (
    codCliente SERIAL PRIMARY KEY,
    nomeCliente VARCHAR(100) NOT NULL,
    rgCliente VARCHAR(20) NOT NULL,
    endereco VARCHAR(150),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    estado VARCHAR(2),
    CEP VARCHAR(10),
    dataNascimento DATE
);

CREATE TABLE IF NOT EXISTS Chale (
    codChale SERIAL PRIMARY KEY,
    localizacao VARCHAR(100) NOT NULL,
    capacidade INT NOT NULL,
    valorAltaEstacao DECIMAL(10, 2) NOT NULL,
    valorBaixaEstacao DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Hospedagem (
    codHospedagem SERIAL PRIMARY KEY,
    codChale INT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    dataInicio DATE NOT NULL,
    dataFim DATE NOT NULL,
    qtdPessoas INT,
    desconto DECIMAL(5, 2),
    valorFinal DECIMAL(10, 2) NOT NULL,
    codCliente INT NOT NULL,
    FOREIGN KEY (codChale) REFERENCES Chale(codChale),
    FOREIGN KEY (codCliente) REFERENCES Cliente(codCliente)
);

INSERT INTO Cliente (nomeCliente, rgCliente, endereco, bairro, cidade, estado, CEP, dataNascimento)
VALUES ('João da Silva', '12345678', 'Rua A, 123', 'Centro', 'Salvador', 'BA', '40000-000', '1985-07-15');

INSERT INTO Cliente (nomeCliente, rgCliente, endereco, bairro, cidade, estado, CEP, dataNascimento)
VALUES ('Maria Oliveira', '87654321', 'Rua B, 456', 'Bairro Novo', 'Fortaleza', 'CE', '60000-000', '1990-03-22');

INSERT INTO Cliente (nomeCliente, rgCliente, endereco, bairro, cidade, estado, CEP, dataNascimento)
VALUES ('Carlos Pereira', '45678912', 'Avenida C, 789', 'Jardins', 'São Paulo', 'SP', '01000-000', '1978-12-05');

INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao)
VALUES ('Praia do Forte', 4, 500.00, 300.00);

INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao)
VALUES ('Morro de São Paulo', 2, 600.00, 400.00);

INSERT INTO Chale (localizacao, capacidade, valorAltaEstacao, valorBaixaEstacao)
VALUES ('Chapada Diamantina', 6, 700.00, 450.00);

INSERT INTO Hospedagem (codChale, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal, codCliente)
VALUES (1, 'Reservado', '2024-12-20', '2024-12-25', 4, 10.00, 450.00, 1);

INSERT INTO Hospedagem (codChale, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal, codCliente)
VALUES (2, 'Reservado', '2024-11-10', '2024-11-15', 2, 5.00, 570.00, 2);

INSERT INTO Hospedagem (codChale, estado, dataInicio, dataFim, qtdPessoas, desconto, valorFinal, codCliente)
VALUES (3, 'Confirmado', '2024-10-01', '2024-10-05', 6, 0.00, 700.00, 3);