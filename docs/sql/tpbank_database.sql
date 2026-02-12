-- creation bdd
DROP DATABASE IF EXISTS tpbank;
CREATE DATABASE tpbank CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE tpbank;

-- ============================
-- craation de l'utilisateur 
-- ============================
DROP USER IF EXISTS 'tpbank_user'@'localhost';
CREATE USER 'tpbank_user'@'localhost' IDENTIFIED BY 'TpBank2026!';

-- Attribution des privilèges sur la base tpbank uniquement
GRANT SELECT, INSERT, UPDATE, DELETE ON tpbank.* TO 'tpbank_user'@'localhost';
FLUSH PRIVILEGES;

-- =========================
-- table: clients
-- ========================
CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- ===================
-- table: accounts
-- ===================
CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20) NOT NULL UNIQUE,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    type VARCHAR(20) NOT NULL,
    client_id BIGINT NOT NULL,
    overdraft_limit DECIMAL(15, 2) DEFAULT NULL,
    interest_rate DECIMAL(5, 2) DEFAULT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_account_client FOREIGN KEY (client_id) 
        REFERENCES clients(id) ON DELETE CASCADE,
    CONSTRAINT chk_account_type CHECK (type IN ('CURRENT', 'SAVINGS')),
    CONSTRAINT chk_balance CHECK (balance >= 0 OR type = 'CURRENT')
) ENGINE=InnoDB;

-- ======================
-- table: operations
-- ======================
CREATE TABLE operations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(20) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    operation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    account_id BIGINT NOT NULL,
    CONSTRAINT fk_operation_account FOREIGN KEY (account_id) 
        REFERENCES accounts(id) ON DELETE CASCADE,
    CONSTRAINT chk_operation_type CHECK (type IN ('DEPOSIT', 'WITHDRAWAL')),
    CONSTRAINT chk_amount CHECK (amount > 0)
) ENGINE=InnoDB;

-- ===========
-- index 
-- ===========

CREATE INDEX idx_account_client ON accounts(client_id);
CREATE INDEX idx_account_number ON accounts(number);
CREATE INDEX idx_operation_account ON operations(account_id);
CREATE INDEX idx_operation_date ON operations(operation_date);

-- =======================
-- données test
-- ========================

-- insertion clients
INSERT INTO clients (name, email, phone) VALUES
('Mor Diop', 'diop.mor@fms-ea.com', '0612345678'),
('Bubulle Lepoisson', 'bubulle.lepoisson@nemo.com', '0623456789'),
('Matthieu Meteo', 'meteo.france@meteo.com', '0633333333');

-- insert comptes
INSERT INTO accounts (number, balance, type, client_id, overdraft_limit, interest_rate) VALUES
('ACC001', 1500.00, 'CURRENT', 1, 500.00, NULL),
('ACC002', 5000.00, 'SAVINGS', 1, NULL, 2.50),
('ACC003', 2500.00, 'CURRENT', 2, 1000.00, NULL),
('ACC004', 10000.00, 'SAVINGS', 3, NULL, 3.00);

-- insertion d'operations
INSERT INTO operations (type, amount, account_id) VALUES
('DEPOSIT', 1000.00, 1),
('DEPOSIT', 500.00, 1),
('DEPOSIT', 5000.00, 2),
('WITHDRAWAL', 200.00, 1),
('DEPOSIT', 2500.00, 3),
('DEPOSIT', 10000.00, 4);

-- ==================
-- vérifs
-- ================
SELECT 'Clients créés:' AS Info;
SELECT * FROM clients;

SELECT 'Comptes créés:' AS Info;
SELECT * FROM accounts;

SELECT 'Opérations créées:' AS Info;
SELECT * FROM operations;

-- ============
-- infos
-- ==========
SELECT '

Database: tpbank
User: tpbank_user
Password: TpBank2026!
Host: localhost
Port: 3306 (défaut)

' AS 'Configuration';